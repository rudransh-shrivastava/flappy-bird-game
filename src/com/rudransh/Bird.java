package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Bird extends Rectangle {
    //declaring values
    float yVelocity = -3;
    float gravity = 0.2F; // todo this gravity is like standing on the sun

    Bird(float x, float y, float width, float height){
        super((int)x, (int)y, (int)width, (int)height);
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
