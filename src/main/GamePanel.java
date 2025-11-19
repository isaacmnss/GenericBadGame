package main;

import collision.CollisionManager;
import entity.Entidade;
import entity.Jogador;
import input.KeyHandler;
import tile.TileManager;
import ui.HUD;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

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

    public KeyHandler keyHandler = new KeyHandler(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionManager cm = new CollisionManager(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public HUD hud = new HUD(this);
    public EventHandler eventHandler = new EventHandler(this);

    public Jogador jogador = new Jogador(this, keyHandler);
    public Entidade[] objeto = new Entidade[10];
    public Entidade[] npcs = new Entidade[10];
    public Entidade[] monstros = new Entidade[20];
    ArrayList<Entidade> listaEntidades = new ArrayList<>();


    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(larguraTela,alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setObjetos();
        assetSetter.setNPC();
        assetSetter.setMonstros();
        gameState = titleState;

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
                throw new RuntimeException("Erro de Thread: ",e);
            }
        }
    }

    public void update(){
        if (gameState == playState){
            jogador.update();
            for (Entidade npc : npcs) {
                if (npc != null) {
                    npc.update();
                }
            }
            for (int i = 0; i < monstros.length; i++) {
                Entidade monstro = monstros[i];
                if (monstro != null) {
                    if (monstro.vivo && !monstro.morrendo) {
                        monstro.update();
                    }
                    if (!monstro.vivo) {
                        monstros[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState){}
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        long inicioRenderizacao = 0;
        if (keyHandler.checkTempoDeRender){
            inicioRenderizacao = System.nanoTime();
        }

        if(gameState == titleState){
            hud.draw(g2);
        }else {

            tileManager.draw(g2);

            listaEntidades.add(jogador);

            for (Entidade npc : npcs) {
                if (npc != null) {
                    listaEntidades.add(npc);
                }
            }

            for (Entidade objeto : objeto) {
                if (objeto != null) {
                    listaEntidades.add(objeto);
                }
            }

            for (Entidade monstro : monstros){
                if (monstro != null){
                    listaEntidades.add(monstro);
                }
            }

            listaEntidades.sort(new Comparator<Entidade>() {
                @Override
                public int compare(Entidade e1, Entidade e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return 0;
                }
            });

            listaEntidades.forEach(entidade -> entidade.draw(g2));
            listaEntidades.clear();




            hud.draw(g2);
        }

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
