package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        tile = new Tile[10]; //amount of different tiles are stored here
        getTileImage();
        loadMap("maps/map01.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = loadImage("tiles/void.png");

            tile[1] = new Tile();
            tile[1].image = loadImage("tiles/wall.png");

            tile[2] = new Tile();
            tile[2].image = loadImage("tiles/tile.png");

            tile[3] = new Tile();
            tile[3].image = loadImage("tiles/wood.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" "); //split based on space

                    int num = Integer.parseInt(numbers[col]); //convert to int
                    mapTileNum[col][row] = num;
                    col++;

                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Helper method to load images
    private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) {
            System.out.println("ERROR: Image not found -> " + path);
            return null;
        }
        return ImageIO.read(is);
    }

    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screeenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screeenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screeenX &&
                    worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) { //only draw if in bounds of player
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            col++;


            if(col == gp.maxWorldCol){
                col = 0;

                row++;

            }
        }
    }
}
