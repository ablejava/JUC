package com.juc.thread.phaser;

import java.util.concurrent.Phaser;

public class MirriagePhaser extends Phaser {


    /**
     * 婚礼活动有四个阶段，
     *
     * 继承Phaser类，重写onAdvance方法，
     * 当线程抵达栅栏的时候，所有的线程度满足类第一个栅栏的条件类，onAdvance会被自动调用
     * 有几个阶段是写死的，第一个必须是0开始
     * 在每个阶段执行完毕会
     *
     * @param phase 是第几阶段
     * @param registeredParties 是目前这个阶段有几个人参加
     * @return
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {

        switch (phase) {
            case 0:
                System.out.println("所有人都到齐了， "+ registeredParties);
                System.out.println();
                return false;

            case 1:
                System.out.println("所有人都吃完了 "+ registeredParties);
                System.out.println();
                return false;
            case 2:
                System.out.println("所有人都离开了！"+ registeredParties);
                System.out.println();
                return false;
            case 3:
                System.out.println("婚礼结束，新郎新娘入洞房 " +registeredParties);
                return true;
            default:
                return true;

        }
    }
}
