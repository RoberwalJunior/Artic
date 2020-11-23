package minigartic;

import java.awt.Point;
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
                    System.out.println(message);
                } else {
                    break;
                }
                if (message == null || message.equals("")) {
                    break;
                }

                //Mensagem será x|y
                //caller.apagaBolinha();
                StringTokenizer tokens = new StringTokenizer(message, "|");
                String acao =  tokens.nextToken();
                if (acao.equals("0")) {
                    
                    this.caller.AbilitarBotaoIniciar();
                    this.caller.ProximoDesenhar = true;
                    
                } else if(acao.equals("1")){
                    
                    String conteudo = tokens.nextToken();
                    this.caller.AtualizarTitulo(conteudo);
                    this.caller.PermitirDesenhar = true;
                    this.caller.ResetarPontos();
                    
                } else if(acao.equals("2")) {
                    
                    this.caller.AtualizarTitulo(tokens.nextToken());
                    this.caller.PermitirDesenhar = false;
                    this.caller.ResetarPontos();
                    
                } else {
                    
                    int x = Integer.parseInt(tokens.nextToken());
                    int y = Integer.parseInt(tokens.nextToken());

                    caller.points.add(new Point(x, y));
                    caller.draft.repaint();
                    
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }

    }
    
    // 0 => Habilitar o botao para iniciar a partida
    // 1 => Atualizar o titulo da partida

}
