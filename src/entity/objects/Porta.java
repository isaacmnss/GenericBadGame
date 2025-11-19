package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class Porta extends Entidade {
    GamePanel gp;

    public Porta(GamePanel gp){
        super(gp);
        nome = "porta";
        baixo1 = setup("/objects/door.png");
        colisao = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
