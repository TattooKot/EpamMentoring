package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Task2 {

    static final List<Integer> integers = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Runnable add = () -> integers.add(ThreadLocalRandom.current().nextInt(0, 10));


        Runnable sum = () -> {
            synchronized (integers) {
                System.out.println("Sum: " + integers.stream().mapToInt(Integer::intValue).sum());
            }
        };


        Runnable abracadabra = () -> {
            synchronized (integers) {
                System.out.println("Abracadabra: " + Math.sqrt(integers.stream().mapToInt(i -> i * i).sum()));
            }
        };


        while (true) {
            Thread t1 = new Thread(add);
            Thread t2 = new Thread(sum);
            Thread t3 = new Thread(abracadabra);
            t1.start();
            t2.start();
            t3.start();
            Thread.sleep(1000);
        }
    }


}
