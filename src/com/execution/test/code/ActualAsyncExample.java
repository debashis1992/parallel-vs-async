package com.execution.test.code;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActualAsyncExample {
    public static CompletableFuture<String> op1() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("op1 -- "+Thread.currentThread());
            sleep(1000);
            return "op1";
        });
    }

    public static CompletableFuture<String> op2() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("op2 -- "+Thread.currentThread());
            sleep(1000);
            return "op2";
        });
    }

    public static CompletableFuture<String> op3() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("op3 -- "+Thread.currentThread());
            sleep(1000);
            return "op3";
        });
    }

    public static void main(String[] args) {

//        create()
//                .thenApply(data -> data * 2)
//                .thenAccept(data -> System.out.println(data));

//        CompletableFuture<String> future =
//                CompletableFuture.supplyAsync(() -> "hello")
//                        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " world")); //does this operation asynchronously

//        CompletableFuture<String> future2 =
//                CompletableFuture.supplyAsync(() -> "hello")
//                        .thenApply(s -> s + " world"); //does this operation synchronously

        long start = System.currentTimeMillis();
        CompletableFuture<String> f1 = op1();
        CompletableFuture<String> f2 = op2();
        CompletableFuture<String> f3 = op3();

//        CompletableFuture<Void> combinedCompletableFuture = CompletableFuture.allOf(f1,f2,f3);
        String combined = Stream.of(f1,f2,f3).map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

//        try {
//            System.out.println(combinedCompletableFuture.get());
//        } catch (Exception e) {}
        System.out.println(combined);
        System.out.println(f1.isDone()+", "+f2.isDone()+", "+f3.isDone());
        long end = System.currentTimeMillis();
        System.out.println("execution time: "+(end-start));


    }
    public static int compute() {
        return 2;
    }
    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> compute());
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
