package main;

import Entity.Jogador;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Configurações de tela
    private final int originalTileSize = 16; // quadros 16x16
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 = tamanho real dos tiles
    private final int maxTilesVertical = 16;
    private final int maxTilesHorizontal = 12;
    private final int larguraTela = tileSize * maxTilesVertical; //768px
    private final int alturaTela = tileSize * maxTilesHorizontal;//576px

    final int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Jogador jogador = new Jogador(this, keyHandler);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(larguraTela,alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread =  new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval =  1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread!= null){

            long currentTime = System.nanoTime();
            System.out.println(currentTime);

            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        jogador.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        jogador.drawJogador(g2);
        g2.dispose();
    }
}
