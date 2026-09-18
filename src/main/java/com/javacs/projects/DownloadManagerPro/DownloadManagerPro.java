package com.javacs.projects.DownloadManagerPro;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DownloadManagerPro {

    private final List<String> files;
    private static final int MAX_THREAD_POOL = 3;

    public DownloadManagerPro(@NotNull List<String> files) {
        if (files.isEmpty() || files.contains(null))
            throw new RuntimeException();

        this.files = files;
    }

    public void start() throws InterruptedException {
        int size = files.size();
        int perThread = (int) Math.ceil((double) size / MAX_THREAD_POOL);

        CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD_POOL);
    }
}
