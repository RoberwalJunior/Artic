package minigartic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GarticCliente extends javax.swing.JFrame {

    private static final long serialVersionUID = 5661286812709693531L;
    JPanel draft;
    boolean ProximoDesenhar = false;
    boolean PermitirDesenhar = false;
    String nomeJogador;

    ArrayList<Point> points = new ArrayList<>();

    public GarticCliente() {
        initComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        bntIniciarJogo.setVisible(false);
        jLabeltemas.setVisible(false);
        jComboBoxTemas.setVisible(false);
        draft = new Draft();
        draft.setBounds(0, 0, 820, 530);
        draft.setBackground(Color.white);
        draft.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (PermitirDesenhar) {
                    Point ponto = e.getPoint();
                    tcpClient.writeMessage("3|" + ponto.x + "|" + ponto.y);
                    points.add(ponto);
                    draft.repaint();
                }

            }

        });
        draft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // adiciona uma coordenada nula para ignorarmos
                // no paintComponent
                if (PermitirDesenhar) {
                    Point ponto = e.getPoint();
                    tcpClient.writeMessage("3|" + ponto.x + "|" + ponto.y);
                    points.add(ponto);
                    tcpClient.writeMessage("3|-99|-99");
                    points.add(new Point(-99,-99));
                }
            }
        });
        jPanel1.add(draft, BorderLayout.CENTER);
        draft.setLayout(null);
        draft.grabFocus();

        try {
            InputStream imageStream = this.getClass().getResourceAsStream("pencil.png");
            Image image;
            image = ImageIO.read(imageStream);
            Cursor pencilCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 26), "pencil");
            setCursor(pencilCursor);
        } catch (IOException ex) {
            System.out.println("Errro");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaResposta = new javax.swing.JTextArea();
        jTextFieldChat = new javax.swing.JTextField();
        jTextFieldResposta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextServer = new javax.swing.JTextField();
        JtextPorta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonConectar = new javax.swing.JButton();
        jButtonDesconectar = new javax.swing.JButton();
        JogadorAtual = new javax.swing.JLabel();
        JtextNomeJogador = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bntIniciarJogo = new javax.swing.JButton();
        jLabeltemas = new javax.swing.JLabel();
        jComboBoxTemas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanelPrincipal.setBackground(new java.awt.Color(102, 153, 255));
        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(800, 850));
        jPanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 820, 530));

        jPanelPrincipal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 900, 570));

        jTextAreaChat.setEditable(false);
        jTextAreaChat.setColumns(30);
        jTextAreaChat.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextAreaChat.setRows(5);
        jScrollPane1.setViewportView(jTextAreaChat);

        jPanelPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 740, 400, -1));

        jTextAreaResposta.setEditable(false);
        jTextAreaResposta.setColumns(30);
        jTextAreaResposta.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextAreaResposta.setRows(5);
        jScrollPane2.setViewportView(jTextAreaResposta);

        jPanelPrincipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 740, 400, -1));

        jTextFieldChat.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextFieldChat.setPreferredSize(new java.awt.Dimension(69, 30));
        jPanelPrincipal.add(jTextFieldChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 860, 400, -1));

        jTextFieldResposta.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextFieldResposta.setPreferredSize(new java.awt.Dimension(69, 30));
        jTextFieldResposta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRespostaActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(jTextFieldResposta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 860, 400, -1));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chat");
        jPanelPrincipal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 700, 400, 40));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Respostas");
        jPanelPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 700, 400, 40));

        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("Server:");
        jPanelPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 60, 20));

        jTextServer.setText("localhost");
        jPanelPrincipal.add(jTextServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 130, -1));

        JtextPorta.setText("6789");
        jPanelPrincipal.add(JtextPorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, 50, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("Porta:");
        jPanelPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 50, 20));

        jButtonConectar.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jButtonConectar.setText("Jogar");
        jButtonConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(jButtonConectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 900, 640, 60));

        jButtonDesconectar.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jButtonDesconectar.setText("Sair");
        jButtonDesconectar.setToolTipText("");
        jButtonDesconectar.setEnabled(false);
        jButtonDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesconectarActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(jButtonDesconectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 900, 190, 60));

        JogadorAtual.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JogadorAtual.setForeground(new java.awt.Color(255, 255, 255));
        JogadorAtual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JogadorAtual.setText("Aguardando Jogadores");
        jPanelPrincipal.add(JogadorAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 900, 40));
        JogadorAtual.getAccessibleContext().setAccessibleName("Label5");

        jPanelPrincipal.add(JtextNomeJogador, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 240, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 204));
        jLabel5.setText("Nome do jogador:");
        jPanelPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 30));

        bntIniciarJogo.setText("Iniciar jogo");
        bntIniciarJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntIniciarJogoActionPerformed(evt);
            }
        });
        jPanelPrincipal.add(bntIniciarJogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 340, 40));

        jLabeltemas.setForeground(new java.awt.Color(255, 255, 204));
        jLabeltemas.setText("Tema:");
        jPanelPrincipal.add(jLabeltemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 60, 20));

        jComboBoxTemas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Animais", "Objeto", "Alimento" }));
        jPanelPrincipal.add(jComboBoxTemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarActionPerformed

        try {
            if (JtextNomeJogador.getText().equals("")) {
                JtextNomeJogador.requestFocus();
                throw new IOException("Insira seu nome para continuar...");
            }

            String server = jTextServer.getText();
            int porta = Integer.parseInt(JtextPorta.getText());
            nomeJogador = JtextNomeJogador.getText();

            this.tcpClient = new GarticClienteMain(server, porta, this);
            this.tcpClient.writeMessage("0");
            this.tcpClient.writeMessage("-2|" + nomeJogador);
            
            

            jButtonDesconectar.setEnabled(true);
            jButtonConectar.setEnabled(false);
            JtextNomeJogador.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButtonConectarActionPerformed

    private void jButtonDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesconectarActionPerformed
        closeConnection();
    }//GEN-LAST:event_jButtonDesconectarActionPerformed

    private void bntIniciarJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntIniciarJogoActionPerformed
        // Botão para iniciar partida.
        if(jComboBoxTemas.getSelectedItem().equals("Animais")){
            this.tcpClient.writeMessage("-3|0|6");
        } else if(jComboBoxTemas.getSelectedItem().equals("Objeto")){
            this.tcpClient.writeMessage("-3|7|13");
        } else {
            this.tcpClient.writeMessage("-3|14|20");
        }

        this.tcpClient.writeMessage("-1");
        bntIniciarJogo.setVisible(false);
        jLabeltemas.setVisible(false);
        jComboBoxTemas.setVisible(false);
    }//GEN-LAST:event_bntIniciarJogoActionPerformed

    private void jTextFieldRespostaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRespostaActionPerformed
        // TODO add your handling code here:
        this.tcpClient.writeMessage("-4|" + nomeJogador + "|" + jTextFieldResposta.getText());
        jTextFieldResposta.setText("");
    }//GEN-LAST:event_jTextFieldRespostaActionPerformed

    public void closeConnection() {
        try {
            tcpClient.closeConnection();
            jButtonConectar.setEnabled(true);
            jButtonDesconectar.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ResetarPontos() {
        this.points = new ArrayList<>();
        this.draft.repaint();
    }

    public void AbilitarBotaoIniciar(){
        bntIniciarJogo.setVisible(true);
        jLabeltemas.setVisible(true);
        jComboBoxTemas.setVisible(true);
    }
    
    public ArrayList<Point> getPoints() {
        return this.points;
    }
    
    public void AtualizarTitulo(String conteudo){
        JogadorAtual.setText(conteudo);
    }
    
    public void SetResposta(String message){
        jTextAreaResposta.setText(jTextAreaResposta.getText() + "\n" +  message);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GarticCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GarticCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GarticCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GarticCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GarticCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JogadorAtual;
    private javax.swing.JTextField JtextNomeJogador;
    private javax.swing.JTextField JtextPorta;
    private javax.swing.JButton bntIniciarJogo;
    private javax.swing.JButton jButtonConectar;
    private javax.swing.JButton jButtonDesconectar;
    private javax.swing.JComboBox<String> jComboBoxTemas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabeltemas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaChat;
    private javax.swing.JTextArea jTextAreaResposta;
    private javax.swing.JTextField jTextFieldChat;
    private javax.swing.JTextField jTextFieldResposta;
    private javax.swing.JTextField jTextServer;
    // End of variables declaration//GEN-END:variables
private GarticClienteMain tcpClient;

    public class Draft extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 4886600019364448097L;

        public Draft() {

        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);
            int i = 0;

            while (i < points.size() - 1) {

                Point currentPoint = points.get(i);
                Point nextPoint = points.get(i + 1);

                if (nextPoint.x != -99 && nextPoint.y != -99) {
                    Graphics2D g1 = (Graphics2D) g;
                    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g1.setRenderingHints(rh);
                    g1.drawLine(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
                    i++;

                } else {
                    // quando as coordenadas do ponto seguinte forem (-99, -99),
                    // pulamos essa iteração para evitar que a linha anterior
                    // seja ligada a nova linha que está sendo desenhada
                    i += 2;
                }
            }
        }
    }
}
