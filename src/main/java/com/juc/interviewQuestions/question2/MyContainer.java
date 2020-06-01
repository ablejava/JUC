package com.juc.interviewQuestions.question2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer<T> {

    final private LinkedList<T> lists = new LinkedList<>();

    final private int MAX = 10; // 最多10个元素

    private int count = 0;

    // 生产者
    public synchronized void put(T t) {
        // 想想为什么用while， 而不是if
        while (lists.size() == MAX) {

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        lists.add(t);

        ++count;

        this.notifyAll(); // 通知消费者线程进行消费
    }


    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {

            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        t = lists.removeFirst();

        count --;

        this.notifyAll(); // 通知生产者线程进行生产

        return t;

    }


    public static void main(String[] args) {

        MyContainer<String> c = new MyContainer<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c"+i).start();
        }


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            }, "p"+i).start();
        }
    }
}
