package minigartic;

import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class GarticServidorHandler extends Thread {

    private GarticServidorConnection cliente;
    private GarticServidorMain caller;

    public GarticServidorHandler(GarticServidorConnection cliente, GarticServidorMain caller) throws IOException {
        this.cliente = cliente;
        this.caller = caller;
    }

    @Override
    protected void finalize() throws Throwable {
        encerrar();
    }

    private void encerrar() {
        this.caller.removerCliente(this.cliente);
    }

    public synchronized void messageDispatcher(String message) throws IOException {
        List<GarticServidorConnection> clientes = this.caller.getClientes();
        for (GarticServidorConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getOutput().println(message);
                cli.getOutput().flush();
            }
        }
    }
    
    public synchronized void TrocaDeTurno(String message) throws IOException {
        this.caller.Turno++;
        List<GarticServidorConnection> clientes = this.caller.getClientes();
        if(this.caller.Turno == clientes.size() - 1){
            this.caller.Turno = 0;
        }
        GarticServidorConnection proximoDesenhista = clientes.get(this.caller.Turno);
        for (GarticServidorConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                if(cli == proximoDesenhista){
                    cli.getOutput().println("1| É a sua vez de Desenhar : " + this.caller.animais[2]);
                    cli.getOutput().flush();
                } else {
                    cli.getOutput().println("2| É a vez de " + proximoDesenhista.GetNomeJogador() + "!");
                    cli.getOutput().flush();
                }
                
            }
        }
    }

    @Override
    public void run() {

        String message;
        while (true) {
            try {
                if (this.cliente.getSocket().isConnected() && this.cliente.getInput() != null) {
                    message = this.cliente.getInput().readLine();
                } else {
                    break;
                }
                if (message == null || message.equals("")) {
                    break;
                }

                System.out.println(message);
                StringTokenizer tokens = new StringTokenizer(message, "|");
                int acao = Integer.parseInt(tokens.nextToken());
                
                if (acao == -1) {
                    TrocaDeTurno(message);
                    
                } else if(acao == -2){
                    List<GarticServidorConnection> teste = this.caller.getClientes();
                    GarticServidorConnection ultimo = teste.get(teste.size() - 1);
                    ultimo.SetNomeJogador(tokens.nextToken());
                } else {
                    int x = Integer.parseInt(tokens.nextToken());
                    int y = Integer.parseInt(tokens.nextToken());
                    //Point ponto = new Point(x,y);

                    messageDispatcher("3|" + x + "|" + y);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

    }
    // -1 => Condição para trocar de desenhista e começar um novo turno
    // -2 => Adicionar um novo jogador
    // 1 => Ultima opção é para desenhar
    

}
