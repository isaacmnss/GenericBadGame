package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Botas extends SuperObjeto{
    GamePanel gp;

    public Botas(GamePanel gp){
        this.gp = gp;

        nome = "botas";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            utilityTool.scaleImage(imagem, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/boots.png", e);
        }
        colisao = true;
    }
}
