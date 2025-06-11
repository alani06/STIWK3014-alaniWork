package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class TrafficLight implements Runnable {
    private final String name;
    private final ReentrantLock conflictLock;
    private TrafficLightState state;

    public TrafficLight (String name, ReentrantLock conflictLock) {
        this.name = name;
        this.conflictLock = conflictLock;
        this.state = TrafficLightState.RED;
    }

    public void run() {
        while (true) {
            try {
              conflictLock.lock();
              changeState(TrafficLightState.GREEN);
              Thread.sleep(5000);

              changeState(TrafficLightState.YELLOW);
              Thread.sleep(2000);

              changeState(TrafficLightState.RED);
              conflictLock.unlock();

              Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeState(TrafficLightState newState) {
        this.state = newState;
        System.out.println("[" + name + "] Light is " + state);
    }
}
