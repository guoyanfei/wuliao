<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

<title>storehouseIndex.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="../js/jquery-1.8.3.js"></script>
<script language='javascript'>
	function tiaozhuan() {
		var page = document.myform.page.value;
		var componentId = document.searchForm.componentId.value;
		var componentName = document.searchForm.componentName.value;
		var notEnoughTag = document.searchForm.notEnoughTag.value;
		if(!isNaN(page)) {
			document.myform.action = "FindStorehousePageServlet?componentIdSe=" + componentId + "&componentNameSe=" + componentName + "&notEnoughTagSe=" + notEnoughTag + "&page=" + page;
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
	
	function submitForm() {
		if(!isNaN(document.getElementById("componentIdSe").value)) {
			document.searchForm.submit();
		} else {
			alert("料号必须为数字！");
			return;
		}
	}
	
	function calculateMoney() {
		var filePath = $("#filePath");
		filePath.select();
		var realpath = document.selection.createRange().text;
		
		$.get("CalculateMoneyServlet?filePath=" + realpath, null, handleRequest);
	}

	function handleRequest(data) {
		var addMoney = $("#addMoney");
		text = data;
		if (data == "false1") {
			addMoney.html("<font color='red'>表格格式不正确！</font>");
		} else if (data == "false2") {
			addMoney.html("<font color='red'>文件路径错误！</font>");
		} else {
			addMoney.html("<font color='green'>成本： " + data + " 元</font>");
		}
	}
</script>
</head>

<body>
	<center>
		<font size="6" color="black">库存列表</font> <br>
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
				<td align="right">
					<%
						if (admin.getAuth() == 1) {
					%>
					计算成本：<input type="file" name="filePath" id="filePath" onchange="calculateMoney();">
					<span id="addMoney"></span>
					<%
						}
					%>
				</td>
			</tr>
		</table>
		<br>
		<form method="post" name="searchForm" action="FindStorehousePageServlet">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<td align="left">
						<input type="hidden" value="${nowPage}" name="page">
						料号：<input type="text" value="${componentIdSe}" name="componentIdSe" id="componentIdSe">&nbsp; 
						元器件名称：<input type="text" value="${componentNameSe}" name="componentNameSe">&nbsp;
						余量是否不足： 
						<select name="notEnoughTagSe">
							<%
								if (request.getAttribute("notEnoughTagSe").equals("1")) {
							%>
								<option value="2">全部</option>
								<option value="1" selected>不足</option>
								<option value="0">足</option>
							<%
								} else if (request.getAttribute("notEnoughTagSe").equals("0")) {
							%>
							<option value="2">全部</option>
							<option value="1">不足</option>
							<option value="0" selected>足</option>
							<%
								} else {
							%>
							<option value="2">全部</option>
							<option value="1">不足</option>
							<option value="0">足</option>
							<%
								}
							%>
						</select> &nbsp;
						<input type="button" value="查询" onclick="submitForm();">
					</td>
					<%
						if (admin.getAuth() == 1) {
					%>
					<td align="right"><a href="/beFilter/storehouseInsert.jsp"><font
							size="5" color="black">添加新元器件</font></a></td>
					<%
						}
					%>
				</tr>
			</table>
		</form>
		<form method="post" name="myform">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<th height=50>料号</th>
					<th height=50>元器件名称</th>
					<th height=50>生产商</th>
					<th height=50>元器件属性值</th>
					<th height=50>经销商</th>
					<th height=50>价格</th>
					<th height=50>简称</th>
					<th height=50>封装</th>
					<th height=50>备注</th>
					<th height=50>数量</th>
					<%
						if (admin.getAuth() == 1) {
					%>
					<th height=50 width="16%">操作</th>
					<%	
						}
					%>
				</tr>
				<%
					List<Storehouse> list = (List<Storehouse>) request.getAttribute("storehouses");
					for (Storehouse uu : list) {
				%>
				<tr>
					<td align="center"><%=uu.getId()%></td>
					<td align="center"><%=uu.getComponentName()%></td>
					<td align="center"><%=uu.getProducer()%></td>
					<td align="center"><%=uu.getValue()%></td>
					<td align="center"><%=uu.getAgency()%></td>
					<td align="center"><%=uu.getPrice()%></td>
					<td align="center"><%=uu.getShortName()%></td>
					<td align="center"><%=uu.getPotting()%></td>
					<td align="center"><%=uu.getRemark()%></td>
					<td align="center">
						<%
							if (uu.getQuantity() <= uu.getRedline()) {
						%>
						<font color="red"><%=uu.getQuantity()%></font>
						<%	
							} else {
						%>
						<font color="blue"><%=uu.getQuantity()%></font>
						<%	
							}
						%>
					</td>
					<td align="center">
					<%
						if (admin.getAuth() == 1) {
					%>
						<a href="EditStorehouseServlet?id=<%=uu.getId()%>&tag=1">修改</a>
						<a href="EditStorehouseServlet?id=<%=uu.getId()%>&tag=2">入库</a>
						<a href="EditStorehouseServlet?id=<%=uu.getId()%>&tag=3">出库</a>
						<a href="DeleteStorehouseServlet?id=<%=uu.getId()%>&componentName=<%=uu.getComponentName()%>&username=<%=admin.getUsername()%>" onclick="delcfm()">删除</a>
					<%	
						}
					%>
					</td>
				</tr>
				<%
					}
				%>
			</table>

			<pp:pagelink pageCurrent="${nowPage}" totalPage="${lastpage}"
				path="FindStorehousePageServlet?componentIdSe=${componentId}&componentNameSe=${componentName}&notEnoughTagSe=${notEnoughTag}&" param="page"></pp:pagelink>
			第 <input type="text" name="page" size="3" /> 页 <input type="button"
				value="跳转" onclick="tiaozhuan()" />
		</form>
	</center>
</body>
</html>
