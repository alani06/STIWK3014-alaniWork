package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class TrafficLightController {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        TrafficLight j1 = new TrafficLight("Junction 1", lock1);
        TrafficLight j2 = new TrafficLight("Junction 2", lock2);
        TrafficLight j3 = new TrafficLight("Junction 3", lock1);
        TrafficLight j4 = new TrafficLight("Junction 4", lock2);

        new Thread(j1).start();
        new Thread(j2).start();
        new Thread(j3).start();
        new Thread(j4).start();
    }
}
