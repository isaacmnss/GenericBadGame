package entity;

import main.GamePanel;
import input.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador extends Entidade {
    private final KeyHandler keyHandler;

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

        areaAtaque.width = 36;
        areaAtaque.height = 36;

        setValoresDefault();
        getImagemJogador();
        getImagemAtaqueJogador();
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
        cima1 = setup("/player/boy_up_1.png", gp.tileSize, gp.tileSize);
        cima2 = setup("/player/boy_up_2.png", gp.tileSize, gp.tileSize);
        baixo1 = setup("/player/boy_down_1.png", gp.tileSize, gp.tileSize);
        baixo2 = setup("/player/boy_down_2.png", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/player/boy_left_1.png", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/player/boy_left_2.png", gp.tileSize, gp.tileSize);
        direita1 = setup("/player/boy_right_1.png", gp.tileSize, gp.tileSize);
        direita2 = setup("/player/boy_right_2.png", gp.tileSize, gp.tileSize);
    }

    public void getImagemAtaqueJogador(){
        ataqueCima1 = setup("/player/boy_attack_up_1.png", gp.tileSize, gp.tileSize*2);
        ataqueCima2 = setup("/player/boy_attack_up_2.png", gp.tileSize, gp.tileSize*2);
        ataqueBaixo1 = setup("/player/boy_attack_down_1.png", gp.tileSize, gp.tileSize*2);
        ataqueBaixo2 = setup("/player/boy_attack_down_2.png", gp.tileSize, gp.tileSize*2);
        ataqueEsquerda1 = setup("/player/boy_attack_left_1.png", gp.tileSize*2, gp.tileSize);
        ataqueEsquerda2 = setup("/player/boy_attack_left_2.png", gp.tileSize*2, gp.tileSize);
        ataqueDireita1 = setup("/player/boy_attack_right_1.png", gp.tileSize*2, gp.tileSize);
        ataqueDireita2 = setup("/player/boy_attack_right_2.png", gp.tileSize*2, gp.tileSize);
    }

    public void update(){

        if (atacando){
            atacar();

        }
        else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.interactPressed){
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

            int indexMonstro = gp.cm.checkEntidade(this, gp.monstros);
            contatoMonstro(indexMonstro);

            gp.eventHandler.checkEvento();



            if (!collisionOn && !keyHandler.interactPressed){
                switch (direcao){
                    case "cima" -> worldY -= velocidade;
                    case "baixo" -> worldY += velocidade;
                    case "esquerda" -> worldX -= velocidade;
                    case "direita" -> worldX += velocidade;
                }
            }

            gp.keyHandler.interactPressed = false;
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
        if (invencivel){
            iFrames++;
            if (iFrames > 60){
                invencivel =false;
                iFrames = 0;
            }
        }
    }

    private void atacar() {
        spriteCounter++;
        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <=25){
            spriteNum = 2;

            int worldXAtual = worldX;
            int worldYAtual = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direcao){
                case "cima" -> worldY -= areaAtaque.height;
                case "baixo" -> worldY += areaAtaque.height;
                case "esquerda" -> worldX -= areaAtaque.width;
                case "direita" -> worldX += areaAtaque.width;
            }

            solidArea.width = areaAtaque.width;
            solidArea.height = areaAtaque.height;

            int indexMonstro = gp.cm.checkEntidade(this, gp.monstros);
            aplicarDanoEmMonstro(indexMonstro);

            worldX = worldXAtual;
            worldY = worldYAtual;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            atacando = false;
        }
    }

    private void interagirComNPC(int npcIndex) {

        if (gp.keyHandler.interactPressed) {
            if (npcIndex != 999) {
                gp.gameState = gp.dialogState;
                gp.npcs[npcIndex].falar();
            }else {
                atacando = true;
            }
            gp.keyHandler.interactPressed = false;
        }
    }

    public void contatoMonstro(int indexMonstro){
        if (indexMonstro != 999){
            if (!invencivel){
                vida -=1;
                invencivel = true;
            }
        }
    }

    public void aplicarDanoEmMonstro(int indexMonstro){
        if( indexMonstro != 999){
            if (!gp.monstros[indexMonstro].invencivel) {
                gp.monstros[indexMonstro].vida -=1;
                gp.monstros[indexMonstro].invencivel = true;
                if (gp.monstros[indexMonstro].vida <= 0){
                    gp.monstros[indexMonstro] = null;
                }
            }
        }
    }

    public void pegarObjeto(int index){
        if(index != 999){

        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direcao){
            case "cima" :
                if (!atacando){
                    if (spriteNum == 1) {image = cima1;}
                    if (spriteNum == 2) {image = cima2;}
                }
                if (atacando){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = ataqueCima1;}
                    if (spriteNum == 2) {image = ataqueCima2;}
                }
                break;
            case "baixo":
                if(!atacando){
                    if (spriteNum == 1) {image = baixo1;}
                    if (spriteNum == 2) {image = baixo2;}
                }
                if (atacando){
                    if (spriteNum == 1) {image = ataqueBaixo1;}
                    if (spriteNum == 2) {image = ataqueBaixo2;}
                }
                break;
            case "esquerda":
                if (!atacando){
                    if (spriteNum == 1) {image = esquerda1;}
                    if (spriteNum == 2) {image = esquerda2;}
                }
                if (atacando){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = ataqueEsquerda1;}
                    if (spriteNum == 2) {image = ataqueEsquerda2;}
                }
                break;
            case "direita":
                if (!atacando){
                    if (spriteNum == 1) {image = direita1;}
                    if (spriteNum == 2) {image = direita2;}
                }
                if (atacando){
                    if (spriteNum == 1) {image = ataqueDireita1;}
                    if (spriteNum == 2) {image = ataqueDireita2;}
                }
                break;
        }
        if (invencivel){
            if (iFrames % 6 < 3) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));



    }
}
