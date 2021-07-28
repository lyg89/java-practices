package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class ContainerOperatorTest2 {

    private volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {

        ContainerOperatorTest2 test = new ContainerOperatorTest2();
        Object lock = new Object();


        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                while (true) {
                    if (test.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                System.out.println("t2 退出");
                lock.notify();
            }
        }, "t2").start();


        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1启动");
                for (int i = 1; i <= 10; i++) {
                    test.add(i);
                    System.out.println("t1 add array element: " + i);

                    if (i == 5) {
                        lock.notify();


                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();

    }
}
