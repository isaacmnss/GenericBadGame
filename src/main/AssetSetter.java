package main;

import entity.NPC_HomemVelho;
import entity.objects.Porta;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjetos(){
        gp.objeto[0] = new Porta(gp);
        gp.objeto[0].worldX = gp.tileSize*21;
        gp.objeto[0].worldY = gp.tileSize*22;

        gp.objeto[1] = new Porta(gp);
        gp.objeto[1].worldX = gp.tileSize*23;
        gp.objeto[1].worldY = gp.tileSize*25;

    }

    public void setNPC(){
        gp.npcs [0] = new NPC_HomemVelho(gp);
        gp.npcs [0].worldX = gp.tileSize*21;
        gp.npcs [0].worldY = gp.tileSize*21;

        gp.npcs [1] = new NPC_HomemVelho(gp);
        gp.npcs [1].worldX = gp.tileSize*11;
        gp.npcs [1].worldY = gp.tileSize*21;

        gp.npcs [2] = new NPC_HomemVelho(gp);
        gp.npcs [2].worldX = gp.tileSize*31;
        gp.npcs [2].worldY = gp.tileSize*21;

    }
}
