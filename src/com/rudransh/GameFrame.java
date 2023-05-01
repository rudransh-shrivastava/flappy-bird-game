package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    GamePanel panel;
    //create a game frame init a panel and add the panel to the frame
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Flappy Bird");
        //this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
