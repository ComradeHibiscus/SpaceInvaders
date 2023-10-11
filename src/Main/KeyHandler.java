package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyHandler implements KeyListener {

    Panel gp;
    public boolean right, left, shoot;

    public KeyHandler (Panel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) throws RuntimeException{
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A)
            left = true;
        if (code == KeyEvent.VK_D)
            right = true;
        if (code == KeyEvent.VK_W)
            shoot = true;
        if (code == KeyEvent.VK_SPACE) {
            if (gp.gameState == gp.playState)
                gp.gameState = 0;
            else if (gp.gameState == gp.pauseState)
                gp.gameState = 1;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.gameState == gp.endState) {
                gp.gameState = gp.titleState;
                gp.endGame();
            }
            else if (gp.gameState == gp.titleState) {
                gp.gameState = gp.playState;
                try {
                    gp.setNewGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        if (code == KeyEvent.VK_H)
            if (gp.gameState == gp.titleState)
                gp.gameState = gp.scoreState;
            else if(gp.gameState == gp.scoreState)
                gp.gameState = gp.titleState;
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A)
            left = false;
        if (code == KeyEvent.VK_D)
            right = false;
        if (code == KeyEvent.VK_W)
            shoot = false;
    }
}