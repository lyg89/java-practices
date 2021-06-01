package com.cw.tank;

import java.awt.*;

/**
 * @author cassie on 2021/5/22.
 */
public class Bullet extends GameObject {

    private static final int SPEED = 10;
    private int x, y;
    private Dir dir;
    public static final int WIDTH = ResourceMgr.bulletL.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletL.getHeight();

    private Rectangle rect = new Rectangle();

    private boolean living = true;
    private GameModel gm;

    private Group group;

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        gm.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            gm.remove(this);
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

        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.living = false;
        }
    }

    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return false;
        }

        if (rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gm.add(new Explode(eX, eY, gm));
            return true;
        }
        return false;
    }

    private void die() {
        this.living = false;
    }
}
