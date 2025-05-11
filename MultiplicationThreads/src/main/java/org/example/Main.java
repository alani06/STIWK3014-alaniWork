package org.example;

public class Main {
    public static void main(String[] args) {
        Thread t1 = new MultiplicationThread(1);
        Thread t2 = new MultiplicationThread(2);
        Thread t3 = new MultiplicationThread(3);

        t1.start();
        t2.start();
        t3.start();
    }
}
