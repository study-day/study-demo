package com.sutdyday.studythread;

/**
 * 	异常会不会让程序终止？ 异常会让一个main程序终止。
 *         异常会不会让线程终止？ 会让子线程终止，如果主线程没有捕获异常那么也会挂掉。如果捕获了，不会受其影响，主线程统计活着的线程数会减一
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        System.out.println( "Hello World!" );
        
        boolean b=  true;
        while (true) {
          	if(b) {
        		b =false;
        		try {
        			new TestThread().start();	
        		}catch (Exception e) {
					// TODO: handle exception
        			e.printStackTrace();
				}
        	 	
        	}
        		Thread.sleep(1000);
        		System.out.println("live"+Thread.activeCount());
        		
		}
        
        
    }
   static class TestThread extends Thread{

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
        		long x = 1/(System.currentTimeMillis()%2);
			System.out.println(x);
		}
		}
    	
    	
    }
}
