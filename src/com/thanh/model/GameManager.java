package com.thanh.model;

import com.thanh.gui.FrameBoom;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private Bomber myBomber;
    private ArrayList<Monster> arrMonster;
    private ArrayList<Bomb> arrBom;
    private ArrayList<BombExplosive> arrBombExplosives;
    private ArrayList<MapBomb> arrMap;
    private ArrayList<MapBomb> arrBox;
    private ArrayList<MapBomb> arrShawdow;
    private ArrayList<Item> arrItem;
    private ArrayList<HightScore> arrHightScore;
    private Image imageBG = new ImageIcon(getClass().getResource("/images/background_Play.png")).getImage();
    private Image imageInfo = new ImageIcon(getClass().getResource("/images/background_Info.png")).getImage();
    private Random random = new Random();
    private int round = 1;
    private int status = 0;
    private int timeDie = 0;


    public void initGame() {

        arrMonster = new ArrayList<>();
        arrBom = new ArrayList<>();
        arrBox = new ArrayList<>();
        arrShawdow = new ArrayList<>();
        arrItem = new ArrayList<>();
        arrBombExplosives = new ArrayList<>();

        switch (round) {
            case 1:
                myBomber = new Bomber(300, 380, 1, 1, 3);
                readMapBomb(round);
                readMapItem(round);
                initMonster();
                status = 0;
                break;
            case 2:
                myBomber.setNew(300, 380);
                readMapBomb(round);
                readMapItem(round);
                initMonster();
                status = 0;
                break;
            case 3:
                myBomber.setNew(500, 380);
                readMapBomb(round);
                readMapItem(round);
                initMonster();
                status = 0;
                break;

            default:
                break;
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(imageBG, 0, 0, null);
        drawInfo(g2d);
//        for (MapBomb mapBomb : arrMap) {
//            mapBomb.drawMapBomb(g2d);
//
//        }
        for (Item item : arrItem) {
            item.drawItem(g2d);

        }

        for (int i = 0; i < arrBom.size(); i++) {
            // if (arrBom.size() <= myBomber.sizeBomb) {
            arrBom.get(i).draw(g2d);
            // }
        }
        for (int i = 0; i < arrBombExplosives.size(); i++) {
            arrBombExplosives.get(i).draw(g2d);
        }
//        for (int i = 0; i <arrMap.size() ; i++) {
//            arrMap.get(i).drawMapBomb(g2d);
//        }


        for (MapBomb mapBomb : arrBox) {
            mapBomb.drawMapBomb(g2d);

        }
        for (int i = 0; i < arrMonster.size(); i++) {
            arrMonster.get(i).draw(g2d);
        }
        myBomber.draw(g2d);

        for (MapBomb mapBomb : arrShawdow) {
            mapBomb.drawMapBomb(g2d);

        }

    }

    public void drawInfo(Graphics2D g2d) {

        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.RED);
        g2d.drawImage(imageInfo, 678, 0, null);
        g2d.drawString("HEART", 700, 100);
        Image heart = new ImageIcon(getClass().getResource(
                "/images/heart_1.png")).getImage();
        if (myBomber.getHeart() == 3) {
            g2d.drawImage(heart, 750, 120, null);
            g2d.drawImage(heart, 775, 120, null);
            g2d.drawImage(heart, 800, 120, null);
        }
        if (myBomber.getHeart() == 2) {
            g2d.drawImage(heart, 760, 120, null);
            g2d.drawImage(heart, 790, 120, null);
        }
        if (myBomber.getHeart() == 1) {
            g2d.drawImage(heart, 775, 120, null);
        }

        g2d.drawString("SCORE : " + myBomber.getScore(), 700, 200);
    }

    public void initMonster() {
        switch (round) {
            case 1:
                arrMonster.add(new Monster(0, 1, 1));
                arrMonster.add(new Monster(300, -3, 1));
                arrMonster.add(new Monster(FrameBoom.WIDTH_FRAME / 2, FrameBoom.HEIGHT_FRAME / 2 + 70, 1));
                break;
            case 2:
                arrMonster.add(new Monster(0, 100, 1));
                arrMonster.add(new Monster(500, -3, 1));
                arrMonster.add(new Monster(0, 150, 1));
                arrMonster.add(new Monster(500, 300, 1));
                arrMonster.add(new Monster(50, 400, 1));
                arrMonster.add(new Monster(500, 200, 1));
                arrMonster.add(new Monster(FrameBoom.WIDTH_FRAME / 2, FrameBoom.HEIGHT_FRAME / 2 + 70, 1));
                break;
            case 3:
                break;
            default:
                break;
        }

    }

    public void innitBomb() {
        if (myBomber.getStatus() == Bomber.DEAD) {
            return;
        }
        int x = myBomber.getX();
        int y = myBomber.getY() + myBomber.getImages().getHeight(null) / 3;
        for (int i = 0; i < arrBom.size(); i++) {
            if (arrBom.get(i).isImpactBom(x, y)) {// không đặt 2 bom 1 chỗ
                return;
            }
        }

        if (arrBom.size() >= myBomber.getQuantityBomb()) {
            return;
        }
        //GameSound.getIstance().getAudio(GameSound.BOMB).play();
        Bomb mBom = new Bomb(x, y, myBomber.getSizeBomb(), 500);
        arrBom.add(mBom);
    }

    public void setNewBomber() {
        switch (round) {
            case 1:
                myBomber.setNew(FrameBoom.WIDTH_FRAME / 2, FrameBoom.HEIGHT_FRAME - 100);
                break;
            case 2:
                myBomber.setNew(315, 270);
                break;
            case 3:
                myBomber.setNew(315, 495);
                break;

            default:
                break;
        }
    }

    public void setRunBomber() {
        if (arrBom.size() > 0) {
            if (arrBom.get(arrBom.size() - 1).impactBomber(myBomber) == false) {
                myBomber.setRunBomb(Bomber.DISALLOW_RUN);
            }
        }
    }

    public void moveBomber(int newOrient) {
        myBomber.changeOrient(newOrient);
        myBomber.move(arrBox, arrBom, arrMonster);
    }

    public void changeOrientAll() {
        for (int i = 0; i < arrMonster.size(); i++) {
            int orient = random.nextInt(4);
            arrMonster.get(i).changeOrient(orient);
        }
    }

    // doi huong monster va khi monster va chạm thì đổi hướng
    public void moveMonster() {
        for (int i = arrMonster.size() - 1; i >= 0; i--) {
            if (arrMonster.get(i).move(arrBox, arrBom, arrMonster) == false) {
                int orient = random.nextInt(4);
                arrMonster.get(i).changeOrient(orient);
            }
        }
    }


    // tạo bomb nổ và xóa bom đặt, xóa bomb nổ
    private void bombBang() {
        // tao bombang xoa bomb
        for (int i = 0; i < arrBom.size(); i++) {
            arrBom.get(i).deadlineBomb();
            if (arrBom.get(i).getTimeLine() == 50) {
                BombExplosive bomBang = new BombExplosive(arrBom.get(i).getX(), arrBom
                        .get(i).getY(), arrBom.get(i).getSize(),arrBox);
                arrBombExplosives.add(bomBang);
                arrBom.remove(i);
            }
        }

        //2 bom no gan nhau
        for (int i = 0; i < arrBombExplosives.size(); i++) {
            for (int j = 0; j < arrBom.size(); j++) {
                if (arrBombExplosives.get(i).isImpactBomb(arrBom.get(j))) {
                    BombExplosive bomBang = new BombExplosive(arrBom.get(j).getX(),
                            arrBom.get(j).getY(), arrBom.get(j).getSize(),arrBox);
                    arrBombExplosives.add(bomBang);
                    arrBom.remove(j);
                }
            }
        }
        // bombang va cham monster
        for (int i = 0; i < arrBombExplosives.size(); i++) {
            arrBombExplosives.get(i).deadlineBomb();
            for (int j = 0; j < arrMonster.size(); j++) {
                if (arrBombExplosives.get(i).isImpactActor(arrMonster.get(j))) {
                    myBomber.setScore(myBomber.getScore() + 1);
                    arrMonster.remove(j);
                }
            }
            if (arrBombExplosives.get(i).getTimeLine() == 0) {
                arrBombExplosives.remove(i);
            }
        }

        for (int j = 0; j < arrBox.size(); j++) {
            //if (arrBox.get(j).getBit() != 1 && arrShawdow.get(j).getBit() != 3) {
            for (int i = 0; i < arrBombExplosives.size(); i++) {
                if (arrBombExplosives.get(i).isImpactBox(arrBox.get(j))) {

                    arrBox.remove(j);
                    arrShawdow.remove(j);

                }
            }
            //  }
        }
    }

    public void eatItem() {
        for (int i = 0; i < arrItem.size(); i++) {
            if (myBomber.isImpactItem(arrItem.get(i))) {
                if (arrItem.get(i).getType() == Item.ITEM_BOMB) {
                    myBomber.setQuantityBomb(myBomber.getQuantityBomb() + 1);
                    arrItem.remove(i);
                    break;
                }
                if (arrItem.get(i).getType() == Item.ITEM_BOMBSIZE) {
                    myBomber.setSizeBomb(myBomber.getSizeBomb() + 1);
                    arrItem.remove(i);
                    break;
                }
                if (arrItem.get(i).getType() == Item.ITEM_SHOE) {
                    myBomber.setSpeed(myBomber.getSpeed() + 1);
                    arrItem.remove(i);
                    break;
                }

            }
        }
    }


    public void checkWinAndLose() {
        if (myBomber.getHeart() == 0) {
            round = 1;
            status = 1;
        }
        if (arrMonster.size() == 0) {
            if (round == 3) {
                status = 3;
                round = 1;
                return;
            }
            round = round + 1;
            status = 2;
        }
    }

    public void checkDie(ArrayList<Monster> arrMonster, ArrayList<BombExplosive> arrBombEx) {
        for (int i = 0; i < arrBombEx.size(); i++) {
            if (arrBombEx.get(i).isImpactActor(myBomber) && myBomber.getStatus() == Bomber.ALIVE) {
                myBomber.setOrient(4);
                if (myBomber.getStatus() == Bomber.DEAD) {
                    return;
                }
                myBomber.setHeart(myBomber.getHeart() - 1);
                myBomber.setStatus(Bomber.DEAD);
            }
        }
        for (int i = 0; i < arrMonster.size(); i++) {
            if (myBomber.isImpactMonster(arrMonster.get(i))) {
                myBomber.setOrient(5);
                if (myBomber.getStatus() == Bomber.DEAD) {
                    return;
                }
                myBomber.setHeart(myBomber.getHeart() - 1);
                myBomber.setStatus(Bomber.DEAD);
            }
        }
    }

    private void readMapBomb(int lever) {
        arrMap = new ArrayList<>();
        try {
            File fileMap = new File("src/maps/map" + lever + ".txt");
            // đọc từng dòng dùng bufferReader
            FileReader reader = new FileReader(fileMap);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();// đọc 1 dòng
            int n = 0;// chỉ số dòng
            while (line != null) {
                // duyệt từng ký tự trên dòng
                for (int i = 0; i < line.length(); i++) {

                    char c = line.charAt(i);
                    int bit = Integer.parseInt(c + "");

                    if (bit != 5) {

                        int x = i * 45;
                        int y = 0;
                        y = n * 45;
                        if (bit == 1 || bit == 2) {
                            // x = x-23;
                            y = y - 23;// rộng ảnh
                        }
                        if (bit == 4) {
                            // x = x-23;
                            y = y + 15;
                            //y = n *7;// rộng ảnh
                        }
                        MapBomb mapBomb = new MapBomb(x, y, bit);
                        if (mapBomb.getBit() == 1 || mapBomb.getBit() == 2) {
                            arrBox.add(mapBomb);
                        }
                        //arrMap.add(mapBomb);
                        if (mapBomb.getBit() == 3 || mapBomb.getBit() == 4) {
                            arrShawdow.add(mapBomb);
                        }
                    }
                }
                line = bufferedReader.readLine();
                n++; // dòng tiếp theo
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void readMapItem(int lever) {
        //arrMap = new ArrayList<>();
        try {
            File fileMap = new File("src/maps/item" + lever + ".txt");
            // đọc từng dòng dùng bufferReader
            FileReader reader = new FileReader(fileMap);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();// đọc 1 dòng
            int n = 0;// chỉ số dòng
            while (line != null) {
                // duyệt từng ký tự trên dòng
                for (int i = 0; i < line.length(); i++) {

                    char c = line.charAt(i);
                    int bit = Integer.parseInt(c + "");
                    if (bit != 5) {
                        int x = i * 45;
                        int y = n * 45 - 23;
                        String img = "";
                        if (bit == 6) {
                            img = "/images/item_bomb.png";
                        } else if (bit == 7) {
                            img = "/images/item_bombsize.png";
                        } else {
                            img = "/images/item_shoe.png";
                        }
                        Item item = new Item(x, y, bit, img);
                        arrItem.add(item);
                    }
                }
                line = bufferedReader.readLine();
                n++; // dòng tiếp theo
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public int getStatus() {
        return status;
    }

    public void AI() {
        moveMonster();
        bombBang();
        checkDie(arrMonster, arrBombExplosives);
        checkWinAndLose();
        eatItem();
        if (myBomber.status == Actor.DEAD) {
            timeDie++;
            if (timeDie == 300) {
                myBomber.setNew(0, FrameBoom.HEIGHT_FRAME - 100);
                timeDie = 0;
            }
        }

    }
}
