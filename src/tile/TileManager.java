package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxTilesVertical][gamePanel.maxTilesHorizontal];
        getTileImage();
        loadMap("/maps/mapa1.txt");
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int linha = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxTilesVertical && linha < gamePanel.maxTilesHorizontal){

            int tileNum = mapTileNum [col] [linha];

            g2.drawImage(tiles[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x+= gamePanel.tileSize;

            if (col == gamePanel.maxTilesVertical){
                col = 0;
                x = 0;
                linha++;
                y += gamePanel.tileSize;
            }
        }
    }

    public void loadMap(String pathArquivo){
        try{
            InputStream inputStream = getClass().getResourceAsStream(pathArquivo);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int linha = 0;

            while (col < gamePanel.maxTilesVertical && linha < gamePanel.maxTilesHorizontal){
                String line  = bufferedReader.readLine();

                while (col < gamePanel.maxTilesVertical){
                    String[] numeros = line.split(" ");
                    int num = Integer.parseInt(numeros[col]);
                    mapTileNum [col] [linha] = num;
                    col++;
                }
                if (col == gamePanel.maxTilesVertical){
                    col = 0;
                    linha++;
                }

            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
