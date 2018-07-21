package com.thanh.model;

import javax.swing.*;
import java.awt.*;

public class Bomb {

    protected int x;
    protected int y;
    protected int size;
    protected int timeLine;

    public Image getImage() {
        return image;
    }

    // protected int orient;
    protected Image image;

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTimeLine() {
        return timeLine;
    }


    public Bomb(int x, int y,int size,int timeLine) {
        this.x = x;
        this.y = y;
        this.size=size;
        this.timeLine=timeLine;
        image = new ImageIcon(getClass().getResource("/images/bomb.png")).getImage();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }

    public Rectangle getRec() {
        Rectangle rectangle = new Rectangle(x, y,
                image.getWidth(null),
                image.getHeight(null));
        return rectangle;
    }

    // va cham bomb
    public boolean isImpactBom(int x, int y) {
        Rectangle rec1 = new Rectangle(x, y,
                image.getWidth(null),
                image.getHeight(null));
        return this.getRec().intersects(rec1);
    }

    // va cham monster or bomber
    public boolean isImpactActor(Actor actor){
        Rectangle recActor = new Rectangle(actor.getX(),actor.getY(),actor.getImages().getWidth(null),
                actor.getImages().getHeight(null));
        return this.getRec().intersects(recActor);
    }
    public boolean impactBomber(Bomber bomber){
        Rectangle rec2 = new Rectangle(x, y, 45, 45);
        Rectangle rec3 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getImages().getWidth(null)
                , bomber.getImages().getHeight(null));
        return rec2.intersects(rec3);
    }

    public void deadlineBomb() {
        //if(timeLine>0){
        timeLine--;
        //}
    }
}
