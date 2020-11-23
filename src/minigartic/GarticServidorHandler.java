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
    
    public synchronized void TrocaDeTurno() throws IOException {
        this.caller.Turno++;
        List<GarticServidorConnection> clientes = this.caller.getClientes();
        if(this.caller.Turno == clientes.size()){
            this.caller.Turno = 0;
        }
        GarticServidorConnection proximoDesenhista = clientes.get(this.caller.Turno);
        this.caller.desenhoAtual = this.caller.desenho[(int) (Math.random() * (this.caller.max - this.caller.min + 1) + this.caller.min)];
        for (GarticServidorConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                if(cli == proximoDesenhista){
                    cli.getOutput().println("1| É a sua vez de Desenhar : " + this.caller.desenhoAtual);
                    cli.getOutput().flush();
                } else {
                    cli.getOutput().println("2| É a vez de " + proximoDesenhista.GetNomeJogador() + "!");
                    cli.getOutput().flush();
                }
                
            }
        }
    }
    
    public synchronized void MandarResposta(String message) throws IOException{
        
        List<GarticServidorConnection> clientes = this.caller.getClientes();
        for (GarticServidorConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getOutput().println("5|" + message);
                cli.getOutput().flush();
            }
        }
        
    }
    
    public synchronized void MandarChat(String message) throws IOException{
        
        List<GarticServidorConnection> clientes = this.caller.getClientes();
        for (GarticServidorConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getOutput().println("6|" + message);
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
                int acao = Integer.parseInt(tokens.nextToken());
                
                switch (acao) {
                    case -1:
                        TrocaDeTurno();
                        break;
                    case -2:
                        List<GarticServidorConnection> teste = this.caller.getClientes();
                        GarticServidorConnection ultimo = teste.get(teste.size() - 1);
                        ultimo.SetNomeJogador(tokens.nextToken());
                        break;
                    case 3:
                        int x = Integer.parseInt(tokens.nextToken());
                        int y = Integer.parseInt(tokens.nextToken());
                        //Point ponto = new Point(x,y);
                        messageDispatcher("3|" + x + "|" + y);
                        break;
                    case -3:
                        int min = Integer.parseInt(tokens.nextToken());
                        int max = Integer.parseInt(tokens.nextToken());
                        this.caller.min = min;
                        this.caller.max = max;
                        break;
                    case -4:
                        String nomeJogador = tokens.nextToken();
                        String resposta = tokens.nextToken();
                        if(this.caller.desenhoAtual.equals(resposta)){
                            MandarResposta(nomeJogador + " Acertou a resposta!");
                            this.caller.acertos++;
                            if(this.caller.acertos == this.caller.getClientes().size() - 1){
                                this.caller.acertos = 0;
                                TrocaDeTurno();
                            }
                        } else {
                            MandarResposta(nomeJogador + " : " + resposta);
                        }
                        break;
                    case -5:
                        String jogador = tokens.nextToken();
                        String mensagemChat = tokens.nextToken();
                        MandarChat(jogador + " : " + mensagemChat);
                        break;
                    default:
                        break;
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
