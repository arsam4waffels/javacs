package com.javacs.projects.DownloadManager;

import java.util.concurrent.atomic.AtomicInteger;

public class DownloadTask {

    private final String fileName;
    private volatile boolean isDownloadComplete = false;

    private final AtomicInteger downloadPercentage =
            new AtomicInteger(0);

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    public void download() {

        Thread downloadThread = new Thread(() -> {

            while (!isDownloadComplete) {
                System.out.println(
                        fileName
                                + ": %"
                                + downloadPercentage.getAndIncrement()
                );

                try {
                    Thread.sleep(300);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (downloadPercentage.get() >= 100) {
                    isDownloadComplete = true;
                    return;
                }
            }
            System.out.println("[Download Completed]");
        });
        downloadThread.start();
        try {
            downloadThread.join();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
