package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Botas extends SuperObjeto{
    public Botas(){
        nome = "botas";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/boots.png", e);
        }
        colisao = true;
    }
}
