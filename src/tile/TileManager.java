package tile;

import main.GamePanel;
import main.UtilityTool;

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
        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3,"earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
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
                g2.drawImage(tiles[tileNum].image, telaX, telaY, null);

            }

            colunaMundo++;

            if (colunaMundo == gamePanel.maxColunasMundo){
                colunaMundo = 0;
                linhaMundo++;
            }
        }
    }

    public void setup(int index, String nomeImagem, boolean colisao){
        UtilityTool utilityTool = new UtilityTool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/"+ nomeImagem +".png")));
            tiles[index].image = utilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].colisao = colisao;
        }catch (IOException e){
            throw new RuntimeException("Erro ao buscar recurso: "+ nomeImagem, e);
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
