package com.juc.thread.marriagePhaser;

import static com.juc.thread.marriagePhaser.MarriagePhaserDemo.sleep;

public class Person implements Runnable{

    String name;

    public Person(String name) {
        this.name = name;
    }

    public void arrive() {
        System.out.printf("%s到达现场 \n", name);
        MarriagePhaserDemo.phaser.arriveAndAwaitAdvance();
    }

    public void eat() {
        System.out.printf("%s 吃完 \n", name);
        MarriagePhaserDemo.phaser.arriveAndAwaitAdvance();
    }

    public void leave() {
        System.out.printf("%s 离开 \n", name);
        MarriagePhaserDemo.phaser.arriveAndAwaitAdvance();
    }

    private void hug() {
        if (name.equals("新郎") || name.equals("新娘")) {
            System.out.printf("%s 洞房！\n", name);
            MarriagePhaserDemo.phaser.arriveAndAwaitAdvance(); //  提前到达并等待
        } else {
//            MarriagePhaserDemo.phaser.register(); //   登记
                try {
                    MarriagePhaserDemo.phaser.arriveAndDeregister();   //  到达和取消注册
                } catch (Exception e) {
                    System.out.printf("%s 度过了第一天时GG了！\n", name);
                }
        }
    }

    public void survivedNextDay() {
        sleep(3000);

        try {
            MarriagePhaserDemo.phaser.arriveAndAwaitAdvance(); //  提前到达并等待
            //  phaser.arriveAndDeregister();   //  到达和取消注册
        } catch (Exception e) {
            sleep(2000);
            System.out.printf("%s 度过了第二天时GG了！\n", name);
        }
    }

    public void survivedThirdDay() {
        sleep(3000);

        try {
            MarriagePhaserDemo.phaser.arriveAndDeregister();   //  到达和取消注册
        } catch (Exception e) {
            sleep(3000);
            System.out.printf("%s 度过了第三天时GG了！\n", name);
        }
    }


    @Override
    public void run() {
        arrive();

        eat();

        leave();

        hug();

        survivedNextDay();

        survivedThirdDay();
    }
}
