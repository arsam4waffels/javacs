package com.javacs.projects.DownloadManager;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DownloadManager {
    private final List<DownloadTask> downloadTaskList =
            new ArrayList<>();

    public DownloadManager() {
        downloadTaskList.add(new DownloadTask("photo.png"));
        downloadTaskList.add(new DownloadTask("video.mp4"));
        downloadTaskList.add(new DownloadTask("music.mp3"));
    }

    public void runnable() {

        try (ExecutorService executorService =
                     Executors.newFixedThreadPool(
                             downloadTaskList.size()
                     )) {

            List<Future<?>> futures = new ArrayList<>();

            for (DownloadTask downloadTask : downloadTaskList)
                futures.add(executorService.submit(
                        downloadTask::download
                ));

            for (Future<?> future : futures)
                future.get();
        }
        catch (RuntimeException
               | ExecutionException
               | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
