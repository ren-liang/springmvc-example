<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>Spring MVC Demo</title>
</head>
<body>
	<div>
		<h1>书籍列表</h1>
		<a href="../book/bookinput">新增</a>
		<table>
			<tr>
				<th>书名</th>
				<th>描述</th>
				<th>价格</th>
				<th>作者</th>
				<th>操作</th>
			</tr>
			<c:forEach var="book" items="${books}" varStatus="status">
			<tr>
				<td>${book.name}</td>
				<td>${book.description}</td>
				<td>${book.price}</td>
				<td>${book.author.name}</td>
				<td><a href="../book/bookedit/${book.id}">编辑</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
