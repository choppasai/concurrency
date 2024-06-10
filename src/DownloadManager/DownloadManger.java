package DownloadManager;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadManger {
    private final ExecutorService executorService;

    public DownloadManger(int threadPoolSize){
        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }
    public void downloadFiles(List<String> files){
        for(String file : files){
            DownloadTask downloadTask = new DownloadTask(file);
            executorService.shutdown();
        }
    }
    public void shutdownApplication(){
        executorService.shutdown();
    }

}
