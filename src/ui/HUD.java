package ui;

import main.GamePanel;
import objects.Chave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int contadorMensagem = 0;
    public boolean jogoFinalizado = false;

    public HUD(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void exibirMensagem(String texto){
        message = texto;
        messageOn = true;
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
        int altura = gp.tileSize*5;
        drawJanela(x,y,largura,altura);
    }

    public void drawJanela(int x, int y, int largura, int altura){
        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, largura, altura, 35, 35);

    }

    public int getXParaTextoCentralizado(String texto){
        int tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return gp.larguraTela/2 - tamanhoTexto/2;
    }

}
