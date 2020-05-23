package com.juc.thread.join;

/**
 * 通过主程序join使线程按顺序执行
 */
public class ThreadMainJoinDemo {


    public static void main(String[] args) throws Exception {

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("**********7*********");
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("**********8*********");
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("**********9*********");
            }
        });

        System.out.println("早上:");
        System.out.println("********1*********");
        System.out.println("********2*********");
        System.out.println("********3*********");
        thread1.start();
        //在父进程调用子进程的join()方法后，父进程需要等待子进程运行完再继续运行。
        System.out.println("********4*********");
        thread1.join();
        System.out.println("********5*********");
        thread2.start();
        System.out.println("********6*********");
        thread2.join();
        thread3.start();
    }
}
