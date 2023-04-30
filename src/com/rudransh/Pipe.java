package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Pipe extends Rectangle {

    Random random;
    int id;
    int xVelocity = -3;

    Pipe(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public void move() {
        x += xVelocity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

}
