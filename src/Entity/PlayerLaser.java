package Entity;

import Main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerLaser extends Entity {

    Panel gp;
    Player player;

    public PlayerLaser (Panel gp, Player player) throws IOException {
        this.gp = gp;
        this.player = player;

        setDefaults();
        getPlayerLaserImage();
    }
    private void setDefaults() {
        x = player.x;
        y = player.y;
        speed = 6;
    }

    private void getPlayerLaserImage() throws IOException {
        shoot = ImageIO.read(new File("./src/PlayerLaser.png"));
    }

    @Override
    public void update () {
        y -= speed;
    }

    @Override
    public void draw (Graphics2D g2) {
        BufferedImage image = shoot;
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}