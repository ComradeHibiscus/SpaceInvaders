package Entity;

import Main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AlienLaser extends Entity{
    Panel gp;
    Alien alien;

    public AlienLaser (Panel gp, Alien alien) throws IOException {
        this.gp = gp;
        this.alien = alien;

        setDefaults();
        getAlienLaserImage();
    }

    private void setDefaults() {
        x = alien.x;
        y = alien.y;
        speed = 6;
    }

    private void getAlienLaserImage() throws IOException {
        shoot = ImageIO.read(new File("./src/AlienLaser.png"));
    }

    @Override
    public void update () {
        y += speed;
    }
    @Override
    public void draw (Graphics2D g2) {
        BufferedImage image = shoot;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}