package TicketBookingSystem;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

public class ReservationSystem implements Callable<String> {
    int seatNumber;
    String result;
    ConcurrentHashMap<Integer,Boolean> map;
    ConcurrentHashMap<String, List<Integer>> userMap;
    String userName;
    Lock lock;
    public ReservationSystem(int seatNumber, ConcurrentHashMap<Integer,Boolean> map, String result, ConcurrentHashMap<String,
            List<Integer>> userMap, String userName, Lock lock){
        this.seatNumber = seatNumber;
        this.map = map;
        this.result = result;
        this.userMap = userMap;
        this.userName = userName;
        this.lock = lock;

    }
    @Override
    public String call(){

        try{
            lock.lock();
            if(map.get(seatNumber)){
                result = "Seat is already booked";
            }
            else{
                map.put(seatNumber,true);
                if(userMap.containsKey(userName)){
                    userMap.get(userName).add(seatNumber);
                }
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(seatNumber);
                    userMap.put(userName,list);
                }
                result = "your seat booked successfully " +
                        "seat number is " + seatNumber;
            }
        }
        finally {
            lock.unlock();
        }


        return result;
    }

}
