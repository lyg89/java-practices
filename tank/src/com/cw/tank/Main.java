package com.cw.tank;

/**
 * @author cassie on 2021/5/22.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 创建敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, tankFrame));
        }

        // new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(25);
            tankFrame.repaint();
        }
    }
}
