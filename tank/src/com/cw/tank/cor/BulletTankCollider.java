package com.cw.tank.cor;

import com.cw.tank.Bullet;
import com.cw.tank.GameObject;
import com.cw.tank.Tank;

/**
 * @author cassie on 2021/5/31.
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            // TODO: 2021/6/1 Copy code from method collideWith
            if (bullet.collideWith(tank)) {
                // 坦克与子弹相撞后不进行后续collider的处理
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
