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
}
