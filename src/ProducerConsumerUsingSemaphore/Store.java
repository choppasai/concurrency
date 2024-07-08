package ProducerConsumerUsingSemaphore;

public class Store {
    int capacity;


    public synchronized int getCapacity() {
        return capacity;
    }
    public synchronized void add(){
        System.out.println("producer produced :"+ ++capacity+" tshirts");
    }
    public synchronized void sub(){
        System.out.println("consumer consumed :"+ --capacity+" tshirts");
    }
}
