package com.thanh.model;

import javax.swing.*;
import java.awt.*;

public class MapBomb {
    private int x;
    private int y;
    private int bit;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return images[bit - 1];
    }

    private Image[] images = {
            new ImageIcon(getClass()
                    .getResource("/images/box1.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/box2.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/shawdow1.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/shawdow2.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/background_Small.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/item_bomb.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/item_bombsize.png")).getImage(),
            new ImageIcon(getClass()
                    .getResource("/images/item_shoe.png")).getImage()
    };

    public MapBomb(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;// giá trị bit trong map tương ứng vị trí x,y

    }

    public void drawMapBomb(Graphics2D g2d) {
        g2d.drawImage(images[bit - 1], x, y, null);
    }

    public Rectangle getRec() {
        Rectangle rect = new Rectangle(x + 3, y, images[bit - 1].getWidth(null) - 5,
                images[bit - 1].getHeight(null)-20);
//        Rectangle rect1 = new Rectangle(x, y+40,45,1);
        return rect;
    }

    public int getBit() {
        return bit;
    }

    public int isImpactBoxvsActor(Actor actor) {
        Rectangle recBox = new Rectangle(x, y, images[bit - 1].getWidth(null),
                images[bit - 1].getHeight(null));
        Rectangle recA = new Rectangle(actor.getX(), actor.getY(), actor.getImages().getWidth(null),
                actor.getImages().getHeight(null));
        Rectangle rec3 = new Rectangle();
        if (recBox.intersects(recA)) {
            recBox.intersect(recBox, recA, rec3);
            if (rec3.getHeight() == 1 && (actor.getOrient() == Actor.UP || actor.getOrient() == Actor.DOWN)) {
                if (actor.getX() == rec3.getX()) {
                    return (int) rec3.getWidth();
                } else {
                    return (int) -rec3.getWidth();
                }
            } else {
                if (actor.getY() == rec3.getY()) {
                    return (int) rec3.getHeight();
                } else {
                    return (int) -rec3.getHeight();
                }
            }
        }
        return 0;
    }
}
