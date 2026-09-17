package com.javacs;

import com.javacs.threads.JThreads;

// Time spent with cats is never wasted.
public class Main {
    public static void main(String[] args) {
        JThreads jThreads = new JThreads();
        jThreads.raceConditionThread();
    }
}