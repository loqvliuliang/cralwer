package com.example.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liang.liu04@hand-china.com
 * on 2018/6/25
 *
 * 线程池
 *
 * 会清楚看到只能执行4个线程。当执行完一个线程后，才会又执行一个新的线程，
 * 也就是说，我们将所有的线程提交后，线程池会等待执行完最后shutdown。
 * 我们也会发现，提交的线程被放到一个“无界队列里”。这是一个有序队列（BlockingQueue，这个下面会说到）。
 */
public class MyExecutor extends Thread{

    private int index;

    public MyExecutor(int i){
        this.index = i ;
    }

    public void run(){
        try{
            System.out.println("["+this.index+"]" + " start...");
            Thread.sleep((int)(Math.random()*10000));
            System.out.println("["+this.index+"]" + " end...");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("111");
        }
    }



    public static void main(String args[]){
        ExecutorService service= Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++){
            service.execute(new MyExecutor(i));
        }

        System.out.println("submit finish");
        service.shutdown();
        System.out.println("executor finish");
    }

}
