package com.juc.thread.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2019/2/24.
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable{

        private String soldier;
        private final CyclicBarrier cyclic;
        Soldier(CyclicBarrier cyclic, String soldierName) {
            this.cyclic = cyclic;
            this.soldier = soldierName;
        }
        @Override
        public void run() {

            try {
                // 等待所有士兵到齐
                cyclic.await();
                // 等待所有士兵完成工作
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        void doWork(){
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier+":任务完成！");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = false;
            this.N = N;
        }
        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：士兵"+N+"任务完成");
            } else {
                System.out.println("司令：士兵"+N+"集合完成");
                flag = true;
            }
        }
    }


    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        // 设置屏障点 ，主要是为了执行这个方法
        System.out.println("集合队伍！");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵"+i+"报道");
            allSoldier[i] = new Thread(new Soldier(cyclic, "士兵"+i));
            allSoldier[i].start();
        }
    }
}
