package org.example.Task2;

public class ThreadComparison {

    static class Counter {
        int count = 0;

        public void increment() {
            count++;
        }

        public synchronized void synchronizedIncrement() {
            count++;
        }
    }

    static class NormalThread extends Thread {
        Counter counter;

        public NormalThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            for (int i=0;i<100000; i++) {
                counter.increment();
            }
        }
    }

    static class SynchronizedThread extends Thread {
        Counter counter;

        public SynchronizedThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            for(int i=0; i<100000; i++) {
                counter.synchronizedIncrement();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10;

        //Measure Normal Threads
        Counter normalCounter = new Counter();
        Thread[] normalThreads = new Thread[numThreads];

        long startNormal = System.nanoTime();

        for (int i=0; i < numThreads; i++) {
            normalThreads[i] = new NormalThread(normalCounter);
            normalThreads[i].start();
        }

        for (int i=0; i < numThreads; i++) {
            normalThreads[i].join();
        }

        long endNormal = System.nanoTime();
        double timeNormal = (endNormal - startNormal) / 1_000_000_000.0;

        //Measure Synchronized Threads
        Counter syncCounter = new Counter();
        Thread[] syncThreads = new Thread[numThreads];

        long startSync = System.nanoTime();

        for (int i=0; i< numThreads; i++) {
            syncThreads[i] = new SynchronizedThread(syncCounter);
            syncThreads[i].start();
        }

        for (int i=0; i<numThreads; i++) {
            syncThreads[i].join();
        }

        long endSync = System.nanoTime();
        double timeSync = (endSync - startSync) / 1_000_000_000.0;

        System.out.printf("Normal thread: %.8f seconds%n", timeNormal);
        System.out.printf("Synchronized thread: %.8f seconds%n", timeSync);
    }
}
