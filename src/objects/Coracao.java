package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Coracao extends SuperObjeto{
    GamePanel gp;

    public Coracao(GamePanel gp){
        this.gp = gp;
        nome = "Coracao";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png")));
            imagem2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
            imagem3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png")));
            imagem = utilityTool.scaleImage(imagem, gp.tileSize, gp.tileSize);
            imagem2 = utilityTool.scaleImage(imagem2, gp.tileSize, gp.tileSize);
            imagem3 = utilityTool.scaleImage(imagem3, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/heart.png", e);
        }
    }
}
