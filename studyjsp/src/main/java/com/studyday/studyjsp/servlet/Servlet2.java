package com.studyday.studyjsp.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * web.xml 配置发布的servlet
 * @author study-day
 *
 */
public class Servlet2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 
		System.out.println("get! welcome");
		resp.setContentType("text/html; charset=UTF-8");
		PrintStream printStream = new PrintStream(resp.getOutputStream());
		printStream.println("<html>");
		printStream.println("<h2>");
		printStream.println("Welcom! this is servlet2,");
		printStream.println("</h2>");
		printStream.println("</html>");
		
	
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//super.doPost(req, resp);
//		resp.setContentType("text/html;charSet=GBK");
//		System.out.println("get! welcome");
//		PrintStream printStream = new PrintStream(resp.getOutputStream());
//		printStream.println("<html>");
//		printStream.println("<h2>");
//		printStream.println("Welcom! this is servlet2,");
//		printStream.println("</h2>");
//		printStream.println("</html>");
//
//	}
	
}
