package com.javacs.projects.DownloadManagerPro;

import org.jetbrains.annotations.NotNull;

import java.awt.desktop.SystemSleepEvent;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadTaskPro {

    String[] fileNames;
    private volatile boolean isComplete = false;
    private final List<?> listOfFiles;
    private AtomicInteger downloadPercentage = new AtomicInteger(0);
    private final Semaphore semaphore = new Semaphore(3);

    public DownloadTaskPro(@NotNull List<?> listOfFiles) {

        if (listOfFiles.isEmpty() || listOfFiles.contains(null))
            throw new RuntimeException();

        fileNames = new String[listOfFiles.size()];
        this.listOfFiles = listOfFiles;
    }

    public void penetrateArrayList() {
        int setUpIndex = 0;
        for (Object file : listOfFiles) {
            fileNames[setUpIndex] = file.toString();
            setUpIndex++;
        }
        calculateDistribution(fileNames);
    }

    public void calculateDistribution(String[] list) {
        int size = fileNames.length;
        int forEachThread = (int) Math.floor((double)size / 3);
    }

    public void downloadPipeline1(int... index) {

        Thread downloadPipeLine_1 = new Thread(() ->  {

            for (int i = 0; i < index.length; i++) {
                while (!isComplete) {
                    System.out.println(fileNames[i]
                            + ": %"
                            + downloadPercentage.getAndIncrement()
                    );
                }
                if (downloadPercentage.get() > 100) {
                    isComplete = true;
                    downloadPercentage = new AtomicInteger(0);
                }
            }
        });
    }
}
