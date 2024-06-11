package ImageProcessing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) {
        int[][] array = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}

        };
        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        Divide array into quadrants and assign each quadrant to a thread.
        int size = array.length;
        int row = size/2;
        int col = row;
        ArrayRepaintingTask arrayRepaintingTask1 = new ArrayRepaintingTask(array,0,row,0,col);
        ArrayRepaintingTask arrayRepaintingTask2 = new ArrayRepaintingTask(array,0,row,col,size);
        ArrayRepaintingTask arrayRepaintingTask3 = new ArrayRepaintingTask(array,row,size,0,col);
        ArrayRepaintingTask arrayRepaintingTask4 = new ArrayRepaintingTask(array,row,size,col,size);
        executorService.submit(arrayRepaintingTask1);

        executorService.submit(arrayRepaintingTask2);
        executorService.submit(arrayRepaintingTask3);
        executorService.submit(arrayRepaintingTask4);
        executorService.shutdown();
        while(!executorService.isTerminated()){
            System.out.println("Still process is running");
        }
        System.out.println("Here is the expected result ");
        for (int[] ints : array) {
            for (int j = 0; j < size; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println(" ");
        }

    }
}
