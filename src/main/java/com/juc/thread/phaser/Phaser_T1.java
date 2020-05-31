package com.juc.thread.phaser;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Phaser_T1 {
    static Random r = new Random();

    static MirriagePhaser phaser = new MirriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MICROSECONDS.sleep(milli);
        } catch (InterruptedException e) {
        }
    }


    public static void main(String[] args) {

        phaser.bulkRegister(7);

        /**
         * 有5个人参加了婚礼
         */
        for (int i=0; i<5; i++) {
            new Thread(new Person("p " +i)).start();
        }

        new Thread(new Person("新郎")).start();

        new Thread(new Person("新娘")).start();
    }


}
