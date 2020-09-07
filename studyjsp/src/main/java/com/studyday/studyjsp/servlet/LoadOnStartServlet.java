package com.studyday.studyjsp.servlet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/**
   *  当页面启动时执行，模拟一个后台程序，每个一分钟打印一次时间。
 *1. 如果没有service方法，则不接收请求，不用配置url
 *2. 在项目启动时实例化，
 *3. servlet 创建的两个时机，1.应用启动时 2.用户请求之时。
 *4. 应用启动时创建servlet,通常用于某些后台服务的servlet，或者需要拦截很多请求的servlet。 
 * @author study-day
 *
 */
@WebServlet(loadOnStartup = 1)
public class LoadOnStartServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1582675617921261237L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("LoadOnStartServlet 应用启动后启动 ");
		new javax.swing.Timer(1000,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			System.out.println(new Date());	
			}
		}).start();
		
		
	}
}
