<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>
<h2><%=request.getAttribute("msg") %></h2>
<fieldset>
<p>
request.getParameter() 和request.getAttribute() 区别<br/>
getParameter 是用来接受用post个get方法传递过来的参数的., 如果在jsp中出现，那么获取的时提交到这个页面的参数。比如？qq="12133"
getAttribute 必须先setAttribute.<br/>

（1）request.getParameter() 取得是通过容器的实现来取得通过类似post，get等方式传入的数据，request.setAttribute()和getAttribute()只是在web容器内部流转，仅仅是请求处理阶段。
<br/>
（2）request.getParameter() 方法传递的数据，会从Web客户端传到Web服务器端，代表HTTP请求数据。request.getParameter()方法返回String类型的数据。
<br/>
request.setAttribute() 和 getAttribute() 方法传递的数据只会存在于Web容器内部
<br/>
</p>
</fieldset>
</body>
</html>