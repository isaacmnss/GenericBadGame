package main;

import objects.Bau;
import objects.Chave;
import objects.Porta;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjetos(){
        gp.objeto[0] = new Chave();
        gp.objeto[0].worldX = 23 * gp.tileSize;
        gp.objeto[0].worldY = 7 * gp.tileSize;

        gp.objeto[1] = new Chave();
        gp.objeto[1].worldX = 23 * gp.tileSize;
        gp.objeto[1].worldY = 40 * gp.tileSize;

        gp.objeto[2] = new Chave();
        gp.objeto[2].worldX = 38 * gp.tileSize;
        gp.objeto[2].worldY = 8 * gp.tileSize;

        gp.objeto[3] = new Porta();
        gp.objeto[3].worldX = 10 * gp.tileSize;
        gp.objeto[3].worldY = 11 * gp.tileSize;

        gp.objeto[4] = new Porta();
        gp.objeto[4].worldX = 8 * gp.tileSize;
        gp.objeto[4].worldY = 28 * gp.tileSize;

        gp.objeto[5] = new Porta();
        gp.objeto[5].worldX = 12 * gp.tileSize;
        gp.objeto[5].worldY = 22 * gp.tileSize;

        gp.objeto[6] = new Bau();
        gp.objeto[6].worldX = 10 * gp.tileSize;
        gp.objeto[6].worldY = 7 * gp.tileSize;
    }
}
