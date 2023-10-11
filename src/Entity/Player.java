package Entity;

import Main.KeyHandler;
import Main.Panel;
import Main.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{

    Panel gp;
    KeyHandler keyH;
    UI ui;
    int updateTimes = 0;

    public Player(Panel gp, KeyHandler keyH, UI ui) throws IOException {
        this.gp = gp;
        this.keyH = keyH;
        this.ui = ui;
        life = 3;

        getPlayerImage();
        setDefaults();
    }

    private void setDefaults() {
        x = gp.width / 2 - gp.tileSize / 2;
        y = gp.height - gp.tileSize;
        speed = 6;
        action = "rest";
    }

    private void getPlayerImage() throws IOException {
        rest = ImageIO.read(new File("./src/LaserCannon.png"));
        shoot = ImageIO.read(new File("./src/LaserCannonShoots.png"));
    }

    @Override
    public void update () throws IOException {
        if (keyH.left)
            x -= speed;
        else if (keyH.right)
            x += speed;

        if (keyH.shoot) {
            action = "shoot";

            updateTimes++;

            if(updateTimes % 14 == 0)
                ui.entityArrayList.add(new PlayerLaser(gp, this));
        }
        else
            action = "rest";

        if(action.equals("rest"))
            updateTimes = 0;

        if (x > gp.width - gp.tileSize)
            x = gp.width - gp.tileSize;
        if (x < 0)
            x = 0;
    }

    @Override
    public void draw (Graphics2D g2) {
         if(action.equals("shoot"))
             g2.drawImage(shoot, x, y, gp.tileSize, gp.tileSize, null);
         else
             g2.drawImage(rest, x, y, gp.tileSize, gp.tileSize, null);
    }
}