package TicketBookingSystem;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OnlineReservationSystem {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String result = "not booked";
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ConcurrentHashMap<Integer,Boolean> map = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, List<Integer>> userMap = new ConcurrentHashMap<>();
        Lock lock = new ReentrantLock();
        String userName1 = "user1";
        String userName2 = "user2";
        String userName3 = "user3";
        for(int i=1;i<=10;i++){
            map.put(i,false);
        }
        ReservationSystem reservationSystem1 = new ReservationSystem(1,map,result,userMap,userName1,lock);
        ReservationSystem reservationSystem2 = new ReservationSystem(5,map,result,userMap,userName2,lock);
        ReservationSystem reservationSystem3 = new ReservationSystem(2,map,result,userMap,userName2,lock);
        Future<String> t1 = executorService.submit(reservationSystem1);
        Future<String> t2 = executorService.submit(reservationSystem2);
        Future<String> t3 = executorService.submit(reservationSystem3);
        String thread1 = t1.get();
        String thread2 = t2.get();
        String thread3 = t3.get();
        for(Map.Entry<String, List<Integer>> set : userMap.entrySet()){
            System.out.println(set.getKey()+" "+set.getValue());
        }


    }
}
