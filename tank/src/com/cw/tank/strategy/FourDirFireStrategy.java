package com.cw.tank.strategy;

import com.cw.tank.Bullet;
import com.cw.tank.Dir;
import com.cw.tank.Tank;

/**
 * @author cassie on 2021/5/29.
 */
public class FourDirFireStrategy implements FireStrategy {

    private static final FourDirFireStrategy INSTANCE = new FourDirFireStrategy();

    private FourDirFireStrategy() {
    }

    public static FourDirFireStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bX, bY, dir, t.group);
        }

        // if(t.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
