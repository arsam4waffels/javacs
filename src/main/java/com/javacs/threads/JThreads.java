package com.javacs.threads;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class JThreads {
    /*
     * parallelization  : Executing multiple threads in parallel.
     * concurrency      : Tasks are not performed simultaneously; the CPU
     *                    simply switches between threads very rapidly.
     * multitasking     : operating system manages multiple programs or processes.
     * multithreading   : There are several execution paths within a program.
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
     *
     * == Thread State ==
     *      [0] getState()      : STATE
     *      [1] NEW             => built, but not yet executed
     *      [2] RUNNABLE        => threads start != executing on cpu rn
     *      [3] BLOCKED         => locked by 'synchronized'; wating for 'monitor-lock'
     *      [4] WAITING         => wating for smthing to happen
     *      [5] TIMED_WAITING   => Thread.sleep(1000);
     *      [6] TERMINATED      => finished executing
     *
     * [NOTE]   : you CANNOT re-start a thread => [IllegalThreadStateException]
     *
     *               start()
     *                  │
     *                  ▼
     *                 NEW
     *                  │
     *                  ▼
     *               RUNNABLE
     *        ┌─────────┴─────────┐
     *        ▼         ▼         ▼
     *     BLOCKED   WAITING  TIMED_WAITING
     *        |         |         |
     *        └─────────┴─────────┘
     *                  │
     *                  ▼
     *               RUNNABLE
     *                  │
     *                  │ run() ends
     *                  ▼
     *              TERMINATED
     */

    public void runDownloadThread() {
        Thread downloadThread = new Thread(() -> {
            System.out.println("download_Thread");
        });
        downloadThread.start();
    }

    /*
     *              JVM
     *               │ <- thread.start()
     *        ┌──────┴──────┐
     *        │             │
     *     Thread A      Thread B
     *        │             │
     *     println()     println()
     *
     * Thread A --> ?
     * Thread B --> ?
     *
     * JVM
     *  │
     *  │ <- thread.run()
     *  │
     *  └── Thread A --> Thread B
     */

    /**
     * <b>The code that has been written is fragile.</b>
     * <p>Two separate threads — the main one and the one we created — are
     * attempting to modify shared data.</p>
     * <p>The outcome can be unpredictable and indistinguishable.</p>
     */
    public void raceConditionThread() {
        final int SIZE = 5;

        // A public array of integers
        int[] array = new int[SIZE];

        Thread thread = new Thread(() -> {
            for (int i = 0; i < SIZE; i++) {
                array[i] = i;
            }
        });

        thread.start();

        for (int i = 0; i < SIZE; i++) {
            array[i] = i + 1;
        }

        System.out.println(Arrays.toString(array));
    }

    public String getThreadName() {
        return Thread.currentThread().getName();
    }

    public void matryoshka() {

        AtomicInteger counter = new AtomicInteger();

        Thread superThread = new Thread(() -> {

            Thread supThread_1 = new Thread(
                    counter::getAndIncrement
            );
            Thread supThread_2 = new Thread(
                    counter::getAndIncrement
            );
            Thread supThread_3 = new Thread(
                    counter::getAndIncrement
            );

            supThread_1.start();
            supThread_2.start();
            supThread_3.start();
        });
        superThread.start();
        try {
            superThread.join();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /*
     *       RUNNABLE
     *          │ sleep()
     *          ▼
     *     TIMED_WAITING
     *          │ timeout
     *          ▼
     *       RUNNABLE
     */
    public void threadSleeper(long goodnight) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(
                    Thread.currentThread().getName()
            );
        });
        thread.start();
        // ALWAYS STOPS THE CURRENT THREAD
        Thread.sleep(goodnight * 1000);
    }
    /*
     * main
     *  │
     *  ├── worker.start()
     *  │
     *  │        worker
     *  │          │
     *  │          ▼
     *  │       started
     *  │          │
     *  │       sleep 2s
     *  │          │
     *  │       finished
     *  │
     *  ├── worker.join()
     *  │       ↑
     *  │       │
     *  │     worker
     *  │
     *  ▼
     * Main finished
     */
    public void threadJoin() throws InterruptedException {
        Thread worker = new Thread(() -> {

            System.out.println("Worker started");

            try {
                Thread.sleep(2000);
            } // If an error occurs for the current thread
            catch (InterruptedException e) {
                // Stop the current thread (the thread is still alive and holds vital information)
                Thread.currentThread().interrupt();
            }

            System.out.println("Worker finished");
        });

        worker.start();

        worker.join();

        System.out.println("Main finished");
    }
    // volatile : It can help make the changes visible
    private volatile int progress;

    public void downloadFileThread() {
        Thread downloadFile = new Thread(() -> {
            System.out.println("Downloading...");
        });
        downloadFile.start();
        try {
            downloadFile.join();
            saveFileThread();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void saveFileThread() {
        /*
         * If we are on the download thread and its task is complete (it is no longer active),
         * execute the file-saving thread.
         */
        Thread saveFile = new Thread(() -> {
            System.out.println("Where you wanna save you file?");
        });
        saveFile.start();
    }
}
