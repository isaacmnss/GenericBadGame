package ui;

import main.GamePanel;
import objects.Chave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage imagemChave;
    public boolean messageOn = false;
    public String message = "";
    int contadorMensagem = 0;
    public boolean jogoFinalizado = false;

    public HUD(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Chave chave = new Chave(gp);
        imagemChave = chave.imagem;

    }

    public void exibirMensagem(String texto){
        message = texto;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(jogoFinalizado){

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String texto;
            int tamanhoTexto;
            int x;
            int y;

            texto  = "Você encontrou o tesouro!";
            tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = gp.larguraTela/2 - tamanhoTexto/2;
            y = gp.alturaTela/2 - (gp.tileSize*3);
            g2.drawString(texto, x, y);

            g2.setFont(arial_80B);
            texto  = "Parabéns!";
            tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = gp.larguraTela/2 - tamanhoTexto/2;
            y = gp.alturaTela/2 + (gp.tileSize*2);
            g2.drawString(texto, x, y);

            gp.gameThread = null;

        }else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(imagemChave, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.jogador.chavesNoInventario, 74,65);

            if (messageOn){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                contadorMensagem++;

                if (contadorMensagem > 120){
                    contadorMensagem = 0;
                    messageOn = false;
                }
            }
        }
    }
}
