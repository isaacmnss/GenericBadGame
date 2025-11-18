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

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }


    public void getImagem(){
        cima1 = setup("/NPC/oldman_up_1.png");
        cima2 = setup("/NPC/oldman_up_2.png");
        baixo1 = setup("/NPC/oldman_down_1.png");
        baixo2 = setup("/NPC/oldman_down_2.png");
        esquerda1 = setup("/NPC/oldman_left_1.png");
        esquerda2 = setup("/NPC/oldman_left_2.png");
        direita1 = setup("/NPC/oldman_right_1.png");
        direita2 = setup("/NPC/oldman_right_2.png");
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
            lockActionCounter =0;
        }

    }
}
