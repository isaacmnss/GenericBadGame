package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class Machado extends Entidade {
    public Machado(GamePanel gp) {
        super(gp);

        nome = "Machado do Lenhador";
        tipo = tipo_machado;
        baixo1 = setup("/objects/axe.png",gp.tileSize, gp.tileSize);
        danoDeAtaque = 2;
        descricao = "["+nome+"]\nDá mais dano, mas é mais\ncurto";
        areaAtaque.width = 30;
        areaAtaque.height = 30;
    }
}
