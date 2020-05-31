package com.juc.thread.phaser;

/**
 * 要参加婚礼都成员Person类
 */
public class Person implements Runnable {

    String name;

    public Person(String name) {
        this.name = name;
    }

    /**
     * 每个人要执行都方法，arrive 到达
     */
    public void arrive() {
        Phaser_T1.milliSleep(Phaser_T1.r.nextInt(1000));

        System.out.printf("%s 到达现场！\n", name);

        /**
         * 让线程在一个栅栏面前停住，
         */
        Phaser_T1.phaser.arriveAndAwaitAdvance();
    }

    /**
     * 每个人要执行都方法，eat 吃
     */
    public void eat() {
        Phaser_T1.milliSleep(Phaser_T1.r.nextInt(1000));

        System.out.printf("%s 吃完！\n", name);
        Phaser_T1.phaser.arriveAndAwaitAdvance();
    }

    /**
     * 每个人要执行都方法 leave 离开
     */
    public void leave(){
        Phaser_T1.milliSleep(Phaser_T1.r.nextInt(1000));

        System.out.printf("%s 离开！\n", name);
        Phaser_T1.phaser.arriveAndAwaitAdvance();
    }

    /**
     * 新郎，新娘要执行都方法，hug 入洞房
     */
    public void hug() {
        if (name.equals("新郎") || name.equals("新娘")) {
            Phaser_T1.milliSleep(Phaser_T1.r.nextInt(1000));

            System.out.printf("%s 入洞房！\n", name);
            Phaser_T1.phaser.arriveAndAwaitAdvance();
        } else {

            /**
             * 告知其他人不再参与
             */
            Phaser_T1.phaser.arriveAndDeregister();

            /**
             * 如果想继续网上加 调用phaser.register()
             * 不仅可以控制栅栏的个数，还可以控制栅栏上的等待数量
             */
            //Phaser_T1.phaser.register();

        }
    }

    @Override
    public void run() {

        arrive();

        eat();

        leave();

        hug();
    }
}
