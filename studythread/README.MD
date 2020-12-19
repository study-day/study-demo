通过分析下面的FixThreadPoolExecutorTest代码，以及日志（多次执行后，比较典型的日志）可以得到下面的结论

1.ExecutorService pool = Executors.newFixedThreadPool(3);决定了同时只能有三个线程同时执行，如果还有第四个比如pool.execute(thread);。那么第四个将会等待。等待进入线程池中执行。

2.main程序不会因为第四个线程在等待而等待。

3.pool.execute 是异步的。不会影响主线程的执行。

4.当pool中有一个线程执行完了，或者因为异常而”死亡“。排队的线程才可以进来执行

5.pool.shutdown();也是异步的。当所有线程都执行完了或者”死亡“了。才会执行

6.pool.shutdown()执行了，pool.isTerminated() 才会是true

7.纠正自己一个错误的意识，线程只要进入线程池就会常驻，可以复用。这个时结合多线程的动画来理解的。今天通过代码结合异常来分析。现在的理解时new FixedThreadPool(3),好比一个一位母亲怀了三胞胎。每一次将一个执行threadpool.execute(new Thread(){}) 就像生下了一个baby。每个run（）就是每个线程的一生。最终三个线程会消亡，但是这位目前的肚子上三胞胎的留下的印记。

补充测试



7. pool.execute(thread); 不能放在pool.shutdown()之后，不然会抛出异常。换句话说就是没有开始执行的任务会被取消执行。正在执行的任务会继续执行。

```
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task Thread[thread123,5,main] rejected from java.util.concurrent.ThreadPoolExecutor@33909752[Shutting down, pool size = 3, active threads = 3, queued tasks = 1, completed tasks = 0]
	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2047)
	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:823)
	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1369)
	at com.sutdyday.studythread.FixThreadPoolExecutorTest.main(FixThreadPoolExecutorTest.java:65)
```

8.shutdonNow()会终端线程的sleep操作。和shutdown一样，不会接收之后提交的任务。**同时shutdownNow还不会执行队列中的任务。因为他的日志里没有包含@@的打印日志。**

9.shutdonNow()会返回那些未执行的任务。 比如日志的最后返回了Thread[thread123,5,main].

10.https://www.cnblogs.com/aspirant/p/10265863.html 。本次实验，验证了博客里很多描述。对于理解多线程起到了很好的帮助。

```
1、shutDown() 

    当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态。此时，则不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。 

   2、shutdownNow() 

     执行该方法，线程池的状态立刻变成STOP状态，并试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，当然，它会返回那些未执行的任务。 
     它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，但是大家知道，这种方法的作用有限，如果线程中没有sleep 、wait、Condition、定时锁等应用, interrupt()方法是无法中断当前的线程的。所以，ShutdownNow()并不代表线程池就一定立即就能退出，它可能必须要等待所有正在执行的任务都执行完成了才能退出。 
```





这篇博客的部分内容可以参考

https://www.cnblogs.com/aspirant/p/10265863.html

shutdown典型日志

