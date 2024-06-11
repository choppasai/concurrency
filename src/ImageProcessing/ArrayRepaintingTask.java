package ImageProcessing;

import java.util.concurrent.ExecutorService;

public class ArrayRepaintingTask implements Runnable{
//    private final ExecutorService executorService;
    private final int[][] array;
    private final int rowStart;
    private final int rowEnd;
    private final int colStart;
    private final int colEnd;


    public ArrayRepaintingTask(int[][] array1, int rowStart, int rowEnd, int colStart, int colEnd) {
        this.array = array1;
        this.rowStart = rowStart;
        this.colEnd = colEnd;
        this.rowEnd = rowEnd;
        this.colStart = colStart;
    }
    @Override
    public void run(){
        for(int i=rowStart;i<rowEnd;i++){
            for(int j=colStart;j<colEnd;j++){
                array[i][j]*=2;
            }
        }
    }

}
