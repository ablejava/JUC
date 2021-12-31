package com.juc.thread.marriagePhaser;

import java.util.concurrent.Phaser;

import static com.juc.thread.marriagePhaser.MarriagePhaserDemo.sleep;

public class MarriagePhaser extends Phaser {

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                System.out.println("所有人到齐了！" + registeredParties + "人");
                System.out.println();
                sleep(2000);
                return false;
            case 1:
                System.out.println("所有人吃完了！" + registeredParties + "人");
                System.out.println();
                sleep(2000);
                return false;
            case 2:
                System.out.println("所有人离开了！" + registeredParties + "人");
                System.out.println();
                sleep(2000);
                return false;
            case 3:
                System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties + "人");
                System.out.println();
                sleep(2000);
                return false;
            case 4:
                System.out.println("活过了第二天" + registeredParties + "人");
                System.out.println();
                sleep(2000);
                return false;
            default:
                System.out.println("活过了第三天" + registeredParties + "人");
                System.out.println();
                sleep(2000);
                return true;
        }
    }
}
