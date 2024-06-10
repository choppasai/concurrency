package DownloadManager;

import java.util.concurrent.ExecutorService;

public class DownloadTask implements Runnable{
    String file;
    DownloadTask(String file){
        this.file = file;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println(file+" Downloading - "+ i+"%");
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException exception){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(file +" downloaded 100%");
    }
}
