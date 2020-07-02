package com.juc.Atomic;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class TestDelayQueue {



    static BlockingQueue<MyTask> tasks = new DelayQueue<>();
    static Random r = new Random();

    /**
     * DelayQueue 也是一个阻塞队列，装的任务必须实现Delayed接口，重写compareTo方法
     * 往里装任务的时候，先拿到当前时间，在当前时间的基础上指定在多长时间之后运行这个任务，
     * 但是当我们去拿当时候，是按照时间顺序排序
     * 本质上就是一个PriorityQueue
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now +1000);
        MyTask t2 = new MyTask("t2", now+2000);
        MyTask t3 = new MyTask("t3", now+3000);
        MyTask t4 = new MyTask("t4", now+4000);
        MyTask t5 = new MyTask("t5", now+5000);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);
        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }

    }

}
