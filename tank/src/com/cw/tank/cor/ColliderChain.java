package com.cw.tank.cor;

import com.cw.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author cassie on 2021/6/1.
 */
public class ColliderChain implements Collider {

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        // 通过配置文件自动注入
        colliders.add(new BulletTankCollider());
        colliders.add(new TankTankCollider());
    }

    public void add(Collider collider) {
        this.colliders.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
