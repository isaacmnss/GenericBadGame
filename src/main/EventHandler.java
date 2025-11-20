package main;

public class EventHandler {
    GamePanel gp;
    EventRect[][] eventRect;

    int eventoPassadoX, eventoPassadoY;
    boolean podeTocarEvento = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxColunasMundo][gp.maxLinhasMundo];

        int coluna = 0;
        int linha = 0;
        while (coluna < gp.maxColunasMundo && linha < gp.maxLinhasMundo){
            eventRect[coluna][linha] = new EventRect();
            eventRect[coluna][linha].x = 23;
            eventRect[coluna][linha].y = 23;
            eventRect[coluna][linha].width = 2;
            eventRect[coluna][linha].height = 2;
            eventRect[coluna][linha].eventRectDefaultX = eventRect[coluna][linha].x;
            eventRect[coluna][linha].EventRectDefaultY = eventRect[coluna][linha].y;

            coluna++;
            if(coluna == gp.maxColunasMundo){
                coluna =0;
                linha++;
            }
        }


    }

    public void checkEvento(){

        int distanciaX = Math.abs(gp.jogador.worldX - eventoPassadoX);
        int distanciaY = Math.abs(gp.jogador.worldY - eventoPassadoY);
        int distanciaDoEvento = Math.max(distanciaX, distanciaY);
        if (distanciaDoEvento > gp.tileSize){
            podeTocarEvento = true;
        }


        if (podeTocarEvento){
            if (hit(27, 16, "direita")) {danoPoco(27,16, gp.dialogState);}
            if (hit(23, 12, "cima")){pocoDeCura(23,12, gp.dialogState);}
        }


    }



    public boolean hit(int colunaEvento, int linhaEvento, String direcaoReq){
        boolean hit = false;

        gp.jogador.solidArea.x = gp.jogador.worldX + gp.jogador.solidArea.x;
        gp.jogador.solidArea.y = gp.jogador.worldY + gp.jogador.solidArea.y;

        eventRect[colunaEvento][linhaEvento].x = colunaEvento*gp.tileSize + eventRect[colunaEvento][linhaEvento].x;
        eventRect[colunaEvento][linhaEvento].y = linhaEvento*gp.tileSize + eventRect[colunaEvento][linhaEvento].y;

        if(gp.jogador.solidArea.intersects(eventRect[colunaEvento][linhaEvento]) && !eventRect[colunaEvento][linhaEvento].eventoFinalizado){
            if (gp.jogador.direcao.contentEquals(direcaoReq) || direcaoReq.contentEquals("qualquer")){
                hit = true;
                eventoPassadoX = gp.jogador.worldX;
                eventoPassadoY = gp.jogador.worldY;
            }
        }

        gp.jogador.solidArea.x = gp.jogador.solidAreaDefaultX;
        gp.jogador.solidArea.y = gp.jogador.solidAreaDefaultY;
        eventRect[colunaEvento][linhaEvento].x =  eventRect[colunaEvento][linhaEvento].eventRectDefaultX;
        eventRect[colunaEvento][linhaEvento].y =  eventRect[colunaEvento][linhaEvento].EventRectDefaultY;

        return hit;
    }

    public void danoPoco(int coluna, int linha, int gameState) {
        gp.gameState = gameState;
        gp.hud.dialogoAtual = "Você caiu no poço.";
        gp.jogador.vida -= 1;
        podeTocarEvento = false;
    }

    public void pocoDeCura(int coluna, int linha, int gameState){
        if(gp.keyHandler.interactPressed){
            gp.gameState = gameState;
            gp.hud.dialogoAtual = "Você bebe a água.\nIsso te enche de determinação.\nVida recuperada";
            gp.jogador.vida = gp.jogador.vidaMaxima;
            gp.assetSetter.setMonstros();
        }
    }
}
