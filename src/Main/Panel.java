package Main;

import Entity.Alien;
import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Panel extends JPanel implements Runnable {

    public static final int tileSize = 48;
    public static final int rows = 14;
    public static final int columns = 12;
    public static final int width = tileSize * columns;
    public static final int height = tileSize * rows;

    public static final int fps = 45;

    public int gameState;
    public static final int pauseState = 0;
    public static final int playState = 1;
    public static final int endState = 2;
    public static final int titleState = 3;
    public static final int scoreState = 4;

    public int alienCount;
    public int score;

    Thread gameThread;
    KeyHandler keyH;
    TileManager tiles;
    UI ui;
    CollisionChecker checkCollisions;
    public Panel  () throws IOException {
        keyH = new KeyHandler(this);
        tiles  = new TileManager(this);
        ui = new UI (this);
        checkCollisions = new CollisionChecker(this, ui);

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        gameState = titleState;
        score = 0;
    }

    public void startGame () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setNewGame () throws IOException {
        ui.entityArrayList.add(new Player(this, keyH, ui));
        ui.entityArrayList.get(0).life = 3;
        newWave();
        gameState = playState;
    }

    public void endGame () {
        ui.entityArrayList.clear();
        score = 0;
        alienCount = 0;
    }

    public void run() {
        double drawInt = 1000000000.0/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread.isAlive()) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInt;
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }
        }
    }

    private void update () throws IOException {
        if (gameState == playState) {
            for (int i = ui.entityArrayList.size() - 1; i >= 0; i--) {
                if (gameState == playState) {
                    ui.entityArrayList.get(i).update();

                    if (checkCollisions.checkPlayerHit(i)) {
                        i--;
                        continue;
                    }
                    if (checkCollisions.checkAlienHit(i))
                        i--;
                }
            }
            if (alienCount == 0) {
                newWave();
            }
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tiles.draw(g2);

        if (gameState != titleState && gameState != scoreState)
            for (int i = ui.entityArrayList.size() - 1; i >= 0; i--)
                ui.entityArrayList.get(i).draw(g2);

        ui.draw(g2);
        g2.dispose();
    }

    private void newWave () throws IOException{
        int x = 0;
        int y = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < columns; j++) {

                ui.entityArrayList.add(new Alien(this, ui, x, y));
                x += tileSize;
                alienCount++;
            }
            y += tileSize;
            x = 0;
        }
    }
}