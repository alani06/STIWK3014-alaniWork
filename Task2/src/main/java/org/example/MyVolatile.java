package org.example;

import java.io.IOException;

class MyThread extends Thread {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Thread is running....");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread has stopped.");
    }

    public void shutdown() {
        running = false;
    }
}

public class MyVolatile {
    public static void main(String[] args) throws IOException {
        MyThread t = new MyThread();
        t.start();

        System.out.println("Press ENTER to stop the thread....");
        System.in.read();

        t.shutdown();

    }
}
