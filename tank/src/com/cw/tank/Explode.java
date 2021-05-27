package com.cw.tank;

import java.awt.*;

/**
 * @author cassie on 2021/5/27.
 */
public class Explode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    private boolean living = true;
    TankFrame tf;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if (step >= ResourceMgr.explodes.length) {
            step = 0;
        }
    }

}
