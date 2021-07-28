package container.fromHashTableToCHM;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class TestHashMap {

    private static final Map<UUID, UUID> hashMap = new HashMap<>();

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
            for (int i = start; i < start + gap; i++) {
                hashMap.put(keys[i], values[i]);
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
        System.out.printf("hashMap, write time is: %d ms, size: %d", (endTime - startTime), hashMap.size());
        System.out.println();
        System.out.println("---------------");
        long readStartTime = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000000; j++) {
                    hashMap.get(keys[10]);
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
        System.out.printf("hashMap, read time is: %d ms", readEndTime - readStartTime);
    }
}
