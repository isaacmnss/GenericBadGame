package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Porta extends SuperObjeto{
    GamePanel gp;

    public Porta(GamePanel gp){
        this.gp = gp;

        nome = "porta";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            utilityTool.scaleImage(imagem, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/door.png", e);
        }
        colisao = true;
    }
}
