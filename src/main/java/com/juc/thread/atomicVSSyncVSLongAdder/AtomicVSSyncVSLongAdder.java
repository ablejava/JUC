package com.juc.thread.atomicVSSyncVSLongAdder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicVSSyncVSLongAdder {

    static AtomicLong count1 = new AtomicLong(0L);

    static long count2 = 0L;
    /**
     * LongAdder 到内部做类一个分段锁，
     * 会把一个值放到一个数组里，比如说数组长度是4，最开始是0，1000个线程，0-250个在第一个数组元素里，250-500在第二个数组元素里。。。
     * 每一个都往事递增算出来结果相加
     */
    static LongAdder count3 = new LongAdder();


    /**
     * Atomic:100000000 time 2146
     * ---------------------------------------
     * sync: 100000000 time3702
     * -----------------------------------
     * longAdder:100000000 time 235
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() ->{
                for (int k=0; k<100000; k++) {
                    count1.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads){
            t.start();
        }

        for (Thread t: threads){
            t.join();
        }

        long end = System.currentTimeMillis();

        System.out.println("Atomic:" + count1.get() + " time "+ (end -start));

        System.out.println("---------------------------------------");


        Object lock =new Object();

        for (int i=0; i<threads.length; i++){
            threads[i]= new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k=0; k<100000; k++){
                        synchronized (lock){
                            count2++;
                        }
                    }
                }
            });
        }


        start = System.currentTimeMillis();

        for (Thread t : threads){
            t.start();
        }

        for (Thread t: threads){
            t.join();
        }

        end = System.currentTimeMillis();
        System.out.println("sync: "+count2 +" time"+ (end -start));

        System.out.println("-----------------------------------");

        for (int i=0; i<threads.length; i++){
            threads[i] = new Thread(()->{
                for (int k=0; k<100000; k++){
                    count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();

        for (Thread t : threads){
            t.start();
        }

        for (Thread t: threads){
            t.join();
        }

        end = System.currentTimeMillis();

        System.out.println("longAdder:"+ count1.longValue() + " time "+ (end-start));


    }


    static void microSleep(int m) {
        try {
            TimeUnit.MICROSECONDS.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
