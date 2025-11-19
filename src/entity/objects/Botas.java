package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class Botas extends Entidade {
    public Botas(GamePanel gp){
       super(gp);
        nome = "botas";
        baixo1 = setup("/objects/boots.png");
        colisao = true;
    }
}
