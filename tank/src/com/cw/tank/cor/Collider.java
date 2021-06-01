package com.cw.tank.cor;

import com.cw.tank.GameObject;

/**
 * @author cassie on 2021/5/31.
 */
public interface Collider {

    boolean collide(GameObject o1, GameObject o2);
}
