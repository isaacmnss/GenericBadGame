package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class EscudoAzul extends Entidade {
    public EscudoAzul(GamePanel gp) {
        super(gp);

        nome ="Escudo Azul";
        tipo = tipo_escudo;
        baixo1 = setup("/objects/shield_blue.png", gp.tileSize, gp.tileSize);
        valorDefesa = 2;
        descricao = "["+nome+"]\nUm escudo mais forte,\n feito de madeira";
    }
}
