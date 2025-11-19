package main;

import entity.NPC_HomemVelho;
import entity.mob.Slime;
import entity.objects.Porta;

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

    public void setMonstros(){
        gp.monstros[0] = new Slime(gp);
        gp.monstros[0].worldX = gp.tileSize*23;
        gp.monstros[0].worldY = gp.tileSize*36;

        gp.monstros[1] = new Slime(gp);
        gp.monstros[1].worldX = gp.tileSize*23;
        gp.monstros[1].worldY = gp.tileSize*37;
    }
}
