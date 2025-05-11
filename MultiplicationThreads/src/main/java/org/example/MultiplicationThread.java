package org.example;

public class MultiplicationThread extends Thread{
    private final int number;

    public MultiplicationThread(int number) {
        this.number = number;
    }

    public void run() {
        for (int i=1; i<=3; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + number + "*" + i + "=" +(number*i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
