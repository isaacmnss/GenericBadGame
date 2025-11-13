package Entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Jogador extends Entidade {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Jogador(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setValoresDefault();
    }

    public void setValoresDefault(){
        x = 100;
        y = 100;
        velocidade = 4;
    }

    public void update(){
        if(keyHandler.upPressed){
            y -= velocidade;
        }
        if(keyHandler.downPressed){
            y += velocidade;
        }
        if(keyHandler.leftPressed){
            x -= velocidade;
        }
        if (keyHandler.rightPressed){
            x += velocidade;
        }
    }

    public void drawJogador(Graphics2D g2){

        g2.setColor(Color.CYAN);

        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        g2.dispose();
    }
}
