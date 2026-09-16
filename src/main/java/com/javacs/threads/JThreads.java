package com.javacs.threads;

public class JThreads {
    /*
     * parallelization  : Executing multiple threads in parallel.
     * concurrency      : Tasks are not performed simultaneously; the CPU
     *                    simply switches between threads very rapidly.
     * [Race Condition]     -> If different threads modify a single piece of data, there is a possibility of error.
     * [synchronized]       -> Queueing system.
     *                      -> The guarantee is simply that the two threads do not enter simultaneously.
     * [critical section]   -> The part of the program that operates on shared data
     *                         and requires protection against concurrent access.
     *
     * Multithreading is typically employed for four purposes:
     *      [1] better CPU utilization
     *      [2] utilization of multiple cores
     *      [3] improved application responsiveness
     *      [4] fair resource allocation
     */

    public void runDownloadThread() {
        Thread downloadThread = new Thread(() -> {
            System.out.println("download_Thread");
        });
        downloadThread.start();
    }
}
