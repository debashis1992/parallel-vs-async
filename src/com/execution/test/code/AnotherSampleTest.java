package com.execution.test.code;

import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

public class AnotherSampleTest {
    public static String op1() {

            System.out.println("op1 -- "+Thread.currentThread());
            sleep(1000);
            return "op1";
    }

    public static String op2() {

            System.out.println("op2 -- "+Thread.currentThread());
            sleep(1000);
            return "op2";

    }

    public static String op3() {

            System.out.println("op3 -- "+Thread.currentThread());
            sleep(1000);
            return "op3";

    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String op1 = op1();
        String op2 = op2();
        String op3 = op3();
        String combined = op1+" "+op2+" "+op3;
        System.out.println(combined);
        long end = System.currentTimeMillis();
        System.out.println("execution time: "+(end-start));

    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
