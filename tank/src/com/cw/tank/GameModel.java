package com.cw.tank;

import com.cw.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cassie on 2021/5/31.
 */
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }

    Tank myTank;
    List<GameObject> gameObjects = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private GameModel() {
    }

    private void init() {
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 创建敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
        }

        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    public void add(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }


    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        // g.drawString("子弹的数量：" + bulletList.size(), 10, 60);
        // g.drawString("敌人的数量：" + tanks.size(), 10, 80);
        // g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                // collider.collide(gameObjects.get(i), gameObjects.get(j));
                // collider2.collide(gameObjects.get(i), gameObjects.get(j));
                colliderChain.collide(gameObjects.get(i), gameObjects.get(j));
            }
        }
        // collision detect
        // for (int i = 0; i < bulletList.size(); i++) {
        //     for (int j = 0; j < tanks.size(); j++) {
        //         bulletList.get(i).collideWith(tanks.get(j));
        //     }
        // }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
