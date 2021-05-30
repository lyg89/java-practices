package com.cw.tank.abstractfactory;

import com.cw.tank.ResourceMgr;
import com.cw.tank.TankFrame;

import java.awt.*;

/**
 * @author cassie on 2021/5/27.
 */
public class RectExplode extends BaseExplode {

    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;

    TankFrame tf;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        // new Audio("audio/explode.wav").play();
    }

    @Override
    public void paint(Graphics g) {
        // g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, step * 10, step * 10);
        step++;

        if (step >= 10) {
            tf.explodes.remove(this);
        }

        g.setColor(c);
    }

}
