package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screeenX, screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screeenX = gp.screenWidth/2 - (gp.tileSize/2); //subtract as oorner would have been in the middle
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize*25;
        worldY = gp.tileSize*35;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = loadImage("player/chrisUp1.png");
            up2 = loadImage("player/chrisUp2.png");
            down1 = loadImage("player/chrisDown1.png");
            down2 = loadImage("player/chrisDown2.png");
            right1 = loadImage("player/chrisRight1.png");
            right2 = loadImage("player/chrisRight2.png");
            left1 = loadImage("player/chrisLeft1.png");
            left2 = loadImage("player/chrisLeft2.png");
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

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.rightPressed == true || keyH.leftPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                worldY += speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                worldX += speed;
            }else if (keyH.leftPressed == true){
                direction = "left";
                worldX -= speed;
            }

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNumber == 1){
                    spriteNumber = 2;}
                else{
                    spriteNumber = 1;}
                spriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);//set color for drawing objects
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNumber == 1){image = up1;}
                if(spriteNumber == 2){image = up2;}
                break;
            case "down":
                if(spriteNumber == 1){image = down1;}
                if(spriteNumber == 2){image = down2;}
                break;
            case "left":
                if(spriteNumber == 1){image = left1;}
                if(spriteNumber == 2){image = left2;}
                break;
            case "right":
                if(spriteNumber == 1){image = right1;}
                if(spriteNumber == 2){image = right2;}
                break;
        }

        g2.drawImage(image, screeenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
