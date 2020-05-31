package com.juc.thread.readwritLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo{

	private int number = 0;

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	//读
	public void get(){
		lock.readLock().lock(); //上锁

		try{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println(Thread.currentThread().getName() + " : " + number);
		}finally{
			lock.readLock().unlock(); //释放锁
		}
	}

	//写
	public void set(int number){
		lock.writeLock().lock();

		try{
			System.out.println(Thread.currentThread().getName() +" number:"+ number);
			this.number = number;
		}finally{
			lock.writeLock().unlock();
		}
	}
}
