package com.rudransh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game {

    JLabel label = new JLabel();
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    Game() {

        // bird
        ImageIcon Bird = new ImageIcon("Bird.png");
        Image NewBird = Bird.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon FinalBird = new ImageIcon(NewBird);
        label.setIcon(FinalBird);

        //actions
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        //input
        label.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        label.getActionMap().put("upAction", upAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        label.getActionMap().put("downAction", downAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        label.getActionMap().put("leftAction", leftAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        label.getActionMap().put("rightAction", rightAction);

        //frame
        GameFrame frame = new GameFrame();
        frame.add(label);

    }
    //actions begin
    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()-25);
        }
    }

    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()+25);
        }
    }

    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()-25, label.getY());
        }
    }

    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()+25, label.getY());
        }
    }
    //end of actions
}
