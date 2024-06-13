package AdderSubtractorAtomicDataTypes;


import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Adder implements Callable<Integer> {
    private final AtomicInteger atomicInteger;
    public Adder(AtomicInteger atomicInteger){
        this.atomicInteger = atomicInteger;
    }
    @Override
    public Integer call(){
        int c = atomicInteger.get();
        for(int i=0;i<100;i++){
            c = atomicInteger.getAndIncrement();
        }

        return c;
    }
}
