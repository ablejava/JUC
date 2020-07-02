package com.juc.Atomic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSynchronousQueue {

    /**
     * 容量等于0，是专门用来两个线程之间传递内容的
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");// 阻塞等待消费者
        strs.put("bbb");
        strs.add("aaa");
        System.out.println(strs.size());

    }
}
