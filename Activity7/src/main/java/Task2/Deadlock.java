package Task2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Deadlock implements Runnable {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();
    private final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        Thread myThread1 = new Thread(new Deadlock(), "thread-1");
        Thread myThread2 = new Thread(new Deadlock(), "thread-2");
        myThread1.start();
        myThread2.start();
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            boolean b = random.nextBoolean();
            if (b) {
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock lock1.");
                    if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                        System.out.println("[" + Thread.currentThread().getName() + "] Locked lock1.");
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock lock2.");
                            if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("[" + Thread.currentThread().getName() + "] Locked lock2.");
                                } finally {
                                    lock2.unlock();
                                    System.out.println("[" + Thread.currentThread().getName() + "] Released lock2.");
                                }
                            } else {
                                System.out.println("[" + Thread.currentThread().getName() + "] Could not lock lock2. Avoiding deadlock.");
                            }
                        } finally {
                            lock1.unlock();
                            System.out.println("[" + Thread.currentThread().getName() + "] Released lock1.");
                        }
                    } else {
                        System.out.println("[" + Thread.currentThread().getName() + "] Could not lock lock1.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock lock2.");
                    if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                        System.out.println("[" + Thread.currentThread().getName() + "] Locked lock2.");
                        try {
                            System.out.println("[" + Thread.currentThread().getName() + "] Trying to lock lock1.");
                            if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println("[" + Thread.currentThread().getName() + "] Locked lock1.");
                                } finally {
                                    lock1.unlock();
                                    System.out.println("[" + Thread.currentThread().getName() + "] Released lock1.");
                                }
                            } else {
                                System.out.println("[" + Thread.currentThread().getName() + "] Could not lock lock1. Avoiding deadlock.");
                            }
                        } finally {
                            lock2.unlock();
                            System.out.println("[" + Thread.currentThread().getName() + "] Released lock2.");
                        }
                    } else {
                        System.out.println("[" + Thread.currentThread().getName() + "] Could not lock lock2.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

