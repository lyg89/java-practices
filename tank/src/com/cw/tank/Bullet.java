package com.cw.tank;

import java.awt.*;

/**
 * @author cassie on 2021/5/22.
 */
public class Bullet {

    private static final int SPEED = 10;
    private int x, y;
    private Dir dir;
    static final int WIDTH = ResourceMgr.bulletL.getWidth();
    static final int HEIGHT = ResourceMgr.bulletL.getHeight();

    private boolean living = true;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bulletList.remove(this);
        }


        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.living = false;
        }
    }

    public void collideWith(Tank tank) {
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (rect1.intersects(rect2)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
