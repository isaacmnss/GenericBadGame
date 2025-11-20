package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class EscudoMadeira extends Entidade {
    public EscudoMadeira(GamePanel gp) {
        super(gp);

        nome ="Escudo de madeira";
        tipo = tipo_escudo;
        baixo1 = setup("/objects/shield_wood.png", gp.tileSize, gp.tileSize);
        valorDefesa = 1;
        descricao = "["+nome+"]\nUm escudo simples, feito\nde madeira";
    }
}
