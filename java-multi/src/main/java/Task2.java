import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Task2 {

    static final CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        Runnable add = () -> {
            for (int i = 0; i < 10000; i++) {
                integers.add(ThreadLocalRandom.current().nextInt(0, 10));
            }
        };

        Runnable sum = () ->{
            for (int i = 0; i < 10000; i++) {
                System.out.println("Sum: " + integers.stream().mapToInt(Integer::intValue).sum());
            }
        };

        Runnable abracadabra = () ->{
            for (int i = 0; i < 10000; i++) {
                System.out.println("Abracadabra: " + Math.sqrt(integers.stream().mapToInt(n -> n * n).sum()));
            }
        };

        Thread t1 = new Thread(add);
        Thread t2 = new Thread(sum);
        Thread t3 = new Thread(abracadabra);
        t1.start();
        t2.start();
        t3.start();
    }
}