```
第0次检查
第1次检查
pool-1-thread-2--4
pool-1-thread-3--4
pool-1-thread-1--4
第2次检查
第3次检查
pool-1-thread-2--4
pool-1-thread-1--4
pool-1-thread-3--4
第4次检查
第5次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第6次检查
第7次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第8次检查
第9次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第10次检查
第11次检查
pool-1-thread-2--4
pool-1-thread-3--4
pool-1-thread-1--4
第12次检查
第13次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第14次检查
第15次检查
pool-1-thread-2--4
pool-1-thread-3--4
pool-1-thread-1--4
第16次检查
第17次检查
pool-1-thread-2--4
pool-1-thread-1--4
pool-1-thread-3--4
第18次检查
第19次检查
pool-1-thread-2--4
pool-1-thread-3--4
pool-1-thread-1--4
第20次检查
第21次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第22次检查
第23次检查
pool-1-thread-3--4
pool-1-thread-1--4
pool-1-thread-2--4
第24次检查
第25次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第26次检查
第27次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第28次检查
第29次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第30次检查
第31次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第32次检查
第33次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第34次检查
第35次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第36次检查
第37次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第38次检查
第39次检查
pool-1-thread-3--4
pool-1-thread-1--4
pool-1-thread-2--4
第40次检查
第41次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第42次检查
第43次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第44次检查
第45次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第46次检查
第47次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第48次检查
第49次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第50次检查
第51次检查
pool-1-thread-2--4
pool-1-thread-1--4
pool-1-thread-3--4
第52次检查
第53次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第54次检查
第55次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第56次检查
第57次检查
pool-1-thread-1--4
pool-1-thread-2--4
pool-1-thread-3--4
第58次检查
第59次检查
pool-1-thread-2--4
pool-1-thread-3--4
pool-1-thread-1--4
第60次检查
第61次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第62次检查
第63次检查
pool-1-thread-2--4
pool-1-thread-1--4
pool-1-thread-3--4
第64次检查
第65次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-2--4
第66次检查
第67次检查
pool-1-thread-1--4
pool-1-thread-3--4
Exception in thread "pool-1-thread-2" java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:43)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第68次检查
第69次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-4--4@@
第70次检查
第71次检查
pool-1-thread-1--4
pool-1-thread-4--4@@
pool-1-thread-3--4
第72次检查
第73次检查
pool-1-thread-1--4
pool-1-thread-3--4
pool-1-thread-4--4@@
第74次检查
第75次检查
pool-1-thread-1--4
pool-1-thread-4--4@@
pool-1-thread-3--4
第76次检查
第77次检查
pool-1-thread-1--4
pool-1-thread-3--4
Exception in thread "pool-1-thread-4" java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$2.run(FixThreadPoolExecutorTest.java:66)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第78次检查
第79次检查
pool-1-thread-1--3
pool-1-thread-3--3
第80次检查
第81次检查
pool-1-thread-3--3
pool-1-thread-1--3
第82次检查
第83次检查
pool-1-thread-1--3
pool-1-thread-3--3
第84次检查
第85次检查
pool-1-thread-1--3
pool-1-thread-3--3
第86次检查
第87次检查
pool-1-thread-1--3
Exception in thread "pool-1-thread-3" java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:43)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第88次检查
第89次检查
pool-1-thread-1--2
第90次检查
第91次检查
pool-1-thread-1--2
第92次检查
第93次检查
pool-1-thread-1--2
第94次检查
第95次检查
pool-1-thread-1--2
第96次检查
第97次检查
pool-1-thread-1--2
第98次检查
第99次检查
pool-1-thread-1--2
第100次检查
第101次检查
pool-1-thread-1--2
第102次检查
第103次检查
pool-1-thread-1--2
第104次检查
第105次检查
pool-1-thread-1--2
第106次检查
第107次检查
pool-1-thread-1--2
第108次检查
第109次检查
pool-1-thread-1--2
第110次检查
第111次检查
pool-1-thread-1--2
第112次检查
第113次检查
pool-1-thread-1--2
第114次检查
第115次检查
pool-1-thread-1--2
第116次检查
第117次检查
pool-1-thread-1--2
第118次检查
第119次检查
pool-1-thread-1--2
第120次检查
第121次检查
pool-1-thread-1--2
第122次检查
第123次检查
pool-1-thread-1--2
第124次检查
第125次检查
pool-1-thread-1--2
第126次检查
第127次检查
pool-1-thread-1--2
第128次检查
第129次检查
pool-1-thread-1--2
第130次检查
第131次检查
pool-1-thread-1--2
第132次检查
第133次检查
pool-1-thread-1--2
第134次检查
第135次检查
pool-1-thread-1--2
第136次检查
第137次检查
pool-1-thread-1--2
第138次检查
第139次检查
pool-1-thread-1--2
第140次检查
第141次检查
pool-1-thread-1--2
第142次检查
第143次检查
pool-1-thread-1--2
第144次检查
第145次检查
pool-1-thread-1--2
第146次检查
第147次检查
pool-1-thread-1--2
第148次检查
第149次检查
pool-1-thread-1--2
第150次检查
第151次检查
pool-1-thread-1--2
第152次检查
第153次检查
pool-1-thread-1--2
第154次检查
第155次检查
pool-1-thread-1--2
第156次检查
第157次检查
pool-1-thread-1--2
第158次检查
第159次检查
pool-1-thread-1--2
第160次检查
第161次检查
pool-1-thread-1--2
第162次检查
第163次检查
pool-1-thread-1--2
第164次检查
第165次检查
pool-1-thread-1--2
第166次检查
第167次检查
pool-1-thread-1--2
第168次检查
第169次检查
pool-1-thread-1--2
第170次检查
第171次检查
pool-1-thread-1--2
第172次检查
第173次检查
Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:43)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第174次检查
---END---

所有的子线程都结束了！

```



