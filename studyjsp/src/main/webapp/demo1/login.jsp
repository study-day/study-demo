<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post" accept-charset="utf-8">
		<table>
			<tbody>
				<tr>
					<td>消息:</td>
					<td><%=request.getAttribute("msg")%></td>
				</tr>
				<tr>
					<td>请输入用户名：</td>
					<td><input name="name"></td>
				</tr>
				<tr>
					<td>请输入密码：</td>
					<td><input name="passwd"></td>
				</tr>
				<tr>
					<td><button type="submit">登录</button></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>