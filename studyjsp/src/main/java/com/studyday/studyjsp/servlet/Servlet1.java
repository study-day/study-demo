package com.studyday.studyjsp.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用注解发布
 * 
 * @author study-day
 *
 */
@WebServlet(name="Servlet1",urlPatterns = {"/myservlet1"})
public class Servlet1 extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(req, resp);
		String param1 = req.getParameter("parm1");
		System.out.println(param1);
		PrintStream printStream = new PrintStream(resp.getOutputStream());
		printStream.println("<html>");
		printStream.println("<h2>");
		printStream.println("Welcom! this is servlet1,");
		printStream.println("</h2>");
		printStream.println("</html>");
	
		System.out.println("I am coming");
	}
	
	
}
