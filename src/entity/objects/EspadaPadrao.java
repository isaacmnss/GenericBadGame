package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class EspadaPadrao extends Entidade {
    public EspadaPadrao(GamePanel gp) {
        super(gp);

        nome = "Espada Normal";
        tipo = tipo_espada;
        baixo1 = setup("/objects/sword_normal.png", gp.tileSize, gp.tileSize);
        danoDeAtaque = 1;
        areaAtaque.width = 36;
        areaAtaque.height = 36;
        descricao = "["+nome+"]\nUma espada velha.\nNão está muito afiada.";
    }
}
