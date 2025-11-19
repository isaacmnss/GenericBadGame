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

    public BufferedImage imagem, imagem2, imagem3;
    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;
    public BufferedImage ataqueCima1, ataqueCima2, ataqueBaixo1, ataqueBaixo2, ataqueEsquerda1, ataqueEsquerda2,
    ataqueDireita1, ataqueDireita2;


    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle areaAtaque = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    String[]  dialogos = new String[20];

    public int worldX, worldY;
    public int velocidade;
    public String direcao = "baixo";
    int indexDialogo = 0;
    public boolean invencivel = false;
    boolean atacando = false;
    public boolean vivo = true;
    public boolean morrendo = false;
    public boolean barraDeVidaAtivada = false;

    public int iFrames = 0;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int lockActionCounter = 0;
    int deathCounter = 0;
    int barraDeVidaCounter = 0;


    public int vidaMaxima;
    public int vida;
    public String nome;
    public boolean colisao = false;
    public int tipo;


    public Entidade(GamePanel gp) {
        this.gp = gp;
    }

    public void setAcao(){}
    public void damageReaction(){}
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
        gp.cm.checkEntidade(this, gp.npcs);
        gp.cm.checkEntidade(this, gp.monstros);
        boolean  contatoJogador = gp.cm.checkPlayer(this);

        if (this.tipo == 2 && contatoJogador){
            if (!gp.jogador.invencivel){
                gp.jogador.vida -=1;
                gp.jogador.invencivel = true;
            }
        }

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

        if (invencivel){
            iFrames++;
            if (iFrames > 40){
                invencivel = false;
                iFrames = 0;
            }
        }
    }

    private void animacaoMorte(Graphics2D g2) {
        deathCounter++;

        int i = 5;

        if (deathCounter <= i) {mudarOpacidade(g2,0f);}
        if (deathCounter > i && deathCounter <= i*2) {mudarOpacidade(g2,1f);}
        if (deathCounter > i*2 && deathCounter <= i*3) {mudarOpacidade(g2,0f);}
        if (deathCounter > i*3 && deathCounter <= i*4) {mudarOpacidade(g2,1f);}
        if (deathCounter > i*4 && deathCounter <= i*5) {mudarOpacidade(g2,0f);}
        if (deathCounter > i*5 && deathCounter <= i*6) {mudarOpacidade(g2,1f);}
        if (deathCounter > i*6 && deathCounter <= i*7) {mudarOpacidade(g2,0f);}
        if (deathCounter > i*7 && deathCounter <= i*8) {mudarOpacidade(g2,1f);}
        if (deathCounter> i*8){
            morrendo = false;
            vivo = false;
        }
    }

    public void mudarOpacidade(Graphics2D g2, float valorAlpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, valorAlpha));
    }


    public BufferedImage setup(String pathImagem, int largura, int altura){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage imagem;
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathImagem)));
            imagem = utilityTool.scaleImage(imagem, largura, altura);
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
                    if (spriteNum == 1) {imagem = cima1;}
                    if (spriteNum == 2) {imagem = cima2;}
                    break;
                case "baixo":
                    if (spriteNum == 1) {imagem = baixo1;}
                    if (spriteNum == 2) {imagem = baixo2;}
                    break;
                case "esquerda":
                    if (spriteNum == 1) {imagem = esquerda1;}
                    if (spriteNum == 2) {imagem = esquerda2;}
                    break;
                case "direita":
                    if (spriteNum == 1) {imagem = direita1;}
                    if (spriteNum == 2) {imagem = direita2;}
                    break;
            }

            if (tipo == 2 && barraDeVidaAtivada){

                double escalaVida = (double)gp.tileSize/vidaMaxima;
                double valorVidaBarra = escalaVida*vida;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(telaX-1 , telaY-16, gp.tileSize+2, 12 );
                g2.setColor(new Color(255,0,50));
                g2.fillRect(telaX, telaY - 15, (int)valorVidaBarra, 10);
                barraDeVidaCounter ++;

                if (barraDeVidaCounter > 600){
                    barraDeVidaCounter = 0;
                    barraDeVidaAtivada = false;
                }
            }



            if (invencivel){
                if (iFrames % 6 < 3) {
                    barraDeVidaAtivada = true;
                    barraDeVidaCounter = 0;
                    mudarOpacidade(g2,0.3f);
                }
            }

            if (morrendo){
                animacaoMorte(g2);
            }
            g2.drawImage(imagem, telaX, telaY, gp.tileSize, gp.tileSize, null);
            mudarOpacidade(g2, 1f);

        }

    }
}
