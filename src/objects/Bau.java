package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Bau extends SuperObjeto{

    public Bau(){
        nome = "bau";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/chest.png", e);
        }
    }
}
