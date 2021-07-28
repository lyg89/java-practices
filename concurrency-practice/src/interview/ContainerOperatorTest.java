package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class ContainerOperatorTest {

    private volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }


    public static void main(String[] args) {

        ContainerOperatorTest test = new ContainerOperatorTest();
        new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    test.add(i);
                    System.out.println("t1 add array element: " + i);

//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
        }, "t1").start();


        new Thread(() -> {
            while (true) {
                if (test.size() == 5) {
                    System.out.println("array size is 5, t2 quit!");
                    break;
                }
            }
        }, "t2").start();
    }
}
