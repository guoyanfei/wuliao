<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wuliao.dandan.model.*"%>
<%@ taglib prefix="pp" uri="/WEB-INF/pagetag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>logIndex.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script language='javascript'>
	function tiaozhuan() {
		var page = document.myform.page.value;
		var usernameSe = document.searchForm.usernameSe.value;
		var componentNameSe = document.searchForm.componentNameSe.value;
		var tagSe = document.searchForm.tagSe.value;
		var queryBegintime = document.searchForm.queryBegintime.value;
		var queryEndtime = document.searchForm.queryEndtime.value;
		if(!isNaN(page)) {
			document.myform.action = "FindLogPageServlet?usernameSe="+ usernameSe + "&componentNameSe="+ componentNameSe +"&tagSe="+ tagSe +"&queryBegintime="+ queryBegintime +"&queryEndtime="+ queryEndtime + "&page=" + page;
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
		<font size="6" color="black">日志列表</font> <br>
		<%
			Admin admin = (Admin) session.getAttribute("admin");
		%>
		<font size="5" color="black">欢迎您，尊敬的</font><font size="4" color="red"><%=admin.getUsername()%></font>。
		<br>
		<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA" cellspacing="0px">
			<tr>
				<td align="left">
					<a href="FindStorehousePageServlet?page=1">库存列表</a>
					<a href="FindLogPageServlet?page=1">查看日志</a>
				</td>
			</tr>
		</table>
		<br>
		<form method="post" name="searchForm" action="FindLogPageServlet">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA" cellspacing="0px">
				<tr>
					<td align="left">
						<input type="hidden" value="${nowPage}" name="page">
						元器件名称：<input type="text" value="${componentNameSe}" name="componentNameSe" size="8">&nbsp;
						操作人账号：<input type="text" value="${usernameSe}" name="usernameSe" size="8">&nbsp;
						操作名：
						<select name="tagSe">
						<%
							if (request.getAttribute("tagSe") != null && request.getAttribute("tagSe").equals("1")) {
						%>
							<option value="0"></option>
							<option value="1" selected>出库</option>
							<option value="2">入库</option>
							<option value="3">修改</option>
							<option value="4">删除</option>
							<option value="5">新增</option>
						<%	
							} else if (request.getAttribute("tagSe") != null && request.getAttribute("tagSe").equals("2")) {
						%>
							<option value="0"></option>
							<option value="1">出库</option>
							<option value="2" selected>入库</option>
							<option value="3">修改</option>
							<option value="4">删除</option>
							<option value="5">新增</option>
						<%	
							} else if (request.getAttribute("tagSe") != null && request.getAttribute("tagSe").equals("3")) {
						%>
							<option value="0"></option>
							<option value="1">出库</option>
							<option value="2">入库</option>
							<option value="3" selected>修改</option>
							<option value="4">删除</option>
							<option value="5">新增</option>
						<%	
							} else if (request.getAttribute("tagSe") != null && request.getAttribute("tagSe").equals("4")) {
						%>
							<option value="0"></option>
							<option value="1">出库</option>
							<option value="2">入库</option>
							<option value="3">修改</option>
							<option value="4" selected>删除</option>
							<option value="5">新增</option>
						<%	
							} else if (request.getAttribute("tagSe") != null && request.getAttribute("tagSe").equals("5")) {
						%>
							<option value="0"></option>
							<option value="1">出库</option>
							<option value="2">入库</option>
							<option value="3">修改</option>
							<option value="4">删除</option>
							<option value="5" selected>新增</option>
						<%	
							} else {
						%>
							<option value="0"></option>
							<option value="1">出库</option>
							<option value="2">入库</option>
							<option value="3">修改</option>
							<option value="4">删除</option>
							<option value="5">新增</option>
						<%	
							}
						%>
						</select>
						&nbsp;
						操作时间：
						<input name="queryBegintime" readonly="true" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" type="text" id="queryBegintime" value="${queryBegintime}" style='width:100px'/>至 
						<input name="queryEndtime" id="queryEndtime" readonly="true" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" type="text" style='width:100px' value="${queryEndtime}"/>
						<input type="submit" value="查询">
					</td>
				</tr>
			</table>
		</form>
		<form method="post" name="myform">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA" cellspacing="0px">
				<tr>
					<th height=50 width="12%">编号</th>
					<th height=50 width="12%">库存编号</th>
					<th height=50 width="12%">元器件名称</th>
					<th height=50 width="12%">操作人账号</th>
					<th height=50 width="12%">操作名</th>
					<th height=50 width="12%">数量</th>
					<th height=50 width="12%">操作时间</th>
					<th height=50 width="12%">操作</th>
				</tr>
				<%
					List<Log> list = (List<Log>) request.getAttribute("logs");
					for (Log uu : list) {
				%>
				<tr>
					<td align="center"><%=uu.getId()%></td>
					<td align="center"><%=uu.getStorehouseId()%></td>
					<td align="center"><%=uu.getComponentName()%></td>
					<td align="center"><%=uu.getUsername()%></td>
					<td align="center">
						<%
							if (uu.getTag() != null && uu.getTag() == 1) {
						%>
							出库
						<%	
							} else if (uu.getTag() != null && uu.getTag() == 2) {
						%>
							入库
						<%	
							} else if (uu.getTag() != null && uu.getTag() == 3) {
						%>
							修改
						<%	
							} else if (uu.getTag() != null && uu.getTag() == 4) {
						%>
							删除
						<%	
							} else if (uu.getTag() != null && uu.getTag() == 5) {
						%>
							新增
						<%	
							} else {
						%>
							
						<%	
							}
						%>
					</td>
					<td align="center"><%=uu.getQuantity()%></td>
					<td align="center"><%=uu.getCreatedAt()%></td>
					<td align="center">
						<a href="DeleteLogServlet?id=<%=uu.getId()%>" onclick="delcfm()">删除</a>
					</td>
				</tr>
				<%
					}
				%>
			</table>

			<pp:pagelink pageCurrent="${nowPage}" totalPage="${lastpage}"
				path="FindLogPageServlet?usernameSe=${usernameSe}&componentNameSe=${componentNameSe}&tagSe=${tagSe}&queryBegintime=${queryBegintime}&queryEndtime=${queryEndtime}&" param="page"></pp:pagelink>
			第 <input type="text" name="page" size="3" /> 页 <input type="button"
				value="跳转" onclick="tiaozhuan()" />
		</form>
	</center>
</body>
</html>
