package com.cw.tank.cor;

import com.cw.tank.GameObject;
import com.cw.tank.Tank;
import com.cw.tank.Wall;

/**
 * @author cassie on 2021/5/31.
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if (t.getRect().intersects(w.rect)) {
                t.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
