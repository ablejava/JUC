package com.juc.interviewQuestions.question2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {

    final private LinkedList<T> lists = new LinkedList<>();

    final private int MAX =10;

    private int count =0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {

        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            ++count;
            consumer.signalAll(); // 通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public T get() {
        T t = null;


        try {
            lock.lock();
            while (lists.size() == 0) {

                consumer.await();

            }

            t = lists.removeFirst();
            count --;

            producer.signalAll(); // 通知生产者生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }


    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();



        // 启动10个消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                /**
                 * 每个线程执行5次 c.get() ====> 共执行50次get
                  */
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


        // 启动2个生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() ->{
                /**
                 * 每个线程执行25次 put， 共50次put
                 */
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            }, "p"+i).start();
        }


    }

}
