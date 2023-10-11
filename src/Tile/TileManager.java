package Tile;

import Main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    Panel gp;
    Tile [] tile;
    int [][] mapTileNum;

    public TileManager (Panel gp) throws IOException {
        this.gp = gp;

        tile = new Tile [3];
        mapTileNum = new int [gp.columns][gp.rows];

        getTileImage();
        loadMap();
    }

    private void loadMap() {
            for (int rowL  = 0; rowL < mapTileNum.length; rowL++)
                for (int colL = 0; colL < mapTileNum[0].length; colL++)
                    mapTileNum [rowL][colL] = (int)(Math.random() * 3);
    }

    private void getTileImage () throws IOException {
        tile [0] = new Tile ();
        tile [0].image = ImageIO.read(new File("./src/Space.png"));

        tile [1] = new Tile ();
        tile [1].image = ImageIO.read(new File("./src/Space2.png"));

        tile [2] = new Tile ();
        tile [2].image = ImageIO.read(new File("./src/Space3.png"));
    }

    public void draw (Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        int tileNum;

        while (col < gp.columns && row < gp.rows) {
            tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;

            x += gp.tileSize;

            if (col == gp.columns) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}