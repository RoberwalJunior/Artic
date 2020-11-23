package minigartic;

import java.awt.Point;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class GarticServidorMain extends Thread {
    
    private List<GarticServidorConnection> clientes;
    int Turno = -1;
    private ServerSocket server;
    String[] desenho = {
        /*0*/"Peixe", "Tubarão", "Girafa", "Leão", "Macaco", "Abelha", "Baleia",/*6*/
        /*7*/"Óculos", "Relógio", "Garrafa", "Abajur", "Âncora", "Cadeado", "Faca",/*13*/
        /*14*/"Feijão", "Arroz", "Linguiça", "Uvas", "Bala", "Leite", "Queijo"/*20*/};
    int min, max;
    ArrayList<Point> points = new ArrayList<>();
    GarticServidorConnection desenhista;
    String desenhoAtual;
    int acertos = 0;

    public GarticServidorMain(int porta) throws IOException {
        
        this.server = new ServerSocket(porta);
        System.out.println(this.getClass().getSimpleName() + " rodando na porta: " + server.getLocalPort());
        this.clientes = new ArrayList<>();
        
    }
    
    @Override
    public void run(){
        
        Socket socket;
        while(true){
            try {
                socket = this.server.accept();
                GarticServidorConnection cliente = new GarticServidorConnection(socket);
                novoCliente(cliente);
                (new GarticServidorHandler(cliente, this)).start();
                
            } catch (IOException ex) {
                System.out.println("Erro 4: " + ex.getMessage());
            }
        }
        
    }
    
    public synchronized void novoCliente(GarticServidorConnection cliente) throws IOException {
        if(clientes.isEmpty()){
            cliente.getOutput().println("0|0|0");
        }
        clientes.add(cliente);
    }

    public synchronized void removerCliente(GarticServidorConnection cliente) {
        clientes.remove(cliente);
        try {
            cliente.getInput().close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        cliente.getOutput().close();
        try {
            cliente.getSocket().close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List getClientes() {
        return clientes;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.server.close();
    }
    
}
