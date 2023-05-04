package com.rudransh;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOver extends Rectangle {
    BufferedImage gameOverImage;
    GameOver(){
        super(150, -130, 10, 10);
        try {
            gameOverImage = ImageIO.read(new File("gameover.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        g.drawImage(gameOverImage, x, y, null);
    }
}
