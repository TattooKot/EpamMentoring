import java.util.ArrayList;
import java.util.List;

/** * Pool that block when it has not any items or it full */

public class Task4 {

}

class BlockingObjectPool<T> {
    private final List<T> buffer = new ArrayList<>();
    private final int size;

    BlockingObjectPool(int size){
        this.size = size;
    }

    public synchronized void get(T object) throws InterruptedException {
        while (buffer.size() == size){
            wait();
        }
        buffer.add(object);
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while (buffer.size() == 0){
            wait();
        }
        T object = buffer.get(0);
        buffer.remove(0);
        System.out.println("Object removed: " + object);
        notify();
    }
}
