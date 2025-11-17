package collision;

import entity.Entidade;
import main.GamePanel;

public class CollisionManager {
    GamePanel gp;

    public CollisionManager(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entidade entidade){
        int entityLeftWorldX = entidade.worldX + entidade.solidArea.x;
        int entityRightWorldX = entidade.worldX + entidade.solidArea.x + entidade.solidArea.width;
        int entityTopWorldY = entidade.worldY + entidade.solidArea.y;
        int entityBottomWorldY = entidade.worldY + entidade.solidArea.y + entidade.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entidade.direcao){
            case "cima":
                entityTopRow = (entityTopWorldY - entidade.velocidade)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tiles[tileNum1].colisao || gp.tileManager.tiles[tileNum2].colisao){
                    entidade.collisionOn = true;
                }
                break;
            case "baixo":
                entityBottomRow = (entityBottomWorldY + entidade.velocidade)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].colisao || gp.tileManager.tiles[tileNum2].colisao){
                    entidade.collisionOn = true;
                }
                break;
            case "esquerda":
                entityLeftCol = (entityLeftWorldX - entidade.velocidade)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].colisao || gp.tileManager.tiles[tileNum2].colisao){
                    entidade.collisionOn = true;
                }
                break;
            case "direita":
                entityRightCol = (entityRightWorldX + entidade.velocidade)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].colisao || gp.tileManager.tiles[tileNum2].colisao){
                    entidade.collisionOn = true;
                }
                break;
        }
    }

    public int checkObjeto(Entidade entidade, boolean jogador){
        int index = 999;


        for (int i = 0; i < gp.objeto.length; i++) {
            if(gp.objeto[i] != null){
                // Obter área sólida da entidade
                entidade.solidArea.x = entidade.worldX + entidade.solidArea.x;
                entidade.solidArea.y = entidade.worldY + entidade.solidArea.y;

                gp.objeto[i].solidArea.x = gp.objeto[i].worldX + gp.objeto[i].solidArea.x;
                gp.objeto[i].solidArea.y = gp.objeto[i].worldY + gp.objeto[i].solidArea.y;

                switch (entidade.direcao){
                    case "cima":
                        entidade.solidArea.y -= entidade.velocidade;
                        if(entidade.solidArea.intersects(gp.objeto[i].solidArea)){
                            if(gp.objeto[i].colisao){
                                entidade.collisionOn = true;
                            }
                            if (jogador){
                                index = i;
                            }
                        }
                        break;
                    case "baixo":
                        entidade.solidArea.y += entidade.velocidade;
                        if(entidade.solidArea.intersects(gp.objeto[i].solidArea)){
                            if(gp.objeto[i].colisao){
                                entidade.collisionOn = true;
                            }
                            if (jogador){
                                index = i;
                            }
                        }
                        break;
                    case "esquerda":
                        entidade.solidArea.x -= entidade.velocidade;
                        if(entidade.solidArea.intersects(gp.objeto[i].solidArea)){
                            if(gp.objeto[i].colisao){
                                entidade.collisionOn = true;
                            }
                            if (jogador){
                                index = i;
                            }
                        }
                        break;
                    case "direita":
                        entidade.solidArea.x += entidade.velocidade;
                        if(entidade.solidArea.intersects(gp.objeto[i].solidArea)){
                            if(gp.objeto[i].colisao){
                                entidade.collisionOn = true;
                            }
                            if (jogador){
                                index = i;
                            }
                        }
                        break;
                }
                entidade.solidArea.x = entidade.solidAreaDefaultX;
                entidade.solidArea.y = entidade. solidAreaDefaultY;
                gp.objeto[i].solidArea.x = gp.objeto[i].solidAreaDefaultX;
                gp.objeto[i].solidArea.y = gp.objeto[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
