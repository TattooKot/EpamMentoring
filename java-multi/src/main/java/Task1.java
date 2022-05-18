import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {

    static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        Runnable add = () -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                map.put(ThreadLocalRandom.current().nextInt(), ThreadLocalRandom.current().nextInt(0, 10));
            }
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("Adding element in " + Thread.currentThread().getName() + ": " + time);
        };

        Runnable sum = () -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                List<Integer> integers = new ArrayList<>(map.values());
                integers.stream()
                        .mapToInt(Integer::intValue)
                        .sum();
            }
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("Summing element in " + Thread.currentThread().getName() + ": " + time);
        };


        long start = System.currentTimeMillis();
        Thread t1 = new Thread(add);
        Thread t2 = new Thread(sum);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        long finish = System.currentTimeMillis();
        long time = finish - start;
        System.out.println("Thread dead in:  " + time);
    }
}

//Java 11 = 3040, 2571, 2772, 3168, 2846
//Java 10 = 2160, 1845, 2363, 2663, 2598
//Java 8 = 2737, 2546, 2999, 2246, 2445
//Java 6 = 1385, 894, 862, 1150, 1243
