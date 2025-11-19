package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class Coracao extends Entidade {

    public Coracao(GamePanel gp){
        super(gp);
        nome = "Coracao";
        imagem = setup("/objects/heart_full.png", gp.tileSize, gp.tileSize);
        imagem2 = setup("/objects/heart_half.png", gp.tileSize, gp.tileSize);
        imagem3 = setup("/objects/heart_blank.png", gp.tileSize, gp.tileSize);
    }
}
