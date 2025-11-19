package entity;

import main.GamePanel;
import input.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador extends Entidade {
    private KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int idleCounter = 0;

    public Jogador(GamePanel gamePanel, KeyHandler keyHandler){
        super(gamePanel);

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
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        velocidade = 4;
        direcao = "baixo";

        vidaMaxima = 6;
        vida = vidaMaxima;
    }

    public void getImagemJogador(){
        cima1 = setup("/player/boy_up_1.png");
        cima2 = setup("/player/boy_up_2.png");
        baixo1 = setup("/player/boy_down_1.png");
        baixo2 = setup("/player/boy_down_2.png");
        esquerda1 = setup("/player/boy_left_1.png");
        esquerda2 = setup("/player/boy_left_2.png");
        direita1 = setup("/player/boy_right_1.png");
        direita2 = setup("/player/boy_right_2.png");
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
            gp.cm.checkTile(this);

            int indexObjeto = gp.cm.checkObjeto(this, true);
            pegarObjeto(indexObjeto);

            int npcIndex = gp.cm.checkEntidade(this, gp.npcs);
            interagirComNPC(npcIndex);

            gp.eventHandler.checkEvento();

            gp.keyHandler.interactPressed = false;

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

    private void interagirComNPC(int npcIndex) {
        if(npcIndex != 999){
            if (gp.keyHandler.interactPressed){
                gp.gameState = gp.dialogState;
                gp.npcs[npcIndex].falar();
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
