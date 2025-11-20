package main;

import entity.NPC_HomemVelho;
import entity.mob.Slime;
import entity.objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjetos(){
        int i = 0;
        gp.objeto[i] = new Chave(gp);
        gp.objeto[i].worldX = gp.tileSize*25;
        gp.objeto[i].worldY = gp.tileSize*23;
        i++;

        gp.objeto[i] = new EscudoAzul(gp);
        gp.objeto[i].worldX = gp.tileSize*35;
        gp.objeto[i].worldY = gp.tileSize*21;
        i++;

        gp.objeto[i] = new Machado(gp);
        gp.objeto[i].worldX = gp.tileSize*33;
        gp.objeto[i].worldY = gp.tileSize*21;
    }

    public void setNPC(){
        gp.npcs[0] = new NPC_HomemVelho(gp);
        gp.npcs[0].worldX = gp.tileSize*21;
        gp.npcs[0].worldY = gp.tileSize*21;
    }

    public void setMonstros(){

        int i = 0;
        gp.monstros[i] = new Slime(gp);
        gp.monstros[i].worldX = gp.tileSize*23;
        gp.monstros[i].worldY = gp.tileSize*36;
        i++;

        gp.monstros[i] = new Slime(gp);
        gp.monstros[i].worldX = gp.tileSize*23;
        gp.monstros[i].worldY = gp.tileSize*37;
        i++;

        gp.monstros[i] = new Slime(gp);
        gp.monstros[i].worldX = gp.tileSize*24;
        gp.monstros[i].worldY = gp.tileSize*35;
        i++;
        gp.monstros[i] = new Slime(gp);
        gp.monstros[i].worldX = gp.tileSize*34;
        gp.monstros[i].worldY = gp.tileSize*42;
        i++;

        gp.monstros[i] = new Slime(gp);
        gp.monstros[i].worldX = gp.tileSize*38;
        gp.monstros[i].worldY = gp.tileSize*42;
    }
}
