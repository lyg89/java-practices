package com.cw.tank.cor;

import com.cw.tank.Bullet;
import com.cw.tank.GameObject;
import com.cw.tank.Wall;

/**
 * @author cassie on 2021/5/31.
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;

            if (b.getRect().intersects(w.rect)) {
                b.die();
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
