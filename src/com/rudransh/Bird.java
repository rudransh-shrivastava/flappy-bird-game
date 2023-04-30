package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Bird extends Rectangle {
    //declaring values
    int yAcceleration;
    int yVelocity = -3;
    int gravity = 1;

    Bird(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public void setYDirection(int yDirection) {

    }
    // this code runs every tick
    public void move() {
        y += yVelocity;
        yVelocity += gravity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }

}
