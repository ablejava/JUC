package com.juc.thread.readwritLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockVSReentrantLock {

	private static int number = 0;

	private static ReentrantLock lock = new ReentrantLock();
	// private static ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * ReentrantLock是独占的，其中一个线程获得锁，其他线程要等待
	 * @param args
	 */
	public static void main(String[] args) {

		Runnable readRee = () -> get();
		Runnable readRL = () -> get();
		Runnable readWee = () -> set(100);
		Runnable readWL = () -> set(100);

		for (int i=0; i<18; i++){
			new Thread(readRee).start();
		}

		for (int i=0; i<2;i++){
			new Thread(readWee).start();
		}



	}

	//读
	public static void get(){
		lock.lock(); //上锁

		try{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println(Thread.currentThread().getName() + " : " + number);
		}finally{
			lock.unlock(); //释放锁
		}
	}

	//写
	public static void set(int number){
		lock.lock();

		try{
			System.out.println(Thread.currentThread().getName() +" number:"+ number);
			ReadWriteLockVSReentrantLock.number = number;
		}finally{
			lock.unlock();
		}
	}
}
