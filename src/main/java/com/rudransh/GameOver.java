package com.rudransh;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOver extends Rectangle {
    // declaring the values
    BufferedImage gameOverImage;

    GameOver(){
        super(150, -130, 10, 10);
        //initialising the gameOverImage
        try {
            gameOverImage = ImageIO.read(new File("gameover.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    // drawing the game over screen & score & high score
    public void draw(Graphics g) {
        // drawing gameOverImage
        g.drawImage(gameOverImage, x, y, null);
        // drawing the score on top of gameOverImage
        int score = GamePanel.gameScore/41;
        int[] scoreArray = GamePanel.intToArray(score);
        for(int i=0;i<scoreArray.length;i++){
            int x;
            if(i==0){
                x=325;
            }else x = 325 + i*30;
            g.drawImage(GamePanel.numbersImage[scoreArray[i]], x, 310, null);
        }
        // drawing the high score on top of gameOverImage
        int[] highScoreArray = GamePanel.intToArray(GamePanel.highScore/41);
        if(highScoreArray.length>0){
            for(int i=0;i<highScoreArray.length;i++){
                int x;
                if(i==0){
                    x=620;
                }else x = 620 + i*30;
                g.drawImage(GamePanel.numbersImage[highScoreArray[i]], x, 310, null);
            }
        }
    }
}
