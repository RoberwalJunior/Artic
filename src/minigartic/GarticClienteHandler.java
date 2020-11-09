package minigartic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;


public class GarticClienteHandler extends Thread {
    
    private Socket socket;
    private GarticCliente caller;
    private BufferedReader input;

    public GarticClienteHandler(Socket socket, GarticCliente caller) throws IOException {
        this.socket = socket;
        this.caller = caller;
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        
        String message;
        while (true) {
            try {
                if (this.socket.isConnected() && this.input != null) {
                    message = this.input.readLine();
                } else {
                    break;
                }
                if (message == null || message.equals("")) {
                    break;
                }
                
                //Mensagem será x|y
                //caller.apagaBolinha();
                StringTokenizer tokens = new StringTokenizer(message, "|");
                //caller.X = Integer.parseInt(tokens.nextToken());
                //caller.Y = Integer.parseInt(tokens.nextToken());
                //caller.pintaBolinha();
                
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
        
    }
    
    
    
}