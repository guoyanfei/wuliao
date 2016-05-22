<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>login.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language='javascript'>
	function checkNull1() {
		if (myform.username.value == "") {
			myName.innerHTML = "用户名不能为空！";
			return false;
		} else {
			myName.innerHTML = "";
			return true;
		}
	}
	
	function checkNull2() {
		if (myform.password.value == "") {
			myPwd.innerHTML = "密码不能为空！";
			return false;
		} else {
			myPwd.innerHTML = "";
			return true;
		}
	}
	
	function submitForm() {
		if (checkNull1() && checkNull2()) {
			myform.submit();
		}
	}
</script>

</head>

<body>
	<center>
		<font size="6" color="red">登&nbsp;&nbsp;&nbsp;&nbsp;录</font> <br>
		<form method="post" action="LoginServlet" name="myform">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<td height=50 width="12%" align="right">
						用户名：
					</td>
					<td height=50 width="18%" valign="top">
						<input type="text" name="username" size="20" onBlur="checkNull1()" value="" />
						<font size="3" color="red" id="myName"></font>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">
						密码：
					</td>
					<td height=50 width="18%" valign="top">
						<input type="password" name="password" size="18" onBlur="checkNull2()" value="" />
						<font size="3" color="red" id="myPwd"></font>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="radio" name="auth" value="2" checked/> 用户
						<input type="radio" name="auth" value="1" /> 普通管理员
						<input type="radio" name="auth" value="0" /> 超级管理员
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<%
							if (request.getAttribute("error") != null) {
								out.print(request.getAttribute("error"));
							}
						%>
						<br>
						<input type="button" value="登录" onclick="submitForm();"/>
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="reset" value="重置" />
					</td>
				</tr>
			</table>
			<br>
		</form>
	</center>
</body>

</html>
