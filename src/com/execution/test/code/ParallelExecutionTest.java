package com.execution.test.code;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParallelExecutionTest {
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static int transform(int number) {
        System.out.println("t: "+number+" -- "+Thread.currentThread());
        sleep(1000);
        return number;
    }
    public static boolean check(int number) {
        return true;
    }

    public static int add(int a,int b) {
        int result = a+b;

        System.out.println("a: "+a+", b: "+b+", result: "+result+" -- "+Thread.currentThread());
        return result;
    }
    public static void process(Stream<Integer> stream) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(100);
        forkJoinPool.submit(() -> stream.forEach(e -> {}));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
    }
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20);

        process(numbers.parallelStream().map(e -> transform(e)));
//        numbers.parallelStream().map(e -> transform(e)).forEach(e -> {});

        long end = System.currentTimeMillis();
        System.out.println("execution time: "+(end-start));

        // number of cores on my machine
        System.out.println(Runtime.getRuntime().availableProcessors());
        // details about the common pool
        System.out.println(ForkJoinPool.commonPool());
    }
}
