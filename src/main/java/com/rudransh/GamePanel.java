package com.rudransh;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
    // declaring the values
    Action jump;
    Action gameEnter;
    Action gameMenuEnter;
    static final float GAME_WIDTH = 1000;
    static final float GAME_HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension((int)GAME_WIDTH, (int)GAME_HEIGHT);
    static final float BIRD_SIZE = (GAME_WIDTH/30);
    static final float PIPE_SPACING = (BIRD_SIZE*4);
    ArrayList<Pipe> pipes = new ArrayList<Pipe>();
    static Image[] numbersImage = new Image[10];
    GameBackground gameBackground;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    GameMenu menu;
    GameOver gameOver;
    float topPipeLength;
    Bird bird;
    static int gameScore = 0;
    static int highScore = 0;
    Clip menuClip;
    // game states
    private enum GameState {
        MENU,
        PLAYING,
        GAME_OVER;
    }
    // initially the game state is menu
    private GameState gameState = GameState.MENU;
    // create a game panel
    GamePanel(){

        if (gameState == GameState.PLAYING) {
            newBird();
            newPipe(true);
            newPipe(true);
            newPipe(true);
            newPipe(true);
        }
        if (gameState == GameState.MENU) {
            newMenu();
            try {
                AudioInputStream menuAudioInputStream = AudioSystem.getAudioInputStream(new File("main.wav"));
                menuClip = AudioSystem.getClip();
                menuClip.open(menuAudioInputStream);
                menuClip.start();
            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException er) {
                er.printStackTrace();
            }
        }
        if (gameState == GameState.GAME_OVER) {
            newGameOver();
        }
        // loading the numbers image array
        loadNumbers();
        jump = new Jump();
        gameEnter = new GameEnter();
        gameMenuEnter = new GameMenuEnter();
        gameBackground = new GameBackground();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        // create game thread
        gameThread = new Thread(this);
        gameThread.start();
        // action for jump key
        if (gameState == GameState.PLAYING) {
            this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "jump");
            this.getActionMap().put("jump", jump);
        }
        // action for enter key
        if (gameState == GameState.MENU) {
            this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "gameEnter");
            this.getActionMap().put("gameEnter", gameEnter);
        }
        // action for enter key
        if (gameState == GameState.GAME_OVER) {
            this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "gameOver");
            this.getActionMap().put("gameOver", gameMenuEnter);
        }
    }
    public void newGameOver() {
        gameOver = new GameOver();
    }
    public void newMenu() {
        menu = new GameMenu();
    }
    // this loads the array of number images
    public void loadNumbers() {
        Image zeroImage;
        Image oneImage;
        Image twoImage;
        Image threeImage;
        Image fourImage;
        Image fiveImage;
        Image sixImage;
        Image sevenImage;
        Image eightImage;
        Image nineImage;
        try {
            zeroImage = ImageIO.read(new File("zero.png"));
            oneImage = ImageIO.read(new File("one.png"));
            twoImage = ImageIO.read(new File("two.png"));
            threeImage = ImageIO.read(new File("three.png"));
            fourImage = ImageIO.read(new File("four.png"));
            fiveImage = ImageIO.read(new File("five.png"));
            sixImage = ImageIO.read(new File("six.png"));
            sevenImage = ImageIO.read(new File("seven.png"));
            eightImage = ImageIO.read(new File("eight.png"));
            nineImage = ImageIO.read(new File("nine.png"));

            numbersImage[0] = zeroImage;
            numbersImage[1] = oneImage;
            numbersImage[2] = twoImage;
            numbersImage[3] = threeImage;
            numbersImage[4] = fourImage;
            numbersImage[5] = fiveImage;
            numbersImage[6] = sixImage;
            numbersImage[7] = sevenImage;
            numbersImage[8] = eightImage;
            numbersImage[9] = nineImage;

        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    // this converts an integer to an array
    public static int[] intToArray(int n) {
        int digits=0;
        int num = n;
        while(num>0){
            num = num/10;
            digits++;
        }
        int[] array = new int[digits];
        for(int i=digits-1;i>=0;i--){
            int lastDigit = n % 10;
            array[i] = lastDigit;
            n = n / 10;
        }
        return array;
    }
    // this creates a new pipe, both top and bottom
    public void newPipe(boolean start) {
        random = new Random();
        topPipeLength = random.nextInt(1, 400);
        if(start) {
            pipes.add(new Pipe(GamePanel.GAME_WIDTH-(GamePanel.BIRD_SIZE*2)+pipes.size()*300, 0, GamePanel.BIRD_SIZE*2, topPipeLength, 0));
            pipes.add(new Pipe(GamePanel.GAME_WIDTH-(GamePanel.BIRD_SIZE*2)+(pipes.size()-1)*300, topPipeLength+PIPE_SPACING, GamePanel.BIRD_SIZE*2, GAME_WIDTH-PIPE_SPACING-topPipeLength, 1));
        }
        else {
            pipes.add(new Pipe(pipes.get(pipes.size()-1).x + 600, 0, GamePanel.BIRD_SIZE*2, topPipeLength, 0));
            pipes.add(new Pipe(pipes.get(pipes.size()-1).x, topPipeLength+PIPE_SPACING, GamePanel.BIRD_SIZE*2, GAME_WIDTH-PIPE_SPACING-topPipeLength, 1));
        }
    }
    //this creates a new bird
    public void newBird() {
        bird = new Bird((GAME_WIDTH/5)-BIRD_SIZE,(GAME_HEIGHT/2)-BIRD_SIZE, BIRD_SIZE, BIRD_SIZE);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    // everything inside of this will be drawn
    public void draw(Graphics g) {
        gameBackground.draw(g);
        if (gameState == GameState.PLAYING) {
            bird.draw(g);
            for (int i=pipes.size()-1;i>=0;i--) {
                pipes.get(i).draw(g);
            }
            int score = gameScore/41;
            int[] scoreArray = intToArray(score);
            for(int i=0;i<scoreArray.length;i++){
                int x;
                if(i==0){
                    x=5;
                }else x = i*30;
                g.drawImage(numbersImage[scoreArray[i]], x, 5, null);
            }
        }
        if (gameState == GameState.MENU) {
            menu.draw(g);
        }
        if (gameState == GameState.GAME_OVER) {
            if (gameOver != null) {
                gameOver.draw(g);
            }
        }
    }
    // everything inside of this will move every tick
    public void move() {
        if (gameState == GameState.PLAYING) {
            if (bird != null) {
                bird.move();
            }
            for (int i=pipes.size()-1;i>=0;i--) {
                pipes.get(i).move();

                if (pipes.get(i).x+GamePanel.BIRD_SIZE*2 < 0) {
                    pipes.remove(pipes.get(i));
                    newPipe(false);
                }
            }
            // this updates the score
            if (pipes.size()>0){
                if ((pipes.get(0).x)+GamePanel.BIRD_SIZE*2<(GAME_WIDTH/5)-BIRD_SIZE && (pipes.get(0).x)+GamePanel.BIRD_SIZE*2<(GAME_WIDTH/5)-BIRD_SIZE+1){
                    gameScore++;
                    // play the sound
                    if(gameScore%41 == 0){
                        try {
                            AudioInputStream scoreAudioInputStream = AudioSystem.getAudioInputStream(new File("score.wav"));
                            Clip clip = AudioSystem.getClip();
                            clip.open(scoreAudioInputStream);
                            clip.loop(0);
                        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException er) {
                            er.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    // this checks for all collisions
    public void checkCollision() {
        // this checks for the bird - boundary collision
        if (gameState == GameState.PLAYING && bird != null && pipes.size() > 0) {
            if(bird.y<0){
                bird.y = 0;
            }
            if(bird.y>=GAME_HEIGHT-BIRD_SIZE){
                try {
                    AudioInputStream hitAudioInputStream = AudioSystem.getAudioInputStream(new File("hit.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(hitAudioInputStream);
                    clip.loop(0);
                } catch(UnsupportedAudioFileException | IOException | LineUnavailableException er) {
                    er.printStackTrace();
                }
                GameOverEnter();
            }
            if (pipes.size()>0){
                for (int i = pipes.size() - 1; i >= 0; i--) {
                    if (i < pipes.size() && bird.intersects(pipes.get(i))) {
                        try {
                            AudioInputStream hitAudioInputStream = AudioSystem.getAudioInputStream(new File("hit.wav"));
                            Clip clip = AudioSystem.getClip();
                            clip.open(hitAudioInputStream);
                            clip.loop(0);
                        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException er) {
                            er.printStackTrace();
                        }
                        GameOverEnter();
                    }
                }
            }
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
    // when jump key is pressed this code is executed
    public class Jump extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            bird.yVelocity = -7;
            try {
                AudioInputStream jumpAudioInputStream = AudioSystem.getAudioInputStream(new File("jump.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(jumpAudioInputStream);
                clip.loop(0);
            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException er) {
                er.printStackTrace();
            }

        }
    }
    // everything inside this will happen when you enter the game over screen
    public void GameOverEnter() {
        highScore = Math.max(highScore, gameScore);
        gameState = GameState.GAME_OVER;
        newGameOver();
        getInputMap().remove(KeyStroke.getKeyStroke("SPACE"));
        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "gameEnter");
        getActionMap().put("gameEnter", gameEnter);
        pipes.clear();
    }
    // everything inside this will happen when you enter the game
    public class GameEnter extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameState = GameState.PLAYING;
            menuClip.stop();
            gameScore = 0;
            newBird();
            newPipe(true);
            newPipe(true);
            newPipe(true);
            newPipe(true);
            getInputMap().remove(KeyStroke.getKeyStroke("ENTER"));
            getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "jump");
            getActionMap().put("jump", jump);
        }
    }
    // everything inside this will happen when you enter the menu
    public class GameMenuEnter extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameState = GameState.MENU;
            newMenu();
            try {
                AudioInputStream menuAudioInputStream = AudioSystem.getAudioInputStream(new File("main.wav"));
                menuClip = AudioSystem.getClip();
                menuClip.open(menuAudioInputStream);
                menuClip.start();
            } catch(UnsupportedAudioFileException | IOException | LineUnavailableException er) {
                er.printStackTrace();
            }
            getInputMap().remove(KeyStroke.getKeyStroke("ENTER"));
            getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "gameEnter");
            getActionMap().put("gameEnter", gameEnter);
        }
    }
}
