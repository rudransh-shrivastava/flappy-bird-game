package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Bird extends Rectangle {
    //declaring values
    float yVelocity = -3;
    float gravity = 0.4F;
    BufferedImage birdImage;



    Bird(float x, float y, float width, float height){
        super((int)x, (int)y, (int)width, (int)height);

        try {
            birdImage = ImageIO.read(new File("res/bird.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // this code runs every tick
    public void move() {
        y += yVelocity;
        yVelocity += gravity;
    }
    public void draw(Graphics g) {
        g.drawImage(birdImage, x, y, null);
    }

}
