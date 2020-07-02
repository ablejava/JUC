package com.juc.Atomic;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TestQueue {

    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票据号"+i);
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String s = tickets.poll();
                        if (s == null) {
                            break;
                        }else {
                            System.out.println("销售了--"+ s);
                        }
                    }
                }
            }).start();

        }
    }
}
