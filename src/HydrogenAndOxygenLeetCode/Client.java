package HydrogenAndOxygenLeetCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Client {
    public static void main(String[] args) {
//        String str = "OOHHHH";
        String str = "OOOHHHHHH";
        Semaphore h = new Semaphore(2);
        Semaphore o = new Semaphore(0);
        H2O h2O = new H2O(h,o);
        Runnable releaseHydrogen = () -> System.out.print("H");
        Runnable releaseOxygen = () -> System.out.print("O");
        ExecutorService ex = Executors.newCachedThreadPool();

        for(char i:str.toCharArray()){
            if (i=='H'){

                ex.submit(()-> {
                    try{
                        h2O.hydrogen(releaseHydrogen);
                    }
                    catch (Exception e){
                        throw new IllegalStateException("not h");
                    }
                });
            }
            else {
                ex.submit(() -> {
                    try {
                        h2O.oxygen(releaseOxygen);
                    }
                    catch (Exception e){
                        throw new IllegalStateException("not oxygen ");
                    }
                });
            }
        }
    }
}
