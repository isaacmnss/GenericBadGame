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
        tiles = new Tile[50];
        mapTileNum = new int[gamePanel.maxColunasMundo][gamePanel.maxLinhasMundo];
        getTileImage();
        loadMap("/maps/worldv2.txt");
    }

    public void getTileImage(){
        // Placholder
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);
        setup(10, "grass00", false);

        setup(11, "grass01", false);
        
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);

        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);

        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);

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
