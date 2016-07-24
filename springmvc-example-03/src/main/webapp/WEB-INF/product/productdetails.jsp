<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>Spring MVC Demo</title>
</head>
<body>
	<form action="">
		<fieldset>
			<legend>产品明细</legend>
			<div id="globle">
				<h4>${msg}</h4>
				<p>
					<h5>明细：</h5>
					名称：${product.name}<br/>
					描述：${product.description}<br/>
					价格：${product.price}
				</p>
			</div>
		</fieldset>
	</form>
	<br>
</body>
</html>
