package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {

    static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        Runnable add = () -> {
            map.put(ThreadLocalRandom.current().nextInt(), ThreadLocalRandom.current().nextInt(0, 10));
        };

        Runnable sum = () -> {
            List<Integer> integers = new ArrayList<>(map.values());
            System.out.println(integers.stream().mapToInt(Integer::intValue).sum());
        };

        while (true) {
            Thread t1 = new Thread(add);
            Thread t2 = new Thread(sum);
            t1.start();
            t2.start();
            Thread.sleep(1000);
        }

    }
}
