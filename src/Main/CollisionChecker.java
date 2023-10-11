package Main;

import Entity.Alien;
import Entity.AlienLaser;
import Entity.Entity;
import Entity.PlayerLaser;

public class CollisionChecker {

    Panel gp;
    UI ui;
    FileHandler fh;

    public CollisionChecker (Panel gp, UI ui) {
        this.gp = gp;
        this.ui = ui;
        fh = ui.fh;
    }

    public boolean checkPlayerHit(int i) {
        Entity entity = ui.entityArrayList.get(i);
        Entity player  = ui.entityArrayList.get(0);

        if (entity instanceof Alien)
            if (entity.y > gp.height - gp.tileSize) {
                gp.gameState = gp.endState;
                fh.setHighScores();
            }

        if (entity instanceof AlienLaser) {
            boolean yIntersect = entity.y - player.y > 0 && entity.y - player.y < gp.tileSize;
            boolean xIntersect = Math.abs(entity.x - player.x) < gp.tileSize;

            if (yIntersect && xIntersect)  {
                if (player.life > 1)
                    player.life--;
                else {
                    player.life--;
                    gp.gameState = gp.endState;
                    ui.drawEndScreen();
                    fh.setHighScores();
                }

                ui.entityArrayList.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean checkAlienHit (int i) {
        Entity laser = ui.entityArrayList.get(i);
        Entity entity;

        if (laser instanceof PlayerLaser) {
            for (int j = ui.entityArrayList.size() - 1; j > 0; j--) {
                entity  = ui.entityArrayList.get(j);

                boolean yIntersect = Math.abs(entity.y - laser.y) < gp.tileSize;
                boolean xIntersect = Math.abs(entity.x - laser.x) < gp.tileSize;

                if (entity instanceof Alien)
                    if (yIntersect && xIntersect) {
                        ui.entityArrayList.remove(j);
                        gp.alienCount--;
                        if (j > i)
                            ui.entityArrayList.remove(i);
                        else
                            ui.entityArrayList.remove(i - 1);
                        gp.score++;
                        return true;
                    }
            }
        }
        return false;
    }
}