shutdownNow()典型日志

```
第0次检查
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:28)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:28)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
java.lang.InterruptedException: sleep interrupted
	at java.lang.Thread.sleep(Native Method)pool-1-thread-1--4

	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:28)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
Exception in thread "pool-1-thread-2" pool-1-thread-3--4
java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:34)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第1次检查
第2次检查
pool-1-thread-1--3
pool-1-thread-3--3
第3次检查
pool-1-thread-1--3
第4次检查
pool-1-thread-3--3
第5次检查
第6次检查
pool-1-thread-3--3
pool-1-thread-1--3
第7次检查
pool-1-thread-3--3
pool-1-thread-1--3
第8次检查
第9次检查
pool-1-thread-1--3
pool-1-thread-3--3
第10次检查
第11次检查
pool-1-thread-1--3
pool-1-thread-3--3
第12次检查
第13次检查
pool-1-thread-1--3
pool-1-thread-3--3
第14次检查
第15次检查
pool-1-thread-1--3
pool-1-thread-3--3
第16次检查
第17次检查
pool-1-thread-1--3
pool-1-thread-3--3
第18次检查
第19次检查
pool-1-thread-1--3
pool-1-thread-3--3
第20次检查
第21次检查
pool-1-thread-1--3
pool-1-thread-3--3
第22次检查
第23次检查
pool-1-thread-1--3
pool-1-thread-3--3
第24次检查
第25次检查
pool-1-thread-1--3
pool-1-thread-3--3
第26次检查
第27次检查
pool-1-thread-1--3
pool-1-thread-3--3
第28次检查
第29次检查
pool-1-thread-1--3
pool-1-thread-3--3
第30次检查
第31次检查
pool-1-thread-1--3
pool-1-thread-3--3
第32次检查
第33次检查
pool-1-thread-1--3
pool-1-thread-3--3
第34次检查
第35次检查
pool-1-thread-1--3
pool-1-thread-3--3
第36次检查
第37次检查
pool-1-thread-1--3
Exception in thread "pool-1-thread-3" java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:34)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第38次检查
第39次检查
pool-1-thread-1--2
第40次检查
第41次检查
pool-1-thread-1--2
第42次检查
第43次检查
pool-1-thread-1--2
第44次检查
第45次检查
pool-1-thread-1--2
第46次检查
第47次检查
pool-1-thread-1--2
第48次检查
第49次检查
pool-1-thread-1--2
第50次检查
第51次检查
pool-1-thread-1--2
第52次检查
第53次检查
pool-1-thread-1--2
第54次检查
第55次检查
pool-1-thread-1--2
第56次检查
第57次检查
pool-1-thread-1--2
第58次检查
第59次检查
pool-1-thread-1--2
第60次检查
第61次检查
pool-1-thread-1--2
第62次检查
第63次检查
pool-1-thread-1--2
第64次检查
第65次检查
pool-1-thread-1--2
第66次检查
第67次检查
pool-1-thread-1--2
第68次检查
第69次检查
pool-1-thread-1--2
第70次检查
第71次检查
pool-1-thread-1--2
第72次检查
第73次检查
pool-1-thread-1--2
第74次检查
第75次检查
pool-1-thread-1--2
第76次检查
第77次检查
pool-1-thread-1--2
第78次检查
第79次检查
pool-1-thread-1--2
第80次检查
第81次检查
pool-1-thread-1--2
第82次检查
第83次检查
pool-1-thread-1--2
第84次检查
第85次检查
pool-1-thread-1--2
第86次检查
第87次检查
pool-1-thread-1--2
第88次检查
第89次检查
pool-1-thread-1--2
第90次检查
第91次检查
pool-1-thread-1--2
第92次检查
第93次检查
pool-1-thread-1--2
第94次检查
第95次检查
pool-1-thread-1--2
第96次检查
第97次检查
pool-1-thread-1--2
第98次检查
第99次检查
pool-1-thread-1--2
第100次检查
第101次检查
pool-1-thread-1--2
第102次检查
第103次检查
pool-1-thread-1--2
第104次检查
第105次检查
Exception in thread "pool-1-thread-1" java.lang.ArithmeticException: / by zero
	at com.sutdyday.studythread.FixThreadPoolExecutorTest$1.run(FixThreadPoolExecutorTest.java:34)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
第106次检查
---END---

Thread[thread123,5,main]
所有的子线程都结束了！

```

https://blog.csdn.net/zhaojianting/article/details/97664370
