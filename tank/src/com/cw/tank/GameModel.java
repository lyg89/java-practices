package com.cw.tank;

import com.cw.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cassie on 2021/5/31.
 */
public class GameModel {

    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
    List<GameObject> gameObjects = new ArrayList<>();

    ColliderChain colliderChain = new ColliderChain();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 创建敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            this.gameObjects.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }
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
