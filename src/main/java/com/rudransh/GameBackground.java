package com.rudransh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameBackground extends Rectangle {
    // declaring the values
    BufferedImage backgroundImage;

    GameBackground(){
        super();
        // initialising the backgroundImage
        try {
            backgroundImage = ImageIO.read(new File("background.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    // drawing the background image
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, x, y, null);
    }
}
