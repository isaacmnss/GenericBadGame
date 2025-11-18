package objects;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjeto {

    public BufferedImage imagem;
    public String nome;
    public boolean colisao = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool utilityTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){

        int telaX = worldX - gp.jogador.worldX + gp.jogador.screenX;
        int telaY = worldY - gp.jogador.worldY + gp.jogador.screenY;

        if(worldX + gp.tileSize > gp.jogador.worldX - gp.jogador.screenX &&
                worldX - gp.tileSize < gp.jogador.worldX + gp.jogador.screenX &&
                worldY + gp.tileSize > gp.jogador.worldY - gp.jogador.screenY &&
                worldY - gp.tileSize < gp.jogador.worldY + gp.jogador.screenY){
            g2.drawImage(imagem, telaX, telaY, gp.tileSize, gp.tileSize, null);

        }

    }
}
