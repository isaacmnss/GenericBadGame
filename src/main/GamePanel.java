package main;

import collision.CollisionManager;
import entity.Jogador;
import input.KeyHandler;
import objects.SuperObjeto;
import tile.TileManager;
import ui.HUD;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Configurações de tela
    private final int originalTileSize = 16; // quadros 16x16
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 = tamanho real dos tiles
    public final int maxTilesVertical = 16;
    public final int maxTilesHorizontal = 12;
    public final int larguraTela = tileSize * maxTilesVertical; //768px
    public final int alturaTela = tileSize * maxTilesHorizontal;//576px

    // Configs de mundo
    public final int maxColunasMundo = 50;
    public final int maxLinhasMundo = 50;
    public final int larguraMundo = tileSize * maxColunasMundo;
    public final int alturaMundo =  tileSize * maxLinhasMundo;

    final int FPS = 60;
    public Thread gameThread;

    KeyHandler keyHandler = new KeyHandler();
    public TileManager tileManager = new TileManager(this);
    public CollisionManager cm = new CollisionManager(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public HUD hud = new HUD(this);
    public Jogador jogador = new Jogador(this, keyHandler);

    public SuperObjeto[] objeto = new SuperObjeto[10];

    public GamePanel(){
        this.setPreferredSize(new Dimension(larguraTela,alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setObjetos();
    }

    public void startGameThread(){
        gameThread =  new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval =  1000000000D/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread!= null){
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

        long inicioRenderizacao = 0;
        if (keyHandler.checkTempoDeRender){
            inicioRenderizacao = System.nanoTime();
        }


        tileManager.draw(g2);

        for (SuperObjeto superObjeto : objeto) {
            if (superObjeto != null) {
                superObjeto.draw(g2, this);
            }

        }

        jogador.drawJogador(g2);

        hud.draw(g2);

        if (keyHandler.checkTempoDeRender){
            long fimRenderizacao = System.nanoTime();
            long tempoRender = fimRenderizacao - inicioRenderizacao;
            g2.setColor(Color.white);
            g2.drawString("Tempo de rendezização: "+tempoRender,10,400);
            System.out.println("Tempo de renderização: "+tempoRender);
        }

        g2.dispose();
    }
}
