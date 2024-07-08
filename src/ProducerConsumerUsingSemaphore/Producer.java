package ProducerConsumerUsingSemaphore;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private Semaphore consumerSemaphore;
    private Semaphore producerSemaphore;
    private Store store;
    private static final Logger logger = Logger.getLogger(Producer.class.getName());
    public Producer(Semaphore producerSemaphore,Semaphore consumerSemaphore,Store store){
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
        this.store = store;
    }

    @Override
    public void run() {

//        while(true){
            try{
                logger.log(Level.INFO,"{2}: Going to acquire product semaphore", Thread.currentThread().getName());
                producerSemaphore.acquire();
                store.add();
                logger.log(Level.INFO,"{0}: acquired product semaphore", Thread.currentThread().getName());

            }
            catch (Exception e){
                throw new IllegalStateException("no more producers are free");
            }
            finally {
                logger.log(Level.INFO,"{2}: Going to release consumer semaphore", Thread.currentThread().getName());
                consumerSemaphore.release();
                logger.log(Level.INFO,"{2}: released consumer semaphore", Thread.currentThread().getName());
            }
//        }

    }
}
