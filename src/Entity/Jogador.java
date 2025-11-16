package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Entidade {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Jogador(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setValoresDefault();
        getImagemJogador();
    }

    public void setValoresDefault(){
        x = 100;
        y = 100;
        velocidade = 4;
        direcao = "baixo";
    }

    public void getImagemJogador(){
        try {
            cima1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            cima2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            baixo1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            baixo2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            esquerda1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            esquerda2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            direita1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            direita2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.rightPressed == true || keyHandler.leftPressed == true){
            if(keyHandler.upPressed){
                direcao = "cima";
                y -= velocidade;
            }
            if(keyHandler.downPressed){
                direcao = "baixo";
                y += velocidade;
            }
            if(keyHandler.leftPressed){
                direcao = "esquerda";
                x -= velocidade;
            }
            if (keyHandler.rightPressed){
                direcao = "direita";
                x += velocidade;
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if (spriteNum ==1 ){
                    spriteNum =2;
                } else if (spriteNum  == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void drawJogador(Graphics2D g2){
        BufferedImage image = null;

        switch (direcao){
            case "cima" :
                if (spriteNum == 1){
                    image = cima1;
                }
                if (spriteNum == 2){
                    image = cima2;
                }
                break;
            case "baixo":
                if (spriteNum == 1){
                    image = baixo1;
                }
                if (spriteNum == 2){
                    image = baixo2;
                }
                break;
            case "esquerda":
                if (spriteNum == 1){
                    image = esquerda1;
                }
                if (spriteNum == 2){
                    image = esquerda2;
                }
                break;
            case "direita":
                if (spriteNum == 1){
                    image = direita1;
                }
                if (spriteNum == 2){
                    image = direita2;
                }
                break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
