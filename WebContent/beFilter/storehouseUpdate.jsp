<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wuliao.dandan.model.*"%>
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

<title>storehouseUpdate.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="../js/jquery-1.8.3.js"></script>

<script language='javascript'>
	var text = "true";

	function checkName() {
		if (myform.componentName.value == "") {
			checkStr.innerHTML = "<font color='red'>元器件名称不能为空！</font>";
			return;
		} else {
			checkStr.innerHTML = "";
		}
		var name2 = $("#componentName").val();
		var preName = $("#preComponentName").val();
		$.get("CheckComponentNameServlet?componentName=" + name2 + "&preComponentName=" + preName, null, handleRequest);
	}

	function handleRequest(data) {
		var sp = $("#checkStr");
		text = data;
		if (data == "false") {
			sp.html("<font color='red'>对不起，元器件名称已经存在！</font>");
		} else {
			sp.html("<font color='green'>元器件名称可以使用！</font>");
		}
	}
	
	function submit_fun() {
		if (text == "true" && checkRedline()) {
			myform.submit();
		} else {
			return;
		}
	}
	
	function checkRedline() {
		var redline = document.myform.redline.value;
		if(redline == null || redline == "") {
			redlineMsg.innerHTML = "";
			redlineMsg.innerHTML = "警戒线不能为空！";
			return false;
		}
		if(!isNaN(redline)) {
			redlineMsg.innerHTML = "";
			return true;
		} else {
			redlineMsg.innerHTML = "";
			redlineMsg.innerHTML = "警戒线必须为数字！";
			return false;
		}
	}
	
</script>
</head>

<body>
	<center>
		<font size="6" color="red">修&nbsp;&nbsp;&nbsp;&nbsp;改</font> <br>
		<%
			Storehouse storehouse = (Storehouse) request.getAttribute("storehouse");
		%>
		<form action='UpdateStorehouseServlet' method='post' name="myform">
			<input type = "hidden" name="id" value="<%=storehouse.getId()%>">
			<input type = "hidden" name="preComponentName" id="preComponentName" value='<%=storehouse.getComponentName() != null? storehouse.getComponentName(): ""%>'>
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<td height=50 width="12%" align="right">元器件名称：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='componentName' id="componentName" size="20" onBlur="checkName()"
						value='<%=storehouse.getComponentName() != null? storehouse.getComponentName(): ""%>' /> <span id="checkStr"></span></td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">生产商：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='producer' size="18" value='<%=storehouse.getProducer() != null? storehouse.getProducer(): ""%>'/>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">元器件属性值：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='value' size="20" value='<%=storehouse.getValue() != null? storehouse.getValue(): ""%>'/>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">经销商：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='agency' value='<%=storehouse.getAgency() != null? storehouse.getAgency(): ""%>' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">价格：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='price' value='<%=storehouse.getPrice() != null? storehouse.getPrice(): ""%>' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">简称：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='shortName' value='<%=storehouse.getShortName() != null? storehouse.getShortName(): ""%>' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">封装：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='potting' value='<%=storehouse.getPotting() != null? storehouse.getPotting(): ""%>' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">备注：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='remark' value='<%=storehouse.getRemark() != null? storehouse.getRemark(): ""%>' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">警戒线：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='redline' id='redline' value='<%=storehouse.getRedline() != null? storehouse.getRedline(): ""%>' onblur="checkRedline();"/>
						<font size="3" color="red" id="redlineMsg"></font>
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
