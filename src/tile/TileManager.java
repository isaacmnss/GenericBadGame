package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxColunasMundo][gamePanel.maxLinhasMundo];
        getTileImage();
        loadMap("/maps/world1.txt");
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tiles[1].colisao = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tiles[2].colisao = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tiles[4].colisao = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));

        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso", e);
        }
    }

    public void draw(Graphics2D g2){
        int colunaMundo = 0;
        int linhaMundo = 0;

        while (colunaMundo < gamePanel.maxColunasMundo && linhaMundo < gamePanel.maxLinhasMundo){

            int tileNum = mapTileNum[colunaMundo][linhaMundo];

            int worldX = colunaMundo * gamePanel.tileSize;
            int worldY = linhaMundo * gamePanel.tileSize;

            int telaX = worldX - gamePanel.jogador.worldX + gamePanel.jogador.screenX;
            int telaY = worldY - gamePanel.jogador.worldY + gamePanel.jogador.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.jogador.worldX - gamePanel.jogador.screenX &&
               worldX - gamePanel.tileSize < gamePanel.jogador.worldX + gamePanel.jogador.screenX &&
               worldY + gamePanel.tileSize > gamePanel.jogador.worldY - gamePanel.jogador.screenY &&
               worldY - gamePanel.tileSize < gamePanel.jogador.worldY + gamePanel.jogador.screenY){
                g2.drawImage(tiles[tileNum].image, telaX, telaY, gamePanel.tileSize, gamePanel.tileSize, null);

            }


            colunaMundo++;

            if (colunaMundo == gamePanel.maxColunasMundo){
                colunaMundo = 0;
                linhaMundo++;
            }
        }
    }

    public void loadMap(String pathArquivo){
        try{
            InputStream inputStream = getClass().getResourceAsStream(pathArquivo);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int linha = 0;

            while (col < gamePanel.maxColunasMundo && linha < gamePanel.maxLinhasMundo){
                String line  = bufferedReader.readLine();

                while (col < gamePanel.maxColunasMundo){
                    String[] numeros = line.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    mapTileNum [col] [linha] = num;
                    col++;
                }
                if (col == gamePanel.maxColunasMundo){
                    col = 0;
                    linha++;
                }

            }
            bufferedReader.close();
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: mapa ", e);
        }
    }
}
