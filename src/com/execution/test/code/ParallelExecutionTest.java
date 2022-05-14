package com.execution.test.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelExecutionTest {
    public static List<Integer> createList() {
        return IntStream.range(0, 10000000).boxed()
                .collect(Collectors.toList());
    }
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static int transform(int number) {
        System.out.println("t: "+number+" -- "+Thread.currentThread());
//        sleep(1000);
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
//        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,
//                11,12,13,14,15,16,17,18,19,20);
           List<Integer> numbers = createList();

//        process(numbers.stream().map(e -> transform(e)));
        numbers.stream().map(e -> transform(e)).forEach(e -> {});
//        numbers.parallelStream().map(ParallelExecutionTest::transform).forEach(e -> {});

//        List<List<Integer>> lists = new ArrayList<>();
//        int chunkSize = 1000;
//        for (int i = 0; i < numbers.size(); i += chunkSize) {
//            int end = Math.min(numbers.size(), i + chunkSize);
//            lists.add(numbers.subList(i, end));
//        }
//        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
//        for(List<Integer> list : lists)
//            completableFutureList.add(processMapAsync(list));
////
////        List<Integer> finalList =
////                completableFutureList.stream().map(CompletableFuture::join).flatMap(res -> res.stream())
////                        .collect(Collectors.toList());
//
//        completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

        long end = System.currentTimeMillis();
        System.out.println("execution time: "+(end-start));

        // number of cores on my machine
//        System.out.println(Runtime.getRuntime().availableProcessors());
        // details about the common pool
        System.out.println(ForkJoinPool.commonPool());
    }

    public static CompletableFuture<Void> processMapAsync(List<Integer> list) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("executing async method...");
            list.stream().map(x -> {
                System.out.println("t: "+x+" -- "+Thread.currentThread());
                return x;
            }).forEach(x -> {});
        });
    }
}
