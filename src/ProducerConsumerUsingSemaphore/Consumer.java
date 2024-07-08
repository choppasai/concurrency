package ProducerConsumerUsingSemaphore;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private Semaphore consumerSemaphore;
    private Semaphore producerSemaphore;
    private Store store;
    private static final Logger logger = Logger.getLogger(Consumer.class.getName());
    public Consumer( Semaphore producerSemaphore,Semaphore consumerSemaphore,Store store){
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.store = store;
    }
    @Override
    public void run(){
        try{

            logger.log(Level.INFO, "{0}: Going to Acquire consumer semaphore.", Thread.currentThread().getName());

            consumerSemaphore.acquire();

            logger.log(Level.INFO, "{0}: Acquired consumer semaphore.", Thread.currentThread().getName());

            store.sub();

        }
        catch (Exception e){
            throw new IllegalStateException("no products to consume");
        }
        finally {
            logger.log(Level.INFO,"{0}: Going to release product semaphore", Thread.currentThread().getName());
            producerSemaphore.release();
            logger.log(Level.INFO,"{0}: released product semaphore", Thread.currentThread().getName());
        }

    }
}
