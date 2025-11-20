package ui;

import entity.Entidade;
import main.GamePanel;
import entity.objects.Coracao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HUD {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    ArrayList<String> mensagem = new ArrayList<>();
    ArrayList<Integer> contadorMensagem = new ArrayList<>();
    public String dialogoAtual;
    public int comando = 0;
    public int colunaSlot = 0;
    public int linhaSlot = 0;

    public HUD(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        Entidade coracao = new Coracao(gp);
        heart_full = coracao.imagem;
        heart_half = coracao.imagem2;
        heart_blank = coracao.imagem3;

    }

    public void addMensagem(String texto){
        mensagem.add(texto);
        contadorMensagem.add(0);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState){
            drawTelaInicial();
        }

        if(gp.gameState == gp.playState){
            drawVidaJogador();
            drawMensagem();
        }
        if (gp.gameState == gp.pauseState){
            drawVidaJogador();
            drawTelaDePause();
        }
        if (gp.gameState == gp.dialogState){
            drawVidaJogador();
            drawDialogo();
        }
        if (gp.gameState == gp.characterState){
            drawTelaStatus();
            drawInventario();
        }
    }

    public void drawVidaJogador(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;

        int i = 0;

        while (i< gp.jogador.vidaMaxima/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while (i< gp.jogador.vida){
            g2.drawImage(heart_half, x, y,null);
            i++;
            if (i <  gp.jogador.vida){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x+= gp.tileSize;
        }
    }

    public void drawMensagem(){
        int xMensagem = gp.tileSize;
        int yMensagem = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 26));

        for (int i = 0; i < mensagem.size(); i++) {
            if (mensagem.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(mensagem.get(i), xMensagem+2, yMensagem+2 );
                g2.setColor(Color.white);
                g2.drawString(mensagem.get(i), xMensagem, yMensagem);
                int contador = contadorMensagem.get(i) +1;
                contadorMensagem.set(i, contador);
                yMensagem += 50;

                if (contadorMensagem.get(i) > 180){
                    mensagem.remove(i);
                    contadorMensagem.remove(i);
                }
            }
        }

    }

    private void drawTelaInicial() {
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.larguraTela, gp.alturaTela);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String texto = "Jogo (sem nome)";
        int x = getXParaTextoCentralizado(texto);
        int y = gp.tileSize*3;

        g2.setColor(Color.gray);
        g2.drawString(texto, x+5, y+5);

        g2.setColor(Color.white);
        g2.drawString(texto, x, y);

        x = gp.larguraTela/2 -(gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.jogador.baixo1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        texto = "Novo jogo";
        x = getXParaTextoCentralizado(texto);
        y += gp.tileSize*3.5;
        g2.drawString(texto, x, y);
        if (comando == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        texto = "Carregar jogo";
        x = getXParaTextoCentralizado(texto);
        y += gp.tileSize;
        g2.drawString(texto, x, y);
        if (comando == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        texto = "Sair";
        x = getXParaTextoCentralizado(texto);
        y += gp.tileSize;
        g2.drawString(texto, x, y);
        if (comando == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }


    }

    public void drawTelaDePause(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String texto = "PAUSADO";
        int x = getXParaTextoCentralizado(texto);
        int y = gp.alturaTela / 2;

        g2.drawString(texto, x, y);
    }

    public void drawDialogo(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int largura = gp.larguraTela - (gp.tileSize*4);
        int altura = gp.tileSize*4;
        drawJanela(x,y,largura,altura);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x+=gp.tileSize;
        y+=gp.tileSize;

        for(String linha : dialogoAtual.split("\n")){
            g2.drawString(linha, x, y);
            y+=40;
        }
    }

    private void drawTelaStatus() {
        final int molduraX = gp.tileSize;
        final int molduraY = gp. tileSize;
        final int laguraMoldura = gp.tileSize * 5;
        final int alturaMoldura = gp.tileSize * 10;

        drawJanela(molduraX, molduraY, laguraMoldura, alturaMoldura);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));

        int textoX = molduraX + 20;
        int textoY = molduraY + gp.tileSize;
        final int espacamentoLinha = 35;

        g2.drawString("Nível", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Vida", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Força", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Destreza", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Ataque", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Defesa", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Exp.", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Próximo Nível", textoX, textoY);
        textoY += espacamentoLinha;
        g2.drawString("Moedas", textoX, textoY);
        textoY += espacamentoLinha +20;
        g2.drawString("Arma", textoX, textoY);
        textoY += espacamentoLinha +15;
        g2.drawString("Escudo", textoX, textoY);

        int tailX = (molduraX + laguraMoldura) -30;

        textoY = molduraY + gp.tileSize;
        String valor;

        valor = String.valueOf(gp.jogador.nivel);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.vida+"/"+gp.jogador.vidaMaxima);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.forca);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.destreza);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.ataque);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.defesa);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.exp);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.expParaUpar);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        valor = String.valueOf(gp.jogador.moedas);
        textoX = getXParaTextoAlinhado(valor, tailX);
        g2.drawString(valor, textoX, textoY);
        textoY += espacamentoLinha;

        g2.drawImage(gp.jogador.armaAtual.baixo1, tailX - gp.tileSize, textoY-14, null);
        textoY += gp.tileSize;

        g2.drawImage(gp.jogador.escudoAtual.baixo1, tailX - gp.tileSize, textoY-14, null);

    }

    private void drawInventario() {

        int molduraX = gp.tileSize *9;
        int molduraY = gp.tileSize;
        int larguraMoldura = gp.tileSize*6;
        int alturaMoldura = gp.tileSize*5;
        drawJanela(molduraX,molduraY,larguraMoldura,alturaMoldura);

        final int xSlotInicial = molduraX + 20;
        final int ySlotInicial = molduraY + 20;
        int xSlot = xSlotInicial;
        int ySlot = ySlotInicial;
        int tamanhoSlot = gp.tileSize + 3;

        for (int i = 0; i < gp.jogador.inventario.size(); i++) {
            if (gp.jogador.inventario.get(i) == gp.jogador.armaAtual || gp.jogador.inventario.get(i) == gp.jogador.escudoAtual){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(xSlot, ySlot, gp.tileSize, gp.tileSize, 10, 10);

            }

            g2.drawImage(gp.jogador.inventario.get(i).baixo1, xSlot, ySlot, null);
            xSlot += tamanhoSlot;

            if (i == 4 || i == 9 || i == 14){
                xSlot = xSlotInicial;
                ySlot += tamanhoSlot;
            }
        }

        int cursorX = xSlotInicial + (gp.tileSize * colunaSlot);
        int cursorY = ySlotInicial + (gp.tileSize * linhaSlot);
        int larguraCursor = gp.tileSize;
        int alturaCursor = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, larguraCursor, alturaCursor, 10, 10);

        int molduraDescricaoX = molduraX;
        int molduraDescricaoY = molduraY + alturaMoldura;
        int larguraMolduraDescricao = larguraMoldura;
        int alturaMolduraDescricao = gp.tileSize*3;


        int xTexto = molduraDescricaoX + 20;
        int yTexto = molduraDescricaoY + gp.tileSize;


        g2.setFont(g2.getFont().deriveFont(22F));

        int indexItem = getIndexItemNoSlot();

        if (indexItem < gp.jogador.inventario.size()){
            drawJanela(molduraDescricaoX, molduraDescricaoY, larguraMolduraDescricao, alturaMolduraDescricao);

            for(String linha : gp.jogador.inventario.get(indexItem).descricao.split("\n")){
                g2.drawString(linha, xTexto, yTexto);
                yTexto += 32;
            }


        }
    }

    public int getIndexItemNoSlot(){
        return colunaSlot + (linhaSlot*5);
    }

    public void drawJanela(int x, int y, int largura, int altura){
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, largura, altura, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, largura-10, altura-10,25, 25);

    }


    public int getXParaTextoCentralizado(String texto){
        int tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return gp.larguraTela/2 - tamanhoTexto/2;
    }

    public int getXParaTextoAlinhado(String texto, int tailX){
        int tamanhoTexto = (int) g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        return tailX - tamanhoTexto;
    }

}
