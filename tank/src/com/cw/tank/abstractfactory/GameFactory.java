package com.cw.tank.abstractfactory;

import com.cw.tank.Dir;
import com.cw.tank.Group;
import com.cw.tank.TankFrame;

/**
 * @author cassie on 2021/5/30.
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

}
