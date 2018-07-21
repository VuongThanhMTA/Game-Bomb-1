package com.thanh.model;

import javax.swing.*;
import java.awt.*;

public class Item {
    public static int ITEM_BOMB=6;
    public static int ITEM_BOMBSIZE=7;
    public static int ITEM_SHOE=8;
    private int x, y, type, timeLine;
    private Image img;



    public Item(int x, int y, int type, String image) {
        super();
        this.x = x;
        this.y = y;
        this.type = type;
        this.img = new ImageIcon(getClass().getResource(image)).getImage();
        timeLine=250;
    }

    public void drawItem(Graphics2D g2d){
        g2d.drawImage(img, x, y, null);
    }

    public boolean isImpactItemVsBomber(Bomber bomber){
        Rectangle rec1 = new Rectangle(x, y, img.getWidth(null),img.getHeight(null));
        Rectangle rec2 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getImages().getWidth(null),
                bomber.getImages().getHeight(null));
        return rec1.intersects(rec2);
    }

    public Rectangle getRec() {
        Rectangle rectangle = new Rectangle(x, y,
                img.getWidth(null),
                img.getHeight(null)-20);
        return rectangle;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
