package com.threading.test.code;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Double> blockingQueue = new ArrayBlockingQueue<>(10);
        final Runnable producer = () -> {
            while (true) {
                try {
                    double i = Math.random();
                    System.out.println("putting value: "+i);
                    blockingQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Runnable consumer = () -> {
            while(true) {
                try {
                    double i = blockingQueue.take();
                    System.out.println("taking out element: "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
