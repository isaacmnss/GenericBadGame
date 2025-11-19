package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entidade {
    GamePanel gp;
    public int worldX, worldY;
    public int velocidade;
    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;
    public String direcao = "baixo";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int lockActionCounter = 0;
    String[]  dialogos = new String[20];
    int indexDialogo = 0;
    public int vidaMaxima;
    public int vida;
    public BufferedImage imagem, imagem2, imagem3;
    public String nome;
    public boolean colisao = false;


    public Entidade(GamePanel gp) {
        this.gp = gp;
    }

    public void setAcao(){}
    public void falar() {
        if (dialogos[indexDialogo] == null) {
            indexDialogo = 0;
        }

        gp.hud.dialogoAtual = dialogos[indexDialogo];
        indexDialogo++;

        switch (gp.jogador.direcao){
            case "cima" -> direcao = "baixo";
            case "baixo" -> direcao = "cima";
            case "esquerda" -> direcao = "direita";
            case "direita" -> direcao = "esquerda";
        }
    }

    public void update(){

        setAcao();

        collisionOn = false;
        gp.cm.checkTile(this);
        gp.cm.checkObjeto(this, false);
        gp.cm.checkPlayer(this);

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
    }

    public BufferedImage setup(String pathImagem){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage imagem;
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathImagem)));
            imagem = utilityTool.scaleImage(imagem, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao obter recurso: "+pathImagem, e);
        }
        return imagem;
    }

    public void draw(Graphics2D g2){

        BufferedImage imagem= null;
        int telaX = worldX - gp.jogador.worldX + gp.jogador.screenX;
        int telaY = worldY - gp.jogador.worldY + gp.jogador.screenY;

        if(worldX + gp.tileSize > gp.jogador.worldX - gp.jogador.screenX &&
                worldX - gp.tileSize < gp.jogador.worldX + gp.jogador.screenX &&
                worldY + gp.tileSize > gp.jogador.worldY - gp.jogador.screenY &&
                worldY - gp.tileSize < gp.jogador.worldY + gp.jogador.screenY){
            switch (direcao){
                case "cima" :
                    if (spriteNum == 1){
                        imagem = cima1;
                    }
                    if (spriteNum == 2){
                        imagem = cima2;
                    }
                    break;
                case "baixo":
                    if (spriteNum == 1){
                        imagem = baixo1;
                    }
                    if (spriteNum == 2){
                        imagem = baixo2;
                    }
                    break;
                case "esquerda":
                    if (spriteNum == 1){
                        imagem = esquerda1;
                    }
                    if (spriteNum == 2){
                        imagem = esquerda2;
                    }
                    break;
                case "direita":
                    if (spriteNum == 1){
                        imagem = direita1;
                    }
                    if (spriteNum == 2){
                        imagem = direita2;
                    }
                    break;
            }
            g2.drawImage(imagem, telaX, telaY, gp.tileSize, gp.tileSize, null);

        }

    }
}
