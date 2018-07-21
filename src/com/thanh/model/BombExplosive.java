package com.thanh.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BombExplosive {
    private int x;
    private int y;
    // private int orient;
    // private Image[] images;
    private Image imgLeft;
    private Image imgRight;
    private Image imgUp;
    private Image imgDown;

    private int size;
    private int timeLine;

    public BombExplosive(int x, int y, int size, ArrayList<MapBomb> arrMap) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.timeLine = 50;
        // images = new Image[4];

        if (size == 1) {
            imgLeft = new ImageIcon(getClass().getResource("/images/bombbang_left_1.png")).getImage();
            imgRight = new ImageIcon(getClass().getResource("/images/bombbang_right_1.png")).getImage();
            imgUp = new ImageIcon(getClass().getResource("/images/bombbang_up_1.png")).getImage();
            imgDown = new ImageIcon(getClass().getResource("/images/bombbang_down_1.png")).getImage();
        } else {
            imgLeft = new ImageIcon(getClass().getResource("/images/bombbang_left_2.png")).getImage();
            imgRight = new ImageIcon(getClass().getResource("/images/bombbang_right_2.png")).getImage();
            imgUp = new ImageIcon(getClass().getResource("/images/bombbang_up_2.png")).getImage();
            imgDown = new ImageIcon(getClass().getResource("/images/bombbang_down_2.png")).getImage();
        }
//        for (int i = 0; i <arrMap.size()-1 ; i++) {
//            if(impactBox(x,y,))
//        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(imgUp, x, y + 45 - imgUp.getHeight(null), null);
        g2d.drawImage(imgDown, x, y, null);
        g2d.drawImage(imgLeft, x + 45 - imgLeft.getWidth(null), y, null);
        g2d.drawImage(imgRight, x, y, null);
    }

    public void setImages(int orient, int size) {

    }

    public boolean isImpactActor(Actor actor) {
        Rectangle recLeft = new Rectangle(x + 45 - imgLeft.getWidth(null),
                y, imgLeft.getWidth(null), imgLeft.getHeight(null));
        Rectangle recRight = new Rectangle(x, y, imgRight.getWidth(null),
                imgRight.getHeight(null));
        Rectangle recUp = new Rectangle(x, y + 45 - imgUp.getHeight(null)
                , imgUp.getWidth(null), imgUp.getHeight(null));
        Rectangle recDown = new Rectangle(x, y, imgDown.getWidth(null),
                imgDown.getHeight(null));
        Rectangle recActor = new Rectangle(actor.getX(), actor.getY(),
                actor.getImages().getWidth(null), actor.getImages().getHeight(null));
        if (recLeft.intersects(recActor) || recDown.intersects(recActor) || recRight.intersects(recActor)
                || recUp.intersects(recActor)) {
            return true;
        }
        return false;
    }


    public boolean isImpactBomb(Bomb bomb) {
        Rectangle recLeft = new Rectangle(x + 45 - imgLeft.getWidth(null), y, imgLeft.getWidth(null),
                imgLeft.getHeight(null));
        Rectangle recRight = new Rectangle(x, y, imgRight.getWidth(null)
                , imgRight.getHeight(null));
        Rectangle recUp = new Rectangle(x, y + 45 - imgUp.getHeight(null), imgUp.getWidth(null),
                imgUp.getHeight(null));
        Rectangle recDown = new Rectangle(x, y, imgDown.getWidth(null),
                imgDown.getHeight(null));
        Rectangle recBomb = new Rectangle(bomb.getX(), bomb.getY(), bomb.getImage().getWidth(null),
                bomb.getImage().getHeight(null));

        if (recLeft.intersects(recBomb) || recDown.intersects(recBomb) || recRight.intersects(recBomb) || recUp.intersects(recBomb)) {
            return true;
        }
        return false;
    }

    public boolean impactBox(int x, int y, int width, int height, MapBomb map) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle rectBox = new Rectangle(map.getX(), map.getY(),
                map.getImage().getWidth(null), map.getImage().getHeight(null));
        return rect.intersects(rectBox);
    }

    public boolean isImpactBox(MapBomb mapBomb) {
        if (mapBomb.getBit() == 1 || mapBomb.getBit() == 3) {
            return false;
        }

        Rectangle recLeft = new Rectangle(x - imgLeft.getWidth(null) / 2, y, imgLeft.getWidth(null),
                imgLeft.getHeight(null));
        Rectangle recRight = new Rectangle(x, y, imgRight.getWidth(null)
                , imgRight.getHeight(null));
        Rectangle recUp = new Rectangle(x, y - imgUp.getHeight(null) / 2, imgUp.getWidth(null),
                imgUp.getHeight(null));
        Rectangle recDown = new Rectangle(x, y, imgDown.getWidth(null),
                imgDown.getHeight(null));
        Rectangle recBox = new Rectangle(mapBomb.getX(), mapBomb.getY(), mapBomb.getImage().getWidth(null),
                mapBomb.getImage().getHeight(null));
        if (recLeft.intersects(recBox) || recDown.intersects(recBox) || recRight.intersects(recBox) || recUp.intersects(recBox)) {
            return true;
        }
        return false;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public void deadlineBomb() {
        if (timeLine > 0) {
            timeLine--;
        }
    }

}
