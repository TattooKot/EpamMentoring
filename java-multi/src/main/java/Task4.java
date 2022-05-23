import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task4 {

}

class BlockingObjectPool {
    private final List<Object> buffer = new ArrayList<>();
    private final int size;

    BlockingObjectPool(int size){
        this.size = size;
    }

    public synchronized void take(Object object) throws InterruptedException {
        while (buffer.size() == size){
            wait();
        }
        buffer.add(object);
        notify();
    }

    public synchronized Object get() throws InterruptedException {
        while (buffer.size() == 0){
            wait();
        }
        Object object = buffer.get(0);
        buffer.remove(0);
        System.out.println("Object removed: " + object);
        notify();
        return object;
    }
}
