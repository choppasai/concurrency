package AdderSubtractorMutexLock;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class Subtractor implements Callable<Integer> {
    private final Count count;
    private final Lock lock;

    public Subtractor(Count count, Lock lock) {
        this.count = count;
        this.lock = lock;
    }
    @Override
    public Integer call(){
        Integer c = count.value;
        for(int i=0;i<100;i++){
//            lock.lock();
            synchronized (count){
                count.value--;
            }

//            lock.unlock();
        }

        return c;
    }
}
