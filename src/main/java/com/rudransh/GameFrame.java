package com.rudransh;

import javax.swing.*;

public class GameFrame extends JFrame {
    // declaring the values
    GamePanel panel;

    GameFrame(){
        panel = new GamePanel();
        // frame
        this.add(panel);
        this.setTitle("Flappy Bird");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
