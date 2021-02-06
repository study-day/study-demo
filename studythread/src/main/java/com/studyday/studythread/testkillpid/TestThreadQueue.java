package com.studyday.studythread.testkillpid;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * <p>测试  int queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size()。测试结果ThreadPoolExecutor.getQueue()
 * 表示的是等待队列。表示有多少个已经提交的线程在等待执行。
 * 其中 python D:\test\datax\bin\datax.py  D:/test/datax/job/1.json 是一个耗时约1分钟的命令，1分钟后自动退出。
 *
 * </p>
 * <p>创建时间：2021/2/4</p>
 *
 * @author lq
 * @version 1.0
 */
public class TestThreadQueue  {

    //声明一个ExecutorService
    private ExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {
        TestThreadQueue testThreadQueue = new TestThreadQueue();
        //testThreadQueue.test();
        //testThreadQueue.test2();
        testThreadQueue.test3();
    }

    public Future mysubmit(Mycmd1 mycmd1){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 提交一次");
       return  executorService.submit(mycmd1);
    }

    public Future mysubmit(Mycmd2 mycmd2){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 提交一次");
        return  executorService.submit(mycmd2);
    }
    public  void test() throws InterruptedException {
        //定义一个大小为5的线程池
        executorService = Executors.newFixedThreadPool(3);
        Future<?> submit = mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:\\_WORK\\github\\ZooInspector\\build\\zookeeper-dev-ZooInspector.jar"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:/_WORK/soft/jd-gui-1.6.6.jar"));

        System.out.println("开始休眠");
        Thread.sleep(10*1000L);//启动十秒，然后停止这个线程
        System.out.println("结束休眠");
//        System.out.println("submit.isCancelled(): "+submit.isCancelled());
//        System.out.println("submit.isDone(): "+submit.isDone());
//        submit.cancel(true);//停止线程
//        结论，无法直接关闭datax
//        测试线程实池队列
//        1.测试不会自动结束的cmd命令，比如启动zookeeper-dev-ZooInspector.jar
//        2.测试会自动结束的cmd命令，比如启动datax
        int i = 0;
        while(true){
            int queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
            System.out.println("队列的数量: "+queueingSize);
            Thread.sleep(3000L);//20s
            i++;
            if(i==4){
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
                Thread.sleep(3000L);//20s
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
            }
            if(i==5){
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
                Thread.sleep(3000L);//20s
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
            }

        }

    }


    /**
     * 测试feature.cannel对于队列的影响。没有影响，如果cmd命令已经提交了那么feature.cannel 将不起作用
     * @throws InterruptedException
     */
    public  void test2() throws InterruptedException {
        //定义一个大小为5的线程池
        executorService = Executors.newFixedThreadPool(3);
        Future<?> submit = mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:\\_WORK\\github\\ZooInspector\\build\\zookeeper-dev-ZooInspector.jar"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:/_WORK/soft/jd-gui-1.6.6.jar"));

        System.out.println("开始休眠");
        Thread.sleep(20*1000L);//启动十秒，然后停止这个线程
        System.out.println("结束休眠");
        System.out.println("submit.isCancelled(): "+submit.isCancelled());
        System.out.println("submit.isDone(): "+submit.isDone());
        boolean res = submit.cancel(true);//停止线程
        System.out.println("停止线程结果: "+res);
        System.out.println("submit.isCancelled(): "+submit.isCancelled());
        System.out.println("submit.isDone(): "+submit.isDone());
