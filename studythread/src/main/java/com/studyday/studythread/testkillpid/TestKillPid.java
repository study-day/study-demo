package com.studyday.studythread.testkillpid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>TestKillPid 测试是此方法是否可以在线程中杀死进程，</p>
 * 2.通过终止java程序，有时可以终止cmd命令所启动的进程，有时候不能。
 * <p>创建时间：2021/2/4</p>
 *
 * @author lq
 * @version 1.0
 */
public class TestKillPid  {

    //声明一个ExecutorService
    private ExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {
        TestKillPid testKillPid = new TestKillPid();
        testKillPid.test();
    }

    public Future mysubmit(Mycmd mycmd){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 提交一次");
       return  executorService.submit(mycmd);
    }

    public  void test() throws InterruptedException {
        //定义一个大小为5的线程池
        executorService = Executors.newFixedThreadPool(3);
        Future<?> future = mysubmit(new Mycmd("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:\\_WORK\\github\\ZooInspector\\build\\zookeeper-dev-ZooInspector.jar"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:/_WORK/soft/jd-gui-1.6.6.jar"));

        System.out.println("开始休眠");
        Thread.sleep(10*1000L);//启动十秒，然后停止这个线程
        System.out.println("结束休眠");
        System.out.println("submit.isCancelled(): "+future.isCancelled());
        System.out.println("submit.isDone(): "+future.isDone());
        future.cancel(true);//停止线程
//        结论，无法直接关闭datax
//        测试线程实池队列
//        1.测试不会自动结束的cmd命令，比如启动zookeeper-dev-ZooInspector.jar
//        2.测试会自动结束的cmd命令，比如启动datax

    }

}
class Mycmd  extends Thread {
    private String cmd;
    Mycmd(String cmd){
        this.cmd = cmd;
    }
    @Override
    public void run() {
        try {
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

            //System.out.println(exitValue);
            //String log = cmdRuntime.getAllLog();
            //System.out.println(log);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}