package com.cw.tank.cor;

import com.cw.tank.GameObject;
import com.cw.tank.Tank;

/**
 * @author cassie on 2021/5/31.
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.back();
                t2.back();
            }

        }
        return true;
    }
}
