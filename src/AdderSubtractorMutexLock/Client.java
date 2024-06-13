package AdderSubtractorMutexLock;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock;


public class Client {
    /*
    Here 2 threads accessing same data at same time. To I am trying to synchronize using mutex locking.
    */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Count count = new Count();
        Lock lock = new ReentrantLock();
        Adder adder = new Adder(count,lock);
        Subtractor subtractor = new Subtractor(count,lock);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> c1 = executorService.submit(subtractor);
        Future<Integer> c2 = executorService.submit(adder);
        c1.get();
        c2.get();
        System.out.println(count.value);

    }
}
