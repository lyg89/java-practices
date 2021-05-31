package com.cw.tank;

/**
 * @author cassie on 2021/5/22.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();


        // new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(25);
            tankFrame.repaint();
        }
    }
}
