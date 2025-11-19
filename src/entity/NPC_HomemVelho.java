package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_HomemVelho extends Entidade{

    public NPC_HomemVelho(GamePanel gp){
        super(gp);
        direcao = "baixo";
        velocidade = 1;
        getImagem();
        setDialogo();

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }


    public void getImagem(){
        cima1 = setup("/NPC/oldman_up_1.png", gp.tileSize, gp.tileSize);
        cima2 = setup("/NPC/oldman_up_2.png", gp.tileSize, gp.tileSize);
        baixo1 = setup("/NPC/oldman_down_1.png", gp.tileSize, gp.tileSize);
        baixo2 = setup("/NPC/oldman_down_2.png", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/NPC/oldman_left_1.png", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/NPC/oldman_left_2.png", gp.tileSize, gp.tileSize);
        direita1 = setup("/NPC/oldman_right_1.png", gp.tileSize, gp.tileSize);
        direita2 = setup("/NPC/oldman_right_2.png", gp.tileSize, gp.tileSize);
    }

    public void setDialogo(){
        dialogos [0] = "Olá, jovem!";
        dialogos [1] = "Você vem à esta ilha em busca\nde seus tesouros?";
        dialogos [2] = "Eu costumava ser um aventureiro como\nvocê, mas aí eu levei uma flechada no\njoelho";
        dialogos [3] = "Aqui no sul dessa aldeia há um grupo\nde slimes que incomoda bastante\npoderia dar um jeito nisso?";
        dialogos [4] = "Te desejo boa sorte";
    }

    @Override
    public void setAcao() {

        lockActionCounter++;
        if (lockActionCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25){
                direcao = "cima";
            }
            if (i> 25 && i<= 50){
                direcao = "baixo";
            }
            if(i> 50 && i <=75){
                direcao = "esquerda";
            }
            if (i >75){
                direcao = "direita";
            }
            lockActionCounter = 0;
        }

    }

    public void falar(){
        super.falar();
    }
}
