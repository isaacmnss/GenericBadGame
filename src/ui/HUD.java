package ui;

import main.GamePanel;
import objects.Chave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public String dialogoAtual;

    public HUD(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            //placehold.
        }
        if (gp.gameState == gp.pauseState){
            drawTelaDePause();
        }
        if (gp.gameState == gp.dialogState){
            drawDialogo();
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
