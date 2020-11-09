package minigartic;

import java.io.IOException;
import java.util.List;

public class GarticServidorHandler extends Thread{
    
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
    public void run(){
        
    }
    
}
