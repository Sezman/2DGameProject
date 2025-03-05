package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // so we can close properly
        window.setResizable(false); // so the window cannot be resized
        window.setTitle("2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); //add panel to window

        window.pack(); // sets window to fit the preffered size of components

        window.setLocationRelativeTo(null); // window set to center of screen
        window.setVisible(true); // make visible

        gamePanel.startGameThread();



    }
}