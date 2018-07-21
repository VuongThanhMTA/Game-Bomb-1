package com.thanh.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Monster extends Actor {
    private Random random = new Random();

    public Monster(int x, int y,int speed) {
        super(x, y,speed);
        images = new Image[4];

        images[0] = new ImageIcon(getClass().getResource("/images/monster_left.png")).getImage();
        images[1] = new ImageIcon(getClass().getResource("/images/monster_right.png")).getImage();
        images[2] = new ImageIcon(getClass().getResource("/images/monster_up.png")).getImage();
        images[3] = new ImageIcon(getClass().getResource("/images/monster_down.png")).getImage();
        orient = RIGHT;
    }
    public Image getImage(){
        return images[orient];
    }

    public void changeOrient(int newOrient) {
       // int percent = random.nextInt(501);
        //if (percent >= 490) {
            orient =newOrient;
       // }

    }

    public Rectangle getRecMonster(){
        Rectangle rec1 = new Rectangle(x,y,images[orient].getWidth(null),
                images[orient].getHeight(null)-23);
        return rec1;
    }

    public boolean isImpactMonster(Monster monster){
        Rectangle rec1 = new Rectangle(x,y,images[orient].getWidth(null),
                images[orient].getHeight(null));
        Rectangle rec2 = new Rectangle(monster.getX(),monster.getY(),monster.getImage().getWidth(null),
                monster.getImage().getHeight(null));

        return rec1.intersects(rec2);
    }
//    @Override
//    public boolean move(int count){
//        //changeOrient();
//        super.move(count);
//
//        return true;
//    }

}
