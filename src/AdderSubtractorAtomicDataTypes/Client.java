package AdderSubtractorAtomicDataTypes;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client {
    /*
    In Java, the java.util.concurrent.atomic package provides a set of classes that
    support atomic operations on variables. These classes are designed to be thread-safe
    and eliminate the need for explicit synchronization in certain scenarios.
    */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Count count = new Count();
        AtomicInteger atomicInteger = new AtomicInteger(count.value);

        Adder adder = new Adder(atomicInteger);
        Subtractor subtractor = new Subtractor(atomicInteger);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> c1 = executorService.submit(subtractor);
        Future<Integer> c2 = executorService.submit(adder);
        c1.get();
        c2.get();
        System.out.println(count.value);

    }
}
