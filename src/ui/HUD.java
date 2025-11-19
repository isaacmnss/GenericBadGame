package ui;

import entity.Entidade;
import main.GamePanel;
import entity.objects.Coracao;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public String dialogoAtual;
    public int comando = 0;

    public HUD(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        Entidade coracao = new Coracao(gp);
        heart_full = coracao.imagem;
        heart_half = coracao.imagem2;
        heart_blank = coracao.imagem3;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState){
            drawTelaInicial();
        }

        if(gp.gameState == gp.playState){
            drawVidaJogador();
        }
        if (gp.gameState == gp.pauseState){
            drawVidaJogador();
            drawTelaDePause();
        }
        if (gp.gameState == gp.dialogState){
            drawVidaJogador();
            drawDialogo();
        }
    }

    public void drawVidaJogador(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;

        int i = 0;

        while (i< gp.jogador.vidaMaxima/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while (i< gp.jogador.vida){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if (i <  gp.jogador.vida){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x+= gp.tileSize;
        }
    }

    private void drawTelaInicial() {
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.larguraTela, gp.alturaTela);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String texto = "Jogo (sem nome)";
        int x = getXParaTextoCentralizado(texto);
        int y = gp.tileSize*3;

        g2.setColor(Color.gray);
        g2.drawString(texto, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(texto, x, y);

        x = gp.larguraTela/2 -(gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.jogador.baixo1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        texto = "Novo jogo";
        x = getXParaTextoCentralizado(texto);
        y += gp.tileSize*3.5;
        g2.drawString(texto, x, y);
        if (comando == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        texto = "Carregar jogo";
        x = getXParaTextoCentralizado(texto);
        y += gp.tileSize;
        g2.drawString(texto, x, y);
        if (comando == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        texto = "Sair";
        x = getXParaTextoCentralizado(texto);
        y += gp.tileSize;
        g2.drawString(texto, x, y);
        if (comando == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }


    }

    public void drawTelaDePause(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String texto = "PAUSADO";
        int x = getXParaTextoCentralizado(texto);
        int y = gp.alturaTela / 2;

        g2.drawString(texto, x, y);
    }

    public void drawDialogo(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int largura = gp.larguraTela - (gp.tileSize*4);
        int altura = gp.tileSize*4;
        drawJanela(x,y,largura,altura);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x+=gp.tileSize;
        y+=gp.tileSize;

        for(String linha : dialogoAtual.split("\n")){
            g2.drawString(linha, x, y);
            y+=40;
        }
    }

    public void drawJanela(int x, int y, int largura, int altura){
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, largura, altura, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, largura-10, altura-10,25, 25);

    }


    public int getXParaTextoCentralizado(String texto){
        int tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return gp.larguraTela/2 - tamanhoTexto/2;
    }

}
