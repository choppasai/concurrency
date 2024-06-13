package AdderSubtractorMutexLock;


import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class Adder implements Callable<Integer> {
    private final Count count;
    private final Lock lock;
    public Adder(Count count, Lock lock){
        this.count = count;
        this.lock = lock;
    }
    @Override
    public Integer call(){
        Integer c = count.value;
        for(int i=0;i<100;i++){
            //         critical section
//            lock.lock();
//            In Java, the synchronized keyword is not associated with any specific class. Instead, it is a built-in language feature used to ensure that a block of code or method is executed by only one thread at a time. The synchronized keyword can be applied to methods or blocks of code to achieve mutual exclusion and thread safety.
            synchronized (count){
                count.value++;
            }

//            lock.unlock();
        }

        return c;
    }
}
