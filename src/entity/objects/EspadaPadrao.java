package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class EspadaPadrao extends Entidade {
    public EspadaPadrao(GamePanel gp) {
        super(gp);

        nome = "Espada Normal";
        baixo1 = setup("/objects/sword_normal.png", gp.tileSize, gp.tileSize);
        danoDeAtaque = 1;
        descricao = "["+nome+"]\nUma espada velha.\nNão está muito afiada.";
    }
}
