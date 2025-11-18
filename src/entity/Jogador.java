package entity;

import main.GamePanel;
import input.KeyHandler;

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

    public int chavesNoInventario = 0;
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
        try {
            cima1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            cima2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            baixo1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            baixo2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            esquerda1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            esquerda2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            direita1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            direita2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recursos", e);
        }
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

            String nomeObjeto = gamePanel.objeto[index].nome;
            switch (nomeObjeto){
                case "chave":
                    chavesNoInventario++;
                    gamePanel.objeto[index] = null;
                    gamePanel.hud.exibirMensagem("Você pegou uma chave!");
                    break;
                case "porta":
                    if (chavesNoInventario > 0) {
                        gamePanel.objeto[index] = null;
                        chavesNoInventario--;
                        gamePanel.hud.exibirMensagem("Você abriu uma porta!");
                    }else {
                        gamePanel.hud.exibirMensagem("Você precisa de uma chave.");
                    }
                    break;
                case "botas":
                    velocidade += 1;
                    gamePanel.objeto[index] = null;
                    gamePanel.hud.exibirMensagem("Velocidade aumentada!");
                    break;
                case "bau":
                    gamePanel.hud.jogoFinalizado = true;
                    break;
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
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
