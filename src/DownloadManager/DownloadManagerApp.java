package DownloadManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManagerApp {
    public static void main(String[] args) {
        List<String> fileList = List.of("file1","file2","file3","file4","file5");
        DownloadManger downloadManger = new DownloadManger(5);
        downloadManger.downloadFiles(fileList);
        for(int i=0;i<5;i++){
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException interruptedException){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("All files downloaded successfully 😊😊😊");
    }
}
