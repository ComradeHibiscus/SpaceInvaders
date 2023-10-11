package Entity;
import Main.Panel;
import Main.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alien extends Entity{

    Panel gp;
    UI ui;
    int count = 0;

    public Alien (Panel gp, UI ui, int x, int y) throws IOException {
        this.gp = gp;
        this.ui = ui;
        setDefaults(x, y);
        getAlienImage();
    }

    private void setDefaults (int x, int y) {
        this.x = x;
        this.y = y;
        speed = 1;
        action = "rest";
    }

    private void getAlienImage () throws IOException {
        rest = ImageIO.read(new File("./src/Aliens.png"));
        shoot = ImageIO.read(new File("./src/AliensUp.png"));
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image;

        if (action.equals("shoot"))
            image = shoot;
        else
            image = rest;

        g2.drawImage(image, x, y, gp.tileSize,gp.tileSize, null);
    }

    @Override
    public void update() throws IOException {
        if (action.equals("shoot"))
            count++;

        if (count > 15) {
            action = "rest";
            count = 0;
        }

        y += speed;

        if ((int)(Math.random() * 3000) <= 7)
            shoot();
    }

    private void shoot() throws IOException {
        action = "shoot";
        ui.entityArrayList.add(new AlienLaser(gp, this));
    }
}