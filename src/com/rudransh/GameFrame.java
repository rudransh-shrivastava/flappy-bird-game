package com.rudransh;

import javax.swing.*;
public class GameFrame extends JFrame {
    GameFrame() {
        this.setVisible(true);
        this.setTitle("Flappy Bird");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true); // made this true
        this.setSize(1000,1000);

    }

}
