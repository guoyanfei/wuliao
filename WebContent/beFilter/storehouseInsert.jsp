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

<title>storehouseInsert.jsp</title>
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
		if (text == "true" && checkRedline() && checkQuantity()) {
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
	
	function checkQuantity() {
		var quantity = document.myform.quantity.value;
		if(quantity == null || quantity == "") {
			quantityMsg.innerHTML = "";
			quantityMsg.innerHTML = "数量不能为空";
			return false;
		}
		if(!isNaN(quantity)) {
			quantityMsg.innerHTML = "";
			return true;
		} else {
			quantityMsg.innerHTML = "";
			quantityMsg.innerHTML = "数量必须为数字！";
			return false;
		}
	}
	
</script>
</head>

<body>
	<center>
		<font size="6" color="red">修&nbsp;&nbsp;&nbsp;&nbsp;改</font> <br>
		<form action='InsertStorehouseServlet' method='post' name="myform">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<td height=50 width="12%" align="right">元器件名称：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='componentName' id="componentName" size="20" onBlur="checkName()"
						value='' /> <span id="checkStr"></span></td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">生产商：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='producer' size="18" value=''/>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">元器件属性值：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='value' size="20" value=''/>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">经销商：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='agency' value='' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">价格：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='price' value='' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">简称：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='shortName' value='' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">封装：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='potting' value='' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">备注：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='remark' value='' />
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">数量：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='quantity' id='quantity' value='' onblur="checkQuantity();"/>
						<font size="3" color="red" id="quantityMsg"></font>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">警戒线：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='redline' id='redline' value='' onblur="checkRedline();"/>
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
