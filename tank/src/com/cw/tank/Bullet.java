package com.cw.tank;

/**
 * @author cassie on 2021/5/22.
 */
public class Bullet {

    private static final int SPEED = 10;
    private int x, y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
