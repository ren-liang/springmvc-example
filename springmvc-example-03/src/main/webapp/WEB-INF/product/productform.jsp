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
	${abc}
	<%-- <form action="<%=basePath%>product/saveproduct" method="post"> --%>
	<form action="<%=basePath%>product/newsaveproduct" method="post">
		<fieldset>
			<legend>添加产品</legend>
			<p>
				<label for="name">产品名称：</label>
				<input type="text" id="name" name="name" tabindex="1">
			</p>
			<p>
				<label for="description">描述：</label>
				<input type="text" id="description" name="description" tabindex="2">
			</p>
			<p>
				<label for="price">价格：</label>
				<input type="text" id="price" name="price" tabindex="3">
			</p>
			<p id="buttons">
				<input type="reset" id="reset" value="重置" tabindex="4">
				<input type="submit" id="submit" value="保存" tabindex="5">
			</p>
		</fieldset>
	</form>
	<br>
</body>
</html>
