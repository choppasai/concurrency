package ProducerConsumerUsingSemaphore;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        int maxCapacity = 10;
        Semaphore producerSemaphore = new Semaphore(maxCapacity);
        Semaphore consumerSemaphore = new Semaphore(0);
        ExecutorService ex = Executors.newCachedThreadPool();
        Store store = new Store();
        for(int i=0;i<5;i++){
            Producer p = new Producer(producerSemaphore,consumerSemaphore,store);
            ex.submit(p);

        }
        for(int i=0;i<5;i++){

            Consumer c = new Consumer(producerSemaphore,consumerSemaphore,store);
            ex.submit(c);

        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ex.shutdown();
            while(!ex.isTerminated()){
                try{
                    ex.awaitTermination(2, TimeUnit.SECONDS);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Final store capacity: " + store.getCapacity());
        }));
        System.out.println(store.getCapacity());
    }

    private static void setupLogger() {
        try {
            FileHandler fileHandler = new FileHandler("producer_consumer.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger.getLogger("").addHandler(fileHandler);
        } catch (IOException e) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, "Failed to set up logger", e);
        }
    }
}
