package container.fromHashTableToCHM;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * HashTable性能测试类：并发写入读取测试
 * @author Flash
 */
public class TestHashTable {

    private static final Map<UUID, UUID> hashTable = new Hashtable<>();

    private static final int COUNT = Constants.COUNT;
    private static final int THREAD_COUNT = Constants.THREAD_COUNT;

    private static final UUID[] keys = new UUID[COUNT];
    private static final UUID[] values = new UUID[COUNT];

    static {
        for (int i = 0; i < COUNT; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class WriteThread extends Thread {
        int start;
        int gap = COUNT / THREAD_COUNT;

        public WriteThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            int min = Math.min(start + gap, COUNT);
            for (int i = start; i < min; i++) {
                hashTable.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WriteThread(i * (COUNT / THREAD_COUNT));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("hashTable, write time is: %d ms, size: %d", (endTime - startTime), hashTable.size());
        System.out.println();
        System.out.println("---------------");
        long readStartTime = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    hashTable.get(keys[10]);
                }
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long readEndTime = System.currentTimeMillis();
        System.out.printf("hashTable, read time is: %d ms", readEndTime - readStartTime);
    }
}
