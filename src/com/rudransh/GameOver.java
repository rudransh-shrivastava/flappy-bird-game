package com.rudransh;

import java.awt.*;

public class GameOver extends Rectangle {

    GameOver(){
        super(0, 0, 10, 10);

    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("GAME OVER", 400, 200);
        g.drawString("PRESS ENTER KEY TO RETURN TO MAIN MENU", 170, 300);
        g.drawString("TEMP", 440, 400);
    }
}
