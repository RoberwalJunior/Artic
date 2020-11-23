package minigartic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class GarticClienteMain {
    
    private GarticClienteHandler handler;
    private Socket socket;
    private PrintWriter output;

    public GarticClienteMain(String serverAddress, int serverPort, GarticCliente caller) throws UnknownHostException, IOException {
        
        this.socket = new Socket(serverAddress, serverPort);
        //this.socket.setKeepAlive(true);
        this.handler = new GarticClienteHandler(socket, caller);
        this.handler.start();
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
        
    }
    
    public void writeMessage(String outMessage) {
        this.output.println(outMessage);
    }

    public void closeConnection() throws IOException {
        this.handler.stop();
        this.output.close();
        this.socket.close();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.closeConnection();
        } finally {
            super.finalize();
        }
    }
    
    
}
