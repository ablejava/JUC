package com.juc.thread.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo_T3 {

    /**
     * 当LockSupport.unpark(t) 先执行，就相当于给线程t一个"令牌"
     * 当i==5时候，当前t发现有一个令牌，会立即向下执行
     * 令牌使用后失效
     * 当i==8时候，LockSupport.park()，线程就会一直阻塞在这
     *
     * AQS 就是通过调用LockSupport.park() 和 unpark()方法来实现线程当阻塞和唤醒当
     *
     * LockSupport总结：
     *  1.LockSupport不需要synchronized加锁就可以实现线程当阻塞和唤醒
     *  2.LockSupport.unpark()可以先于park()方法执行，并且线程不回阻塞
     *  3.如果一个线程处于等待状态，连续调用了两次park()方法，就会使该线程永远阻塞
     *
     *
     *  LockSupport实现原理：
     *  由Unsefa类提供的，Unsefa由c,C++完成，它是通过一个变量作为标识，变量在0，1之间来回切换，当这个变量大于0当时候，线程就
     *  获得类"令牌"
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new Thread(() ->{
            for (int i =0; i<10; i++){
                System.out.println(i);

                if (i ==5){

                    System.out.println("park 时间"+System.currentTimeMillis());
                    // 调用LockSupport的park()方法阻塞当前线程t
                    LockSupport.park();
                }

                if (i ==8){

                    LockSupport.park();
                }


                try {
                    // 使当前线程 休眠1s
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        // 唤醒线程t

        System.out.println(System.currentTimeMillis()+" unpark时间");
        LockSupport.unpark(t);
    }
}
