package com.rudransh;

import java.awt.*;

public class GameMenu extends Rectangle {

    GameMenu(){
        super(0, 0, 10, 10);

    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("MAIN MENU", 400, 200);
        g.drawString("PRESS ENTER TO START THE GAME", 230, 300);
        g.drawString("TEMP", 440, 400);
    }
}
