package minigartic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class GarticServidorMain extends Thread {
    
    private final List<GarticServidorConnection> clientes;
    private final ServerSocket server;
    String desenho = "Sapo";
    GarticServidorConnection desenhista;

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
