package com.thanh.gui;

import com.thanh.model.Actor;
import com.thanh.model.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelBoom extends JPanel implements KeyListener, Runnable {
    private GameManager gameManager;
    private boolean flag[] = new boolean[256];


    public PanelBoom() {
        setBackground(Color.gray);
        gameManager = new GameManager();
        gameManager.initGame();

        setFocusable(true);
        addKeyListener(this);

        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameManager.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        flag[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        flag[e.getKeyCode()] = false;
    }

    @Override
    public void run() {
        while (true) {


            if (flag[KeyEvent.VK_LEFT] == true) {
                gameManager.moveBomber(Actor.LEFT);
            } else if (flag[KeyEvent.VK_RIGHT] == true) {
                gameManager.moveBomber(Actor.RIGHT);
            } else if (flag[KeyEvent.VK_UP] == true) {
                gameManager.moveBomber(Actor.UP);
            } else if (flag[KeyEvent.VK_DOWN] == true) {
                gameManager.moveBomber(Actor.DOWN);
            }
            if (flag[KeyEvent.VK_SPACE] == true) {
                gameManager.innitBomb();
            }

            gameManager.AI();
            if(gameManager.getStatus()==2){
                gameManager.initGame();
            }

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
