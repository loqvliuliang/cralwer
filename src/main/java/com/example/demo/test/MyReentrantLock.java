package com.example.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liang.liu04@hand-china.com
 * on 2018/6/25
 *
 * 更强大的同步锁 ReentrantLock
 *
 *
 */
public class MyReentrantLock extends Thread {
    TestReentrantLock lock;

    private int id;
	public MyReentrantLock(int i,TestReentrantLock test){
        	    this.id=i;
        	    this.lock=test;
    }

    public void run(){
        lock.print(id);
    }


    public static void main(String args[]){
        	    ExecutorService service= Executors.newCachedThreadPool();
        	    TestReentrantLock lock=new TestReentrantLock();
        	    for(int i=0;i<10;i++){
            	     service.submit(new MyReentrantLock(i,lock));
            	    }
        	    service.shutdown();
        	}
	}

    class TestReentrantLock{
        //加true：公平锁（先执行等待时间比较长的，默认不公平）
	private ReentrantLock lock=new ReentrantLock(true);
	public void print(int str){
            	    try{
                	     lock.lock();
                	     System.out.println(str+"获得");
                	     Thread.sleep((int)(Math.random()*1000));
                    }
            	    catch(Exception e){
                	     e.printStackTrace();
                    }
            	    finally{
                	     System.out.println(str+"释放");
                	     lock.unlock();
                	    }
            	}
	}

