package com.juc.Atomic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class TestConcurrentSkipListMap {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
//        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new Hashtable<>();
//        Map<String, String> map = new TreeMap<>();

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    map.put("a"+r.nextInt(100000), "a"+ r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t->t.start());


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }
}
