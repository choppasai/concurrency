package HydrogenAndOxygenLeetCode;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    To Make HHO consistently
    Please refer leetcode problem number 1117
 */

//Release(permit) will release at once. so the available permit will increase the permit
public class H2O {
    private Semaphore h;
    private Semaphore o;
    private Logger logger = Logger.getLogger(H2O.class.getName());
    public H2O(Semaphore h,Semaphore o){
        this.o = o;
        this.h = h;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        logger.log(Level.INFO,"{0}: hydrogen is going to acquire and available permits are " +h.availablePermits(), Thread.currentThread().getName());
        try{
            h.acquire();
            releaseHydrogen.run();

            logger.log(Level.INFO,"{0}: hydrogen acquired and executed run, available permits are "+
                    h.availablePermits()+" "+o.availablePermits(), Thread.currentThread().getName());
        }
        finally {
            o.release();

            logger.log(Level.INFO,"{0}: oxygen released and available permits for oxygen is "
                    +o.availablePermits()+" permit for hydrogen is "+h.availablePermits(), Thread.currentThread().getName());
        }

    }
    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        try{
            o.acquire(2);
            releaseOxygen.run();
            logger.log(Level.INFO,"{0}: oxygen acquired and executed run, available permits are "+
                    o.availablePermits()+" hydrogen permit is"+h.availablePermits(), Thread.currentThread().getName());
        }
        finally {
            h.release(2);
            logger.log(Level.INFO,"{0}: hydrogen released and available permits are "
                    +h.availablePermits()+" oxygen permit "+o.availablePermits(), Thread.currentThread().getName());
        }

    }

}
