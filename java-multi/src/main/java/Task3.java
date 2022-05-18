import java.util.ArrayList;
import java.util.List;

public class Task3 {

    private static final MessageBus messageBus = new MessageBus();

    public static void main(String[] args) throws InterruptedException {
        Runnable createMessage = () -> {
            try {
                messageBus.producer("Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable readMessage = () -> {
            try {
                messageBus.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        while (true){
            Thread t1 = new Thread(createMessage);
            Thread t2 = new Thread(createMessage);
            Thread t3 = new Thread(createMessage);
            Thread t4 = new Thread(readMessage);
            Thread t5 = new Thread(readMessage);

            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();

            Thread.sleep(1000);
        }
    }
}

class MessageBus{
    private final List<String> buffer = new ArrayList<>();
    private static final int MAX_CAPACITY = 10;

    public synchronized void producer(String message) throws InterruptedException {
        while (buffer.size() == MAX_CAPACITY){
            wait();
        }
        buffer.add(message);
        System.out.println("Message added: " + message);
        notify();
    }

    public synchronized void consumer() throws InterruptedException {
        while (buffer.size() == 0){
            wait();
        }
        String message = buffer.get(0);
        buffer.remove(0);
        System.out.println("Message removed: " + message);
        notify();
    }
}
