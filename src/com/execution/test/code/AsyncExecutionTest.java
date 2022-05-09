package com.execution.test.code;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class AsyncExecutionTest {
    public static int compute() {
        System.out.println("compute: "+Thread.currentThread());
        sleep(1000);
       return 1;
    }
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<Integer> create() {
        ForkJoinPool pool = new ForkJoinPool(10);
        return CompletableFuture.supplyAsync(() -> compute(), pool);
    }
    public static void printIt(int val) {
        System.out.println(val+" -- "+Thread.currentThread());
    }
    public static void main(String[] args) throws Exception {
        /*CompletableFuture<Integer> future = create();

        //some famous functional interfaces in Java 8
        //Supplier -> get value - T get()
        //Predicate -> filter using boolean expressions - boolean test(T)
        //Function -> gives a result based on another result - R apply(T)
        //Consumer -> takes a data and doesn't return anything - void accept(T)

        future.thenAccept(System.out::println);

        create().thenAccept((data) -> System.out.println(data))
                .thenRun(() -> System.out.println("this never dies"))
                .thenRun(() -> System.out.println("this never dies 2"))
                .thenRun(() -> System.out.println("this never dies 3"));

        CompletableFuture<Integer> future2 = compute();
        System.out.println(future2.getNow(-1));
        System.out.println("got it...");*/

        System.out.println("In main -- "+Thread.currentThread());
        CompletableFuture<Integer> future3 = create();
        sleep(100);
        future3.thenAccept((val) -> printIt(val));
        System.out.println("here...");
        sleep(1000); //main thread is put to sleep as the FJP thread executes the printIn method.
    }

}
