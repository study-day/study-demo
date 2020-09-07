package com.studyday.studyjsp.servlet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
   *  当页面启动时执行，展示更多的配置参数。
 *1. 如果没有service方法，则不接收请求，不用配置url
 *2. 在项目启动时实例化，
 *3. servlet 创建的两个时机，1.应用启动时 2.用户请求之时。
 *4. 应用启动时创建servlet,通常用于某些后台服务的servlet，或者需要拦截很多请求的servlet。 
 *5. 展示了参数的使用方法。 //通过ServletConfig 获取@WebInitParam配置的参数
 * @author study-day
 *
 */
@WebServlet(loadOnStartup = 1,
urlPatterns = {"/loadOnStartServlet2"},
initParams = {
		@WebInitParam(name="qq",value = "1234678")
		})
public class LoadOnStartServlet2 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1582675617921261237L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("LoadOnStartServlet2-应用启动后开始时启动。。");
		 
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		resp.getOutputStream().print("hello world");
	}
}
