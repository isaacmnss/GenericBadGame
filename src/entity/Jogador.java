package entity;

import main.GamePanel;
import input.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Jogador extends Entidade {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int idleCounter = 0;

    public Jogador(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.larguraTela/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.alturaTela/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setValoresDefault();
        getImagemJogador();
    }

    public void setValoresDefault(){
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        velocidade = 4;
        direcao = "baixo";
    }

    public void getImagemJogador(){
        cima1 = setup("boy_up_1");
        cima2 = setup("boy_up_2");
        baixo1 = setup("boy_down_1");
        baixo2 = setup("boy_down_2");
        esquerda1 = setup("boy_left_1");
        esquerda2 = setup("boy_left_2");
        direita1 = setup("boy_right_1");
        direita2 = setup("boy_right_2");
    }

    public BufferedImage setup(String nomeImagem){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/"+nomeImagem+".png")));
            imagem = utilityTool.scaleImage(imagem, gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao obter recurso: "+"/player/"+nomeImagem+".png", e);
        }
        return imagem;
    }

    public void update(){
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed){
            if(keyHandler.upPressed){
                direcao = "cima";
            }
            if(keyHandler.downPressed){
                direcao = "baixo";
            }
            if(keyHandler.leftPressed){
                direcao = "esquerda";
            }
            if (keyHandler.rightPressed){
                direcao = "direita";
            }

            collisionOn = false;
            gamePanel.cm.checkTile(this);

            int indexObjeto = gamePanel.cm.checkObjeto(this, true);
            pegarObjeto(indexObjeto);

            if (!collisionOn){
                switch (direcao){
                    case "cima" -> worldY -= velocidade;
                    case "baixo" -> worldY += velocidade;
                    case "esquerda" -> worldX -= velocidade;
                    case "direita" -> worldX += velocidade;
                }
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
        }else {
            idleCounter++;
            if (idleCounter == 20){
                spriteNum=1;
                idleCounter = 0;
            }
        }
    }

    public void pegarObjeto(int index){
        if(index != 999){

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
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