//        结论，无法直接关闭datax
//        测试线程实池队列
//        1.测试不会自动结束的cmd命令，比如启动zookeeper-dev-ZooInspector.jar
//        2.测试会自动结束的cmd命令，比如启动datax
        int i = 0;
        while(true){
            int queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
            System.out.println("队列的数量: "+queueingSize);
            Thread.sleep(3000L);//20s
            i++;
            if(i==4){
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
                Thread.sleep(3000L);//20s
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
            }
            if(i==5){
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
                Thread.sleep(3000L);//20s
                mysubmit(new Mycmd1("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
            }

        }

    }


    /**
     * 测试对mycmd2的影响,feature.cancel会终止休眠，然后会抛出一个异常，此时在异常中执行break，则此线程自行完毕。
     * @throws InterruptedException
     */
    public  void test3() throws InterruptedException {
        //定义一个大小为5的线程池
        executorService = Executors.newFixedThreadPool(1);
        Future<?> future = mysubmit(new Mycmd2("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:\\_WORK\\github\\ZooInspector\\build\\zookeeper-dev-ZooInspector.jar"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:/_WORK/soft/jd-gui-1.6.6.jar"));

        System.out.println("开始休眠");
        Thread.sleep(8*1000L);//启动十秒，然后停止这个线程
        System.out.println("结束休眠");
        System.out.println("submit.isCancelled(): "+future.isCancelled());
        System.out.println("submit.isDone(): "+future.isDone());
        boolean res = future.cancel(true);//停止线程


        System.out.println("停止线程结果: "+res);
        System.out.println("submit.isCancelled(): "+future.isCancelled());
        System.out.println("submit.isDone(): "+future.isDone());
//        结论，无法直接关闭datax
//        测试线程实池队列
//        1.测试不会自动结束的cmd命令，比如启动zookeeper-dev-ZooInspector.jar
//        2.测试会自动结束的cmd命令，比如启动datax
        int i = 0;
        while(1==i){
            int queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
            System.out.println("队列的数量: "+queueingSize);
            Thread.sleep(3000L);//20s
            i++;
            if(i==4){
                mysubmit(new Mycmd2("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
                Thread.sleep(3000L);//20s
                mysubmit(new Mycmd2("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
            }
            if(i==5){
                mysubmit(new Mycmd2("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
                Thread.sleep(3000L);//20s
                mysubmit(new Mycmd2("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
                queueingSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                System.out.println("队列的数量: "+queueingSize);
            }

        }

    }
}

class Mycmd1  extends Thread {
    private String cmd;
    Mycmd1(String cmd){
        this.cmd = cmd;
    }
    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            BaseRuntime cmdRuntime =  new BaseRuntime() {
                @Override
                public void runcmd() throws IOException {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 执行一次cmd命令 ");
                    this.setProcess(getRt().exec(cmd));
                }

                @Override
                public void wirteLog() throws IOException {

                }
            };
            int exitValue = cmdRuntime.start();
            System.out.println("命令执行完了 退出码 ："+exitValue);
            long end = System.currentTimeMillis();
            System.out.println("耗时："+((end-start)/1000/60));
            //System.out.println(exitValue);
            //String log = cmdRuntime.getAllLog();
            //System.out.println(log);
        } catch (Exception e) {
            //e.printStackTrace();
            e.printStackTrace();

        }

    }
}

class Mycmd2  extends Thread {
    private String cmd;
    Mycmd2(String cmd){
        this.cmd = cmd;
    }
    @Override
    public void run() {
//        int i = 0;
//        while(true){
//            try {
//                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 打印一次cmd命令-mycmd2在执行 ");
//                Thread.sleep(3000L+i*100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("我在异常中退出了");
//                break;
//            }
//            if (i>10){
//                System.out.println("我退出了");
//                break;
//            }
//            i++;
//        }

        try {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 打印一次cmd命令-mycmd2在执行 ");
            copyFileUsingFileStreams(new File("D:/_WORK/linux/soft/deepin-desktop-community-1010-amd64.iso"),
                    new File("G:/test/test-deepin-desktop-community-1010-amd64.iso.tmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("我退出了");
    }

    /**
     * 写一个低效率的文件复制程序，假装这个线程在认真的工作
     * @param source
     * @param dest
     * @throws IOException
     */
    private  void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
}