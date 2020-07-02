package com.juc.Atomic;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestArrayBlockingQueue {
    /**
     * 设置队列长度为10
     */
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a"+i);
        }

        strs.put("aaa"); // 满了就会等待，程序阻塞
        strs.add("aaa");
        strs.offer("aaaa");
        strs.offer("aaa", 1, TimeUnit.SECONDS);
        System.out.println(strs);
    }


}
