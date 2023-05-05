package com.rudransh;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pipe extends Rectangle {
    // declaring the values
    float xVelocity = -4;
    Image pipeImage;

    Pipe(float x, float y, float width, float height, int pNum){
        super((int)x, (int)y, (int)width, (int)height);
        // initialising the pipeImage and also checking if top pipe or bottom pipe
        if (pNum == 0){
            try {
                pipeImage = ImageIO.read(new File("longpipe.png")).getScaledInstance((int)width, (int)height, 1);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }else if (pNum == 1){
            try {
                pipeImage = ImageIO.read(new File("longpipe.png")).getScaledInstance((int)width, (int)height, 1);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    // this code runs every tick to move the pipes
    public void move() {
        x += xVelocity;
    }
    // drawing the pipes
    public void draw(Graphics g) {
        g.drawImage(pipeImage, x, y, null);
    }
}
