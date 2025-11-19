package entity.mob;

import entity.Entidade;
import main.GamePanel;

import java.util.Random;

public class Slime extends Entidade {
    public Slime(GamePanel gp) {
        super(gp);

        nome = "Slime Verde";
        velocidade = 1;
        vidaMaxima = 4;
        vida = vidaMaxima;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        cima1 = setup("/monster/greenslime_down_1.png");
        cima2 = setup("/monster/greenslime_down_2.png");
        baixo1 = setup("/monster/greenslime_down_1.png");
        baixo2 = setup("/monster/greenslime_down_2.png");
        esquerda1 = setup("/monster/greenslime_down_1.png");
        esquerda2 = setup("/monster/greenslime_down_2.png");
        direita1 = setup("/monster/greenslime_down_1.png");
        direita2 = setup("/monster/greenslime_down_2.png");
    }

    public void setAcao(){

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
}
