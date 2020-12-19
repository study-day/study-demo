package com.studyday.studyjsp.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

/**
 * web.xml 配置发布的servlet ，使用servlet的配置参数
 *
 * @author lq
 */
public class Servlet3 extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        // 不要调用父方法 会出现HTTP状态 405 - 方法不允许
        //super.service(req, resp);
        ServletConfig servletConfig = getServletConfig();
        //通过ServletConfig 获取web.xml配置的参数
        String driver = servletConfig.getInitParameter("driver");
        String url = servletConfig.getInitParameter("url");
        String user = servletConfig.getInitParameter("user");
        String passwd = servletConfig.getInitParameter("passwd");
        String html = "";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, passwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select 1");

            while (resultSet.next()) {
                int int1 = resultSet.getInt(1);
                System.out.println(int1);
                html = html + int1;
            }

            resp.getOutputStream().print(html);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            //
            e.printStackTrace();
        }

    }


}
