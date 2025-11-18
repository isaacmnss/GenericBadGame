package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Bau extends SuperObjeto{
    GamePanel gp;

    public Bau(GamePanel gp){
        this.gp = gp;
        nome = "bau";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            utilityTool.scaleImage(imagem, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/chest.png", e);
        }
    }
}
