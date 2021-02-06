package com.studyday.studythread.testkillpid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>TestKillPid 测试是此方法是否可以在线程中杀死进程</p>
 *  测试结果1.如果在线程内通过javaruntime启动一个cmd命令。比如 datax.py ,那么future.cancel(true);是不能停止这个cmd所启动的进程的。
 * <p>创建时间：2021/2/4</p>
 *
 * @author lq
 * @version 1.0
 */
public class TestSignalHandler  {

    //声明一个ExecutorService
    private ExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {
        TestSignalHandler testSignalHandler = new TestSignalHandler();
        testSignalHandler.test();
    }

    public Future mysubmit(CmdThread cmdThread){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 提交一次");
       return  executorService.submit(cmdThread);
    }

    /**
     * 测试结果，可以结束执行的cmd命令，但是如果cmd命令如果有子进程，最末端的进程就会成为孤儿线程。
     * @throws InterruptedException
     */
    public  void test() throws InterruptedException {
        //定义一个大小为5的线程池
        executorService = Executors.newFixedThreadPool(3);
        //CmdThread thread = new CmdThread("python D:\\test\\datax\\bin\\datax.py  D:/test/datax/job/1.json");
        //linux 测试
//        CmdThread thread = new CmdThread("java -server -Xms1g -Xmx1g " +
//                "-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/datax/datax/log " +
//                "-Dloglevel=info -Dfile.encoding=UTF-8 " +
//                "-Dlogback.statusListenerClass=ch.qos.logback.core.status.NopStatusListener -Djava.security.egd=file:///dev/urandom -Ddatax.home=/data/datax/datax " +
//                "-Dlogback.configurationFile=/data/datax/datax/conf/logback.xml " +
//                "-Dlog.file.name=tmp_datax_datax_conf " +
//                "-DreadPassword=yhdesp " +
//                "-DwriterPassword=yhdesp " +
//                "-classpath /data/datax/datax/lib/*:. com.alibaba.datax.core.Engine HELLO " +
//                "-mode standalone " +
//                "-jobid -1 " +
//                "-job /data/datax/dataxJson/149122-149137-12275-1611632568985.json");
        //改了代码之后，看会不会出现父进程为1 的进程
        CmdThread thread = new CmdThread("/data/datax/datax/bin/datax.py /data/datax/dataxJson/149122-149137-12275-1611632568985.json");
        Future<?> submit = mysubmit(thread);
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:\\_WORK\\github\\ZooInspector\\build\\zookeeper-dev-ZooInspector.jar"));
        //Future<?> submit = executorService.submit(new Mycmd("java -jar D:/_WORK/soft/jd-gui-1.6.6.jar"));

        System.out.println("开始休眠");
        Thread.sleep(25*1000L);//启动十秒，然后停止这个线程
        System.out.println("结束休眠");
        //thread.getCmdRuntime().getProcess().destroy();
//        thread.getCmdRuntime().getProcess().destroy();//销毁runtime测试
//        System.out.println();
        Process process = thread.getCmdRuntime().getProcess().destroyForcibly();//销毁runtime测试
        System.out.println("process.isAlive():"+process.isAlive());

//        System.out.println("submit.isCancelled(): "+submit.isCancelled());
//        System.out.println("submit.isDone(): "+submit.isDone());
//        submit.cancel(true);//停止线程
//        结论，无法直接关闭datax
//        测试线程实池队列
//        1.测试不会自动结束的cmd命令，比如启动zookeeper-dev-ZooInspector.jar
//        2.测试会自动结束的cmd命令，比如启动datax


    }

}

class CmdThread  extends Thread {
    private String cmd;
    private  BaseRuntime cmdRuntime;
    CmdThread(String cmd){
        this.cmd = cmd;
        this.cmdRuntime =  new BaseRuntime() {
            @Override
            public void runcmd() throws IOException {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 执行一次cmd命令 ");
                this.setProcess(getRt().exec(cmd));
            }

            @Override
            public void wirteLog() throws IOException {

            }
        };
    }

    public BaseRuntime getCmdRuntime() {
        return cmdRuntime;
    }

    public void setCmdRuntime(BaseRuntime cmdRuntime) {
        this.cmdRuntime = cmdRuntime;
    }

    @Override
    public void run() {
        try {
            int exitValue = this.cmdRuntime.start();

            System.out.println("命令执行完了 退出码 ："+exitValue);

            //System.out.println(exitValue);
            String log = cmdRuntime.getAllLog();
            System.out.println(log);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}