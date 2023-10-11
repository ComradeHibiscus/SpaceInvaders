package Main;

import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class UI {

    Panel gp;
    Font font, smallFont;
    Graphics2D g2;
    FileHandler fh;

    public BufferedImage hearts;
    public BufferedImage alien;

    public ArrayList<Entity> entityArrayList;

    public UI(Panel gp) throws IOException {
        this.gp = gp;
        font = new Font("Nirmala UI Semilight", Font.PLAIN, 50);
        smallFont = new Font("Nirmala UI Semilight", Font.PLAIN, 25);

        entityArrayList = new ArrayList<>();

        fh = new FileHandler(this, gp);
        fh.setHsArrayLists();

        hearts = ImageIO.read(new File("./src/Heart.png"));
        alien = ImageIO.read(new File("./src/Aliens.png"));
    }

    public void draw (Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font);
        g2.setColor(Color.WHITE);

        if (gp.gameState != gp.titleState && gp.gameState != gp.scoreState) {
            if (gp.gameState == gp.pauseState)
                drawPauseScreen();
            if (gp.gameState == gp.endState)
                drawEndScreen();

            drawScore(g2);
            drawHearts(g2, entityArrayList.get(0).life);
        }
        else if (gp.gameState == gp.titleState)
            drawTitleScreen(g2);
        else
            drawScoreScreen(g2);
    }

    private void drawPauseScreen() {
        String text = "PAUSED";
        int x = gp.width / 2 - getXCenterText(text) / 2;
        int y = gp.height / 2;
        g2.drawString(text, x, y);
    }

    public void drawEndScreen() {
        String text = "GAME OVER";
        int x = gp.width / 2 - getXCenterText(text) / 2;
        int y = gp.height / 2;
        g2.drawString(text, x, y);

        text = "Press ENTER to return to menu";
        g2.setFont(smallFont);
        x = gp.width / 2 - getXCenterText(text) / 2;
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);

        g2.setFont(font);
    }

    private void drawTitleScreen(Graphics2D g2) {
        String text = "Space Invaders";
        int x = gp.width / 2 - getXCenterText(text) / 2;
        int x2 = x;
        int y = gp.tileSize * 3;
        g2.drawString(text, x, y);

        text = "Press ENTER to Play";
        x = gp.width / 2 - getXCenterText(text) / 2;
        y = gp.tileSize * 9;
        g2.drawString(text, x, y);

        text = "Press H to see Scores";
        x = gp.width / 2 - getXCenterText(text) / 2;
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);

        y = gp.tileSize * 4;
        x2 += gp.tileSize * 1.25;
        drawAlien(g2, x2, y);
    }

    private void drawScoreScreen(Graphics2D g2) {
        String text = "High Scores";
        int x = gp.width / 2 - getXCenterText(text) / 2;
        int y = gp. tileSize * 2;
        g2.drawString(text, x, y);
        y += gp.tileSize;

        g2.setFont(smallFont);
        for (int i = 0; i < fh.highScores.size(); i++) {
            y += gp.tileSize;
            text = "#" + (i + 1) + " :: " + fh.highScores.get(i) + "     " + fh.highScoresNames.get(i);
            g2.drawString(text, x, y);
        }

        text = "Press H to return to menu";
        x = gp.width / 2 - getXCenterText(text) / 2;
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
    }

    private void drawScore(Graphics2D g2) {
        g2.setFont(smallFont);
        g2.setColor(Color.WHITE);
        String text = "Score: " + gp.score;
        int x = 0;
        int y = gp.tileSize;
        g2.drawString(text, x, y);
    }

    private void drawHearts(Graphics2D g2, int playerLife) {
        int x = gp.width - 4 * gp.tileSize;
        int y = 0;

        for (int i = 0; i < playerLife; i++) {
            g2.drawImage(hearts, x, y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
        }
    }

    private void drawAlien(Graphics2D g2, int x, int y) {
        g2.drawImage(alien, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
    }

    private int getXCenterText(String text) {
        return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }
}