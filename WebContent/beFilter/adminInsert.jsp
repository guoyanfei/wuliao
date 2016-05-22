<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wuliao.dandan.model.Admin"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>adminInsert.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="../js/jquery-1.8.3.js"></script>

<script language='javascript'>
	var text = "true";
	function checkNull2() {
		var pass = document.myform.password;
		var rpass = document.myform.password2;
		if (pass.value == "") {
			myPwd.innerHTML = "密码不能为空！";
			myPwd2.innerHTML = "";
			return false;
		} else if (pass.value.length<4 || pass.value.length>20) {
			myPwd.innerHTML = "密码的长度必须在4--20个字符！";
			myPwd2.innerHTML = "";
			return false;
		} else if (rpass.value != pass.value) {
			myPwd.innerHTML = "";
			myPwd2.innerHTML = "确认密码与密码输入不一致！";
			return false;
		} else {
			myPwd.innerHTML = "";
			myPwd2.innerHTML = "";
			return true;
		}
	}

	function checkName() {
		if (myform.username.value == "") {
			checkStr.innerHTML = "<font color='red'>用户名不能为空！</font>";
			return;
		} else {
			checkStr.innerHTML = "";
		}
		var name2 = $("#username").val();
		var preName = $("#preUsername").val();
		$.get("CheckUsernameServlet?username=" + name2 + "&preUsername=" + preName, null, handleRequest);
	}

	function handleRequest(data) {
		var sp = $("#checkStr");
		text = data;
		if (data == "false") {
			sp.html("<font color='red'>对不起，用户名已经存在！</font>");
		} else {
			sp.html("<font color='green'>用户名可以使用！</font>");
		}
	}
	function submit_fun() {
		if (text == "true" && checkNull2() && checkAge()) {
			myform.submit();
		} else {
			return;
		}
	}
	
	function checkAge() {
		var age = document.myform.age.value;
		if(!isNaN(age)) {
			ageMsg.innerHTML = "";
			return true;
		} else {
			ageMsg.innerHTML = "";
			ageMsg.innerHTML = "年龄必须为数字！";
			return false;
		}
	}
</script>
</head>

<body>
	<center>
		<font size="6" color="red">添&nbsp;&nbsp;&nbsp;&nbsp;加</font> <br>
		<form action='InsertAdminServlet' method='post' name="myform">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<td height=50 width="12%" align="right">用户名：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='username' id="username" size="20" onBlur="checkName()"
						value="" /> <span id="checkStr"></span></td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">密码：</td>
					<td height=50 width="18%" valign="top">
						<input type='password' name='password' size="18" onBlur="checkNull2()" value=""/>
						<font size="3" color="red" id="myPwd"></font>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">重复密码：</td>
					<td height=50 width="18%" valign="top">
						<input type='password' name='password2' size="18" onBlur="checkNull2()" value=""/>
						<font size="3" color="red" id="myPwd2"></font>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">姓名：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='realname' value="" />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">性别：</td>
					<td height=50 width="18%" valign="top">
						<input type='radio' name='sex' value="男" checked/> 男 
						<input type='radio' name='sex' value="女" /> 女
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">年龄：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='age' value="" onblur="checkAge();"/>
						<font size="3" color="red" id="ageMsg"></font>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">权限：</td>
					<td height=50 width="18%" valign="top">
						<input type='radio' name='auth' value="2" checked/> 普通员工
						<input type='radio' name='auth' value="1" /> 普通管理员
						<input type='radio' name='auth' value="0" /> 超级管理员
					</td>
				</tr>
				<tr align='center'>
					<td colspan="2">
						<input type='button' value="提交" onclick='submit_fun()' /> &nbsp;&nbsp; 
						<input type='reset' value="重置" />
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
