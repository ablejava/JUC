package com.juc.thread.marriagePhaser;

import java.util.concurrent.TimeUnit;

public class MarriagePhaserDemo {

    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7); // 注册

        for (int i = 0; i < 5; i++) {
            new Thread(new Person("路人 "+i)).start();

        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

        for (;;){
            sleep(1000);
        }
    }

    static void sleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
