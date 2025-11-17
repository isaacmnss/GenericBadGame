package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Chave extends SuperObjeto{

    public Chave(){
        nome = "chave";
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        }catch (IOException e){
            throw new RuntimeException("Falha ao carregar recurso: /objects/key.png", e);
        }
    }
}
