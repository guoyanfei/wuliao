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

<title>storehouseOut.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="../js/jquery-1.8.3.js"></script>

<script language='javascript'>
	
	function submit_fun() {
		if (checkOutQuantity()) {
			myform.submit();
		} else {
			return;
		}
	}
	
	function checkOutQuantity() {
		var outQuantity = document.myform.outQuantity.value;
		var quantity = document.myform.quantity.value;
		
		if(outQuantity == null || outQuantity == "") {
			outQuantityMsg.innerHTML = "";
			outQuantityMsg.innerHTML = "出库数目不能为空！";
			return false;
		}
		
		if(!isNaN(outQuantity)) {
			if (parseInt(outQuantity) > parseInt(quantity)) {
				outQuantityMsg.innerHTML = "";
				outQuantityMsg.innerHTML = "出库数目不能超过库存！";
				return false;
			}
			outQuantityMsg.innerHTML = "";
			return true;
		} else {
			outQuantityMsg.innerHTML = "";
			outQuantityMsg.innerHTML = "出库数目必须为数字！";
			return false;
		}
	}
	
</script>
</head>

<body>
	<center>
		<font size="6" color="red">出&nbsp;&nbsp;&nbsp;&nbsp;库</font> <br>
		<%
			Storehouse storehouse = (Storehouse) request.getAttribute("storehouse");
		%>
		<form action='UpdateStorehouseServlet' method='post' name="myform">
			<input type = "hidden" name="id" value="<%=storehouse.getId()%>">
			<table border="1px" width="90%" bgcolor="E7F1FF" bordercolor="BFCCDA"
				cellspacing="0px">
				<tr>
					<td height=50 width="12%" align="right">元器件名称：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='componentName' id="componentName" size="20" 
						value='<%=storehouse.getComponentName() != null? storehouse.getComponentName(): ""%>' />
						<%=storehouse.getComponentName() != null? storehouse.getComponentName(): ""%>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">生产商：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='producer' size="18" value='<%=storehouse.getProducer() != null? storehouse.getProducer(): ""%>'/>
						<%=storehouse.getProducer() != null? storehouse.getProducer(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">元器件属性值：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='value' size="20" value='<%=storehouse.getValue() != null? storehouse.getValue(): ""%>'/>
						<%=storehouse.getValue() != null? storehouse.getValue(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">经销商：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='agency' value='<%=storehouse.getAgency() != null? storehouse.getAgency(): ""%>' />
						<%=storehouse.getAgency() != null? storehouse.getAgency(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">价格：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='price' value='<%=storehouse.getPrice() != null? storehouse.getPrice(): ""%>' />
						<%=storehouse.getPrice() != null? storehouse.getPrice(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">简称：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='shortName' value='<%=storehouse.getShortName() != null? storehouse.getShortName(): ""%>' />
						<%=storehouse.getShortName() != null? storehouse.getShortName(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">封装：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='potting' value='<%=storehouse.getPotting() != null? storehouse.getPotting(): ""%>' />
						<%=storehouse.getPotting() != null? storehouse.getPotting(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">备注：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='remark' value='<%=storehouse.getRemark() != null? storehouse.getRemark(): ""%>' />
						<%=storehouse.getRemark() != null? storehouse.getRemark(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">数量：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='quantity' value='<%=storehouse.getQuantity() != null? storehouse.getQuantity(): ""%>' />
						<%=storehouse.getQuantity() != null? storehouse.getQuantity(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">警戒线：</td>
					<td height=50 width="18%" valign="top">
						<input type='hidden' name='redline' value='<%=storehouse.getRedline() != null? storehouse.getRedline(): ""%>'/>
						<%=storehouse.getRedline() != null? storehouse.getRedline(): ""%>
					</td>
				</tr>
				<tr>
					<td height=50 width="12%" align="right">出库数目：</td>
					<td height=50 width="18%" valign="top">
						<input type='text' name='outQuantity' id='outQuantity' value='' onblur="checkOutQuantity();"/>
						<font color="red" size="3" id="outQuantityMsg"></font>
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
