package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Pipe extends Rectangle {

    Random random;
    float xVelocity = -3;

    Pipe(float x, float y, float width, float height){
        super((int)x, (int)y, (int)width, (int)height);
    }

    public void move() {
        x += xVelocity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

}
