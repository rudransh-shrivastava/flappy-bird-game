package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Bird extends Rectangle {
    //declaring values
    int yAcceleration;
    int yVelocity = -3;
    int gravity = 1; // todo this gravity is like standing on the sun

    Bird(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    // this code runs every tick
    public void move() {
        y += yVelocity;
        yVelocity += gravity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

}
