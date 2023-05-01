package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    //declare values
    Action jump;
    Action gameEnter;
    static final float GAME_WIDTH = 1000;
    static final float GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension((int)GAME_WIDTH, (int)GAME_HEIGHT);
    static final float BIRD_SIZE = (float)(GAME_WIDTH/30);
    static final float PIPE_SPACING = (float)(BIRD_SIZE*4);
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    GameMenu menu;
    Pipe topPipe;
    Pipe bottomPipe;
    float topPipeLength;
    Bird bird;
    Score score;

    private enum GameState {
        MENU,
        PLAYING,
        GAME_OVER;
    }
    private GameState gameState = GameState.MENU;


    //create a game panel
    GamePanel(){

        if (gameState == GameState.PLAYING) {
            newBird();
            newPipe();
        }
        if (gameState == GameState.MENU) {
            newMenu();
        }
        jump = new Jump();
        gameEnter = new GameEnter();
        score = new Score(GAME_WIDTH, GAME_HEIGHT); // todo
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        //create game thread
        gameThread = new Thread(this);
        gameThread.start();
        //action for jump key
        if (gameState == GameState.PLAYING) {
            this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "jump");
            this.getActionMap().put("jump", jump);
        }
        if (gameState == GameState.MENU) {
            this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "gameEnter");
            this.getActionMap().put("gameEnter", gameEnter);
        }

    }

    public void newMenu() {
        menu = new GameMenu();
    }

    public void newPipe() {
        random = new Random();
        topPipeLength = random.nextInt(400);
        topPipe = new Pipe(GamePanel.GAME_WIDTH-(GamePanel.BIRD_SIZE*2), 0, GamePanel.BIRD_SIZE*2, topPipeLength);
        bottomPipe = new Pipe(GamePanel.GAME_WIDTH-(GamePanel.BIRD_SIZE*2), topPipeLength+PIPE_SPACING, GamePanel.BIRD_SIZE*2, GAME_WIDTH-PIPE_SPACING-topPipeLength);
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
        if (gameState == GameState.PLAYING) {
            bird.draw(g);
            topPipe.draw(g);
            bottomPipe.draw(g);
        }
        if (gameState == GameState.MENU) {
            menu.draw(g);
        }
    }
    public void move() {
        if (gameState == GameState.PLAYING) {
            if (bird != null) {
                bird.move();
            }
            if (topPipe != null) {
                topPipe.move();bird.move();
            }
            if (bottomPipe != null) {
                bottomPipe.move();
            }
        }

    }
    //checking for all collisions
    public void checkCollision() {
        // check bird boundary collision
        if (gameState == GameState.PLAYING && bird != null) {
            if(bird.y<0)
                bird.y = 0;
            if(bird.y>=GAME_HEIGHT-BIRD_SIZE)
                bird.y = (int)(GAME_HEIGHT-BIRD_SIZE);
        }
        
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
            bird.yVelocity = -5;
        }
    }
    public class GameEnter extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameState = GameState.PLAYING;
            newBird();
            newPipe();
            getInputMap().remove(KeyStroke.getKeyStroke("ENTER"));
            getInputMap().put(KeyStroke.getKeyStroke("UP"), "jump");
            getActionMap().put("jump", jump);
        }
    }
}
