package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Porta extends SuperObjeto{

    public Porta(){
        nome = "porta";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/door.png", e);
        }
        colisao = true;
    }
}
