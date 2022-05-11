package com.execution.test.code;

import java.util.concurrent.CompletableFuture;

public class SampleTest {
    public static void main(String[] args) {

        create()
                .thenApply(data -> data * 2)
                .thenAccept(data -> System.out.println(data));
    }
    public static int compute() {
        return 2;
    }
    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> compute());
    }
}
