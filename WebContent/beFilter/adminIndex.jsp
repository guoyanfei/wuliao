<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wuliao.dandan.model.Admin"%>
<%@ taglib prefix="pp" uri="/WEB-INF/pagetag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>adminIndex.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language='javascript'>
	function tiaozhuan() {
		var page = document.myform.page.value;
		var name = document.searchForm.name.value;
		if(!isNaN(page)) {
			document.myform.action = "FindAdminPageServlet?name="+ name +"&page=" + page;
			document.myform.submit();
		} else {
			alert("页码必须为数字！");
			return;
		}
	}
	
	function delcfm() {
		if (!confirm("确认要删除？")) {
			window.event.returnValue = false;
		}
	}
</script>
</head>

<body>
	<center>
		<font size="6" color="black">用户列表</font> <br>
		<%
			Admin admin = (Admin) session.getAttribute("admin");
		%>
		<font size="5" color="black">欢迎您，尊敬的</font><font size="4" color="red"><%=admin.getUsername()%></font>。
		<br>
		<form method="post" name="searchForm" action="FindAdminPageServlet">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA" cellspacing="0px">
				<tr>
					<td align="left">
						<input type="hidden" value="${nowPage}" name="page">
						用户名：<input type="text" value="${name}" name="name">&nbsp;
						<input type="submit" value="查询">
					</td>
					<td align="right"><a href="beFilter/adminInsert.jsp"><font size="5" color="black">添加</font></a></td>
				</tr>
			</table>
		</form>
		<form method="post" name="myform">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<th height=50 width="12%">工号</th>
					<th height=50 width="12%">用户名</th>
					<th height=50 width="12%">密码</th>
					<th height=50 width="12%">姓名</th>
					<th height=50 width="12%">性别</th>
					<th height=50 width="12%">年龄</th>
					<th height=50 width="12%">权限</th>
					<th height=50 width="12%">操作</th>
				</tr>
				<%
					List<Admin> list = (List<Admin>) request.getAttribute("admins");
					for (Admin uu : list) {
				%>
				<tr>
					<td align="center"><%=uu.getId()%></td>
					<td align="center"><%=uu.getUsername()%></td>
					<td align="center"><%=uu.getPassword()%></td>
					<td align="center"><%=uu.getRealname()%></td>
					<td align="center"><%=uu.getSex()%></td>
					<td align="center"><%=uu.getAge()%></td>
					<td align="center">
						<%
							if(uu.getAuth() == 0) {out.print("超级管理员");}
							else if(uu.getAuth() == 1) {out.print("普通管理员");}
							else if(uu.getAuth() == 2) {out.print("普通员工");}
							else {out.print("数据有误！");}
						%>
					</td>
					<td align="center">
						<a href="EditAdminServlet?id=<%=uu.getId()%>">更新</a>
						<a href="DeleteAdminServlet?id=<%=uu.getId()%>" onclick="delcfm()">删除</a>
					</td>
				</tr>
				<%
					}
				%>
			</table>

			<pp:pagelink pageCurrent="${nowPage}" totalPage="${lastpage}"
				path="FindAdminPageServlet?name=${name}&" param="page"></pp:pagelink>
			第 <input type="text" name="page" size="3" /> 页 <input type="button"
				value="跳转" onclick="tiaozhuan()" />
		</form>
	</center>
</body>
</html>
