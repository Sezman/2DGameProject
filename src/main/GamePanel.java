package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
        //works as a game screen

    //Screen setttings
    final int orignalTileSize = 16; //16x16 tile for Player,, NPC, mapTile etc...
    final int scale = 3; //scale size by this to look larger

    public final int tileSize = orignalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //world settings
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int worldWidth = tileSize*maxScreenCol;
    public final int worldHeight = tileSize*maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);
    TileManager tileM =  new TileManager(this);

    int fps = 60;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // makes all drawing done in an offscreen painting buffer (improves rendering performance)
        this.addKeyListener(keyH);// game panel to recognize key input
        this.setFocusable(true); // focused to receive key inputs

    }

    public void startGameThread(){

        gameThread = new Thread(this); // pass the thread to this class
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /fps; //aka 0.01666 seconds to draw the screen 60 times a second
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();

        while (gameThread != null){ //while game thread exists continue
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // change g to grpahics 2d for more functionality

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();//Saves memory realses system resources it is using
    }
}
