package com.javacs.threads;

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

    // volatile : It can help make the changes visible
    private volatile int progress;
}
