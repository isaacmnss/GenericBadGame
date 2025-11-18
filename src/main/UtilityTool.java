package main;

import java.awt.*;
import java.awt.image.BufferedImage;


public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage imagemOriginal, int largura, int altura){

        BufferedImage scaledImage = new BufferedImage(largura, altura, imagemOriginal.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(imagemOriginal, 0, 0, largura, altura, null);
        g2.dispose();
        return scaledImage;
    }
}
