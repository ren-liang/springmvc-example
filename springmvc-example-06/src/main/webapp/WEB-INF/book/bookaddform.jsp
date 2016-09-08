<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>Spring MVC Demo</title>
<link rel="stylesheet" type="text/css" href="../css/main.css"/>
</head>
<body>
	<div>
	用户当前Local:${pageContext.response.locale}<br>
	accept-language header:${header["accept-language"]}
		<form:form commandName="book" action="../book/booksave" method="post">
			<fieldset>
				<legend><spring:message code="legend.addbook"/></legend>
				<!-- 接手数据校验结果并显示 delimiter表示多个错误之间的分隔符-->
				<%-- <form:errors path="*" delimiter=">"/> --%>
				<p>
					<label for="name"><spring:message code="book.name"/></label>
					<form:input id="name" path="name" cssErrorClass="input_error"/>
					<form:errors path="name" cssClass="error_span"/>
				</p>
				<p>
					<label for="price"><spring:message code="book.price"/>：</label>
					<form:input id="price" path="price" cssErrorClass="input_error"/>
					<form:errors path="price" cssClass="error_span" />
				</p>
				<p>
					<label for="author"><spring:message code="book.author"/>：</label>
					<form:select id="author" path="author.id" items="${authors}" itemLabel="name" itemValue="id" cssErrorClass="input_error"/>
					<form:errors path="author.id"/>
				</p>
				<p>
					<label for="description"><spring:message code="book.description"/>：</label>
					<form:textarea id="description" path="description" cssErrorClass="input_error"/>
					<form:errors path="description" cssClass="error_span"/>
				</p>
				<p id="buttons">
					<input id="reset" type="reset" value="<spring:message code="button.reset"/>">
					<input id="submit" type="submit" value="<spring:message code="button.submit.save"/>">					
				</p>
			</fieldset>
		</form:form>	
	</div>
	<br>
</body>
</html>
