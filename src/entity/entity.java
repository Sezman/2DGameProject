package entity;

import java.awt.image.BufferedImage;

/*
This class will be used as the parent class for NPCS, MONSTERS, and PLAYERS
 */
public class entity {
    public int worldX, worldY ;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
