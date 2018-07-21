package com.thanh.model;

import com.thanh.gui.FrameBoom;

import javax.management.monitor.MonitorSettingException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Actor {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    protected int x;
    protected int y;

    public void setOrient(int orient) {
        this.orient = orient;
    }

    protected int orient;
    protected int speed;
    protected Image[] images;
    protected int runBomb;

    public void setRunBomb(int runBomb) {
        this.runBomb = runBomb;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Actor(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;


    }

    public int getOrient() {
        return orient;
    }

    public Image getImages() {
        return images[orient];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void draw(Graphics2D g2d) {

        g2d.drawImage(images[orient], x, y, null);
    }

    public Rectangle getRec() {
        Rectangle rect = new Rectangle(x, y, images[orient].getWidth(null),
                images[orient].getHeight(null));
        return rect;
    }

    public boolean move(ArrayList<MapBomb> arrMap, ArrayList<Bomb> arrBomb, ArrayList<Monster> arrMonster) {


        int xR = x;
        int yR = y;
        switch (orient) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;

                break;
            case UP:
                y -= speed;

                break;
            case DOWN:
                y += speed;

                break;
        }
        boolean checkMap = checkMap(arrMap, arrBomb, arrMonster);

        if (checkMap == false) {
            x = xR;
            y = yR;
            return false;
        }
        return true;
    }



    private boolean checkMap(ArrayList<MapBomb> arrMap, ArrayList<Bomb> arrBomb, ArrayList<Monster> arrMonster) {
        if (x <= 0 || x > FrameBoom.WIDTH_FRAME - 270
                || y < -5 || y > FrameBoom.HEIGHT_FRAME - 100) {
            return false;
        }

        for (MapBomb mapBomb : arrMap) {
            //int bitT = mapBomb.getBit();
            //if (bitT != 5) {
                //Rectangle rectangle = getRec().intersection(mapBomb.getRec());
                Rectangle recA = new Rectangle(x,y,images[orient].getWidth(null),
                    images[orient].getHeight(null));
            Rectangle recBox = new Rectangle(mapBomb.getX()+10,mapBomb.getY(),mapBomb.getImage().getWidth(null)-10,
                    mapBomb.getImage().getHeight(null)-20);
            Rectangle rectangle = recA.intersection(recBox);
                if (rectangle.isEmpty() == false) {//có va chạm
                    return false;
                }
            //}
        }

//        for (MapBomb mapBomb : arrMap) {
//            if (mapBomb.getBit() != 5) {
//                for (Monster monster : arrMonster) {
//                    Rectangle rectangle = monster.getRecMonster().intersection(mapBomb.getRec());
//                    if (rectangle.isEmpty() == false) {//có va chạm
//                        return false;
//                    }
//                }
//            }
//        }



        for (Bomb bomb : arrBomb) {
            for (Monster monster : arrMonster) {
                if (bomb.isImpactActor(monster)) {
                    return false;
                }
            }
        }

        return true;
    }

}
