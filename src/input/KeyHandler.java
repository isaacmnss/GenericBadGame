package input;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, talkPressed;
    public boolean checkTempoDeRender = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState){

            if(code == KeyEvent.VK_W){
                gp.hud.comando--;
                if(gp.hud.comando < 0){
                    gp.hud.comando = 2;
                }
            }

            if(code == KeyEvent.VK_S){
                gp.hud.comando++;
                if(gp.hud.comando > 2){
                    gp.hud.comando = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                switch (gp.hud.comando){
                    case 0 -> gp.gameState = gp.playState;
                    case 1 -> System.out.println("ainda não implementado");
                    case 2 -> System.exit(0);

                }
            }
        }

        if (gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_T){
                checkTempoDeRender = !checkTempoDeRender;
            }
            if (code == KeyEvent.VK_F){
                talkPressed = true;
            }
        }

        else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }

        }
        else if (gp.gameState == gp.dialogState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
