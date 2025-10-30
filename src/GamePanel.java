import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Configurações de tela
    final int originalTileSize = 16; // quadros 16x16
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 = tamanho real dos tiles
    final int maxTilesVertical = 16;
    final int maxTilesHorizontal = 12;
    final int larguraTela = tileSize * maxTilesVertical; //768px
    final int alturaTela = tileSize * maxTilesHorizontal; //576px

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(larguraTela,alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread =  new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }
}
