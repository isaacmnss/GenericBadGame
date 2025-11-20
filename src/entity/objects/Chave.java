package entity.objects;

import entity.Entidade;
import main.GamePanel;

public class Chave extends Entidade {
    public Chave(GamePanel gp){
        super(gp);
        nome = "chave";
        baixo1 = setup("/objects/key.png", gp.tileSize, gp.tileSize);
        descricao = "["+nome+"]\nServe para abrir portas";
    }
}
