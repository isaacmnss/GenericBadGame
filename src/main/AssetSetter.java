package main;

import entity.NPC_HomemVelho;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjetos(){

    }

    public void setNPC(){
        gp.npcs [0] = new NPC_HomemVelho(gp);
        gp.npcs [0].worldX = gp.tileSize*21;
        gp.npcs [0].worldY = gp.tileSize*21;
    }
}
