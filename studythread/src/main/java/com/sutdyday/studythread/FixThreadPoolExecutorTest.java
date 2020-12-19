package com.sutdyday.studythread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * FixThreadPoolExecutorTest 定义一个可重用的固线程数的线程池
 */
public class FixThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Thread th = null;
        // 始终只有10个线程来处理请求任务，来不及处理的任务会放置于阻塞队列中
        for (int i = 0; i < 3; i++) {

            th = new Thread() {

                int d = new Random().nextInt() % 100;

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    while (true) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        long x = 1 / (System.currentTimeMillis() % d);
                        System.out.println(Thread.currentThread().getName() + "--" + Thread.activeCount());

                    }
                }
            };
            th.setName("app" + i);
            pool.execute(th);

        }

        Thread thread = new Thread() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    long x = 1 / (System.currentTimeMillis() % 4);
                    System.out.println(Thread.currentThread().getName() + "--" + Thread.activeCount() + "@@");

                }
            }
        };
        thread.setName("thread123");
        pool.execute(thread);
        //pool.shutdown();

        List<Runnable> shutdownNow = pool.shutdownNow();
        int i = 0;
        while (true) {

            System.out.println("第" + i++ + "次检查");
            if (pool.isTerminated()) {
                System.out.println("---END---\n");
                shutdownNow.forEach(threadNotRun -> System.out.println(threadNotRun.toString()));
                System.out.println("所有的子线程都结束了！");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

//        while(true) {
//        	try {
//				Thread.sleep(8100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	System.out.println(Thread.currentThread().getName()+"--"+Thread.activeCount()+"@@");
//        	pool.
//        	
//        }
    }
}