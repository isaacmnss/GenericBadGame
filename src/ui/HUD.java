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


        }
        if (gp.gameState == gp.pauseState){
            drawTelaDePause();
        }
    }

    public void drawTelaDePause(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String texto = "PAUSADO";
        int x = getXParaTextoCentralizado(texto);
        int y = gp.alturaTela / 2;

        g2.drawString(texto, x, y);
    }
    public int getXParaTextoCentralizado(String texto){
        int tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return gp.larguraTela/2 - tamanhoTexto/2;
    }

}
