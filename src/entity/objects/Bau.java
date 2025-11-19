package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class Bau extends Entidade {
    public Bau(GamePanel gp){
        super(gp);
        nome = "bau";
        baixo1 = setup("/objects/chest.png");
    }
}
