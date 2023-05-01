package com.rudransh;

import java.awt.*;

public class GameMenu extends Rectangle {

    GameMenu(){
        super(0, 0, 10, 10);

    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(1, 1, 100, 100);
    }
}
