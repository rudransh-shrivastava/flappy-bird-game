package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    //declare values
    Action jump;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BIRD_SIZE = (int)(GAME_WIDTH/30);
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Pipe pipe;
    Bird bird;
    Score score;

    //create a game panel
    GamePanel(){
        newBird();
        newPipe();
        jump = new Jump();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        //todo space key action listener key listener;
        this.setPreferredSize(SCREEN_SIZE);

        //create game thread
        gameThread = new Thread(this);
        gameThread.start();
        //action for jump key
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "jump");
        this.getActionMap().put("jump", jump);

    }

    public void newPipe() {
        pipe = new Pipe(GamePanel.GAME_WIDTH-(GamePanel.BIRD_SIZE*2), 0, GamePanel.BIRD_SIZE*2, GamePanel.GAME_HEIGHT);
    }
    //creates a new bird also used for creating the first bird
    public void newBird() {
        bird = new Bird((GAME_WIDTH/5)-BIRD_SIZE,(GAME_HEIGHT/2)-BIRD_SIZE, BIRD_SIZE, BIRD_SIZE);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        bird.draw(g);
        pipe.draw(g);
    }
    public void move() {
        bird.move();
        pipe.move();
    }
    //checking for all collisions
    public void checkCollision() {
        // check bird boundary collision
        if(bird.y<0)
            bird.y = 0;
        if(bird.y>=GAME_HEIGHT-BIRD_SIZE)
            bird.y = GAME_HEIGHT-BIRD_SIZE;
    }
    // running the game in 60 ticks per second / 60 fps
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    //when jump key is pressed this code is executed
    public class Jump extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            bird.yVelocity = -16;
        }
    }
}
