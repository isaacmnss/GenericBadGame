package entity.mob;

import entity.Entidade;
import main.GamePanel;

import java.util.Random;

public class Slime extends Entidade {

    GamePanel gp;
    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;

        nome = "Slime Verde";
        tipo = 2;
        velocidade = 1;
        vidaMaxima = 4;
        vida = vidaMaxima;
        ataque = 3;
        defesa = 0;
        exp =2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        cima1 = setup("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        cima2 = setup("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
        baixo1 = setup("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        baixo2 = setup("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
        esquerda1 = setup("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        esquerda2 = setup("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
        direita1 = setup("/monster/greenslime_down_1.png", gp.tileSize, gp.tileSize);
        direita2 = setup("/monster/greenslime_down_2.png", gp.tileSize, gp.tileSize);
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

    @Override
    public void damageReaction() {
        lockActionCounter = 0;
        direcao = gp.jogador.direcao;
    }
}
