package com.execution.test.code;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActualTest {

    public static List<Integer> createList() {
        return IntStream.range(0, 10000).boxed()
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<Integer> list = createList();

        long start = System.currentTimeMillis();

        List<Integer> finalList = new ArrayList<>();
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
//        // fill collection
////        int chunkSize = 1000;
////        List<List<Integer>> lists = new ArrayList<>();
////        for (int i = 0; i < list.size(); i += chunkSize) {
////            int end = Math.min(list.size(), i + chunkSize);
////            lists.add(list.subList(i, end));
////        }
////
////        List<CompletableFuture<List<Integer>>> completableFutureList = new ArrayList<>();
////        for(List<Integer> li: lists) {
////            completableFutureList.add(processAsync(li));
////        }
////
////        List<Integer> endResult =
////                completableFutureList.stream().map(CompletableFuture::join)
////                        .flatMap(li -> li.stream()).collect(Collectors.toList());
////        System.out.println("size: "+endResult.size());
//        list.stream().map(li -> li+10).forEach(val -> finalList.add(val));

        list.parallelStream().map(li -> li+10).forEach(val -> copyOnWriteArrayList.add(val));
        System.out.println(copyOnWriteArrayList.size());
        long end = System.currentTimeMillis();
        System.out.println("execution time : "+(end-start));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    public static CompletableFuture<List<Integer>> processAsync(List<Integer> list) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Executing on thread : "+Thread.currentThread());
            return list.stream().map(li -> li+10).collect(Collectors.toList());
        });
    }
}
