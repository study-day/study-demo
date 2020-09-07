package com.studyday.studyjsp.demo1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347712989146255834L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//super.doGet(req, resp);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/demo1/login.jsp");
		req.setAttribute("msg", "请输入用户名和密码");
		requestDispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//super.doPost(req, resp);
		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");
		StringBuffer msg = new StringBuffer(); 
		if(name==null || name.isEmpty()) {
			msg.append("请输入用户名，");
		}
		if(passwd==null || passwd.isEmpty()) {
			msg.append("请输入输入用户名，");
		}
		
		if(!msg.toString().isEmpty()) {
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/demo1/login.jsp");
			req.setAttribute("msg", msg);
			requestDispatcher.forward(req, resp);
			return;
		}
		if(passwd.equals(name)) {
			msg.append("登录成功！欢迎");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/demo1/welcome.jsp");
			req.setAttribute("msg", msg.toString());
			requestDispatcher.forward(req, resp);
			return;
		}else {
			msg.append("用户名密码相等，请重新输入");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/demo1/login.jsp");
			req.setAttribute("msg", msg);
			requestDispatcher.forward(req, resp);
			return;
		}
	}
	
	
}
