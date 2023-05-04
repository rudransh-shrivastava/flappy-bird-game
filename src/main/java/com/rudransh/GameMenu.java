package com.rudransh;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GameMenu extends Rectangle {
    BufferedImage gameMenuImage;
    GameMenu(){
        super(-10, 0, 10, 10);
        try {
            gameMenuImage = ImageIO.read(new File("menu.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        g.drawImage(gameMenuImage, x, y, null);
    }
}
