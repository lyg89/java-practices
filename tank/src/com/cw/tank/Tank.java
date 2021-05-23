package com.cw.tank;

import java.awt.*;

/**
 * @author cassie on 2021/5/22.
 */
public class Tank {

    private int x, y;

    private Dir dir;

    private static final int SPEED = 5;

    private boolean moving = false;

    private TankFrame tankFrame;

    static final int WIDTH = ResourceMgr.tankL.getWidth();
    static final int HEIGHT = ResourceMgr.tankL.getHeight();

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        if (!moving) return;
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
    }

    /**
     * 这里为什么不直接返回bullet？
     * 不灵活，返回类型固定了，如果后续要求返回多颗子弹、不同类型子弹则需要修改
     */
    public void fire() {
        int bX = x + WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bulletList.add(new Bullet(bX, bY, this.dir, this.tankFrame));
    }
}
