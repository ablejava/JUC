package com.juc.thread.synchronizedThread;

import java.util.HashMap;

public class AccountingSync4 implements Runnable {
    private static HashMap<String, Integer> map = new HashMap<>();

    @Override
    public void run() {
        add("key");
    }
    public  synchronized void add(String key){
        Integer value = map.get(key);
        if (value == null) {
            map.put(key, 1);
        } else {
            int newValue = value+ 1;
            map.put(key, newValue);
            System.out.println(newValue);
        }

    }


    public static void main(String[] args) throws InterruptedException {
        AccountingSync4 accountingSync4 = new AccountingSync4();
        for (int j = 0; j < 100; j++) {
            Thread t1 = new Thread(accountingSync4);
            t1.start();
        }

//        Thread t1 = new Thread(new AccountingSync4());
//        Thread t2 = new Thread(new AccountingSync4());
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
        Thread.sleep(10000);
        System.out.println(map.get("key"));
    }
}
