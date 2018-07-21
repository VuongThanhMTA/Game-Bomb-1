package com.thanh.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Bomber extends Actor {
    public static int ALLOW_RUN = 0;
    public static int DISALLOW_RUN = 1;
    protected int score;


    protected int heart = 3;
    protected int status;
    protected int sizeBomb;
    protected int quantityBomb;
    protected int runBomb;


    public Bomber(int x, int y, int speed, int sizeBomb, int quantityBomb) {

        super(x, y, speed);
        this.runBomb = DISALLOW_RUN;
        this.status = Actor.ALIVE;
        this.heart = 3;
        this.score = 0;
        orient = RIGHT;
        this.sizeBomb = sizeBomb;
        this.quantityBomb = quantityBomb;

        images = new Image[6];
        images[0] = new ImageIcon(getClass().getResource("/images/bomber_left.png")).getImage();
        images[1] = new ImageIcon(getClass().getResource("/images/bomber_right.png")).getImage();
        images[2] = new ImageIcon(getClass().getResource("/images/bomber_up.png")).getImage();
        images[3] = new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage();
        images[4] = new ImageIcon(getClass().getResource("/images/bomber_dead.png")).getImage();
        images[5] = new ImageIcon(getClass().getResource("/images/ghost.png")).getImage();

    }

    public void changeOrient(int newOrient) {
        if (this.status == Actor.DEAD) {
            return;
        }
        orient = newOrient;
    }

    @Override
    public boolean move(ArrayList<MapBomb> arrMap, ArrayList<Bomb> arrBomb, ArrayList<Monster> arrMonster) {
        if (status == DEAD) {
            return false;
        }
        return super.move(arrMap, arrBomb, arrMonster);
    }

    public boolean isImpactMonster(Monster monster) {

        if (status == Actor.DEAD) {
            return false;
        }
        Rectangle rectBomber = new Rectangle(x, y, images[orient].getWidth(null),
                images[orient].getHeight(null) - 20);
        Rectangle rectMonster = new Rectangle(monster.getX(), monster.getY(), monster.getImage().getWidth(null),
                monster.getImage().getHeight(null) - 23);
        return rectBomber.intersects(rectMonster);
    }

    public Rectangle getRecBomber() {
        Rectangle rect = new Rectangle(x, y, images[orient].getWidth(null),
                images[orient].getHeight(null) - 20);
        return rect;
    }

    public boolean isImpactItem(Item item) {
        return this.getRec().intersects(item.getRec());
    }


    public void setNew(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = ALIVE;
        this.orient = RIGHT;
    }

    public int getHeart() {
        return heart;
    }

    public int getStatus() {
        return status;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSizeBomb(int sizeBomb) {
        this.sizeBomb = sizeBomb;
    }

    public void setQuantityBomb(int quantityBomb) {
        this.quantityBomb = quantityBomb;
    }

    public void setImageDie(Image image) {
        //Image image = new ImageIcon(getClass().getResource("/images/bomber_dead.png")).getImage();
        this.images[orient] = image;
    }

    public int getSizeBomb() {
        return sizeBomb;
    }

    public int getQuantityBomb() {
        return quantityBomb;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getRunBomb() {
        return runBomb;
    }
}
