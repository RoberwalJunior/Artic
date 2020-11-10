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
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                //Point ponto = new Point(x,y);
                
                
                messageDispatcher(x + "|" + y);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

}
