package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Pipe extends Rectangle {

    float xVelocity = -4;
    Image pipeImage;

    Pipe(float x, float y, float width, float height){
        super((int)x, (int)y, (int)width, (int)height);
        try {
            pipeImage = ImageIO.read(new File("pipe.png")).getScaledInstance((int)width, (int)height, 1);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        x += xVelocity;
    }
    public void draw(Graphics g) {
        g.drawImage(pipeImage, x, y, null);
    }

}
