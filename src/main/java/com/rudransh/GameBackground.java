package com.rudransh;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GameBackground extends Rectangle {
    //declaring values
    BufferedImage backgroundImage;

    GameBackground(){
        super();
        try {
            backgroundImage = ImageIO.read(new File("background.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, x, y, null);
    }

}
