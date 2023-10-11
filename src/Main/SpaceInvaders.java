package Main;

import javax.swing.JFrame;
import java.io.IOException;

public class SpaceInvaders {

    public static void main (String [] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Space Invaders");

        Panel panel = new Panel();
        frame.add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startGame();
    }
}