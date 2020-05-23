package com.juc.thread.interrupted.threadStop;

public class WorkService {

    private Thread executeService;

    private volatile boolean finished = false;

    public void execute(Runnable task){
        executeService = new Thread(() ->{
            Thread runner = new Thread(task);
            runner.setDaemon(true);
            runner.start();
            try {
                runner.join();
                finished = true;
            }catch (Exception e){
                System.out.println("打断正在工作的线程。。。");
            }
        });

        executeService.start();
    }

    public void listener(long ms) {
        System.out.println("开始监听。。。。");
        long currentTiem = System.currentTimeMillis();
        while (!finished){
            if ((System.currentTimeMillis() - currentTiem) >= ms) {
                System.out.println("工作耗时过长，开始打断。。。。");
                executeService.interrupt();
                break;
            }


            try {
                executeService.sleep(100L);
            } catch (Exception e){
                e.printStackTrace();
            }


        }
    }


    public static void main(String[] args) {
        WorkService workService = new WorkService();
        long l = System.currentTimeMillis();
        workService.execute(() ->{

            try {
                Thread.sleep(2*1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        });




        workService.listener(30000);

        System.out.println("一共耗时"+(System.currentTimeMillis()-l));
    }
}
