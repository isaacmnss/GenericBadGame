package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chave extends SuperObjeto{
    GamePanel gp;

    public Chave(GamePanel gp){
        this.gp = gp;
        nome = "chave";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            utilityTool.scaleImage(imagem, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/key.png", e);
        }
    }
}
