<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML">
<html>
  <head>
    <title>Spring MVC Demo</title>
  </head>
  <body>
	<div>
		<form:form commandName="book" action="../../book/bookupdate" method="post">
			<fieldset>
				<legend><spring:message code="legend.editbook"/></legend>
				<form:hidden path="id"/>
				<p>
					<label for="name"><spring:message code="book.name"/>：</label>
					<form:input id="name" path="name"/>
				</p>
				<p>
					<label for="price"><spring:message code="book.price"/>：</label>
					<form:input id="price" path="price"/>
				</p>
				<p>
					<label for="author"><spring:message code="book.author"/>：</label>
					<form:select id="author" path="author.id" items="${authors}" itemLabel="name" itemValue="id"/>
				</p>
				<p>
					<label for="description"><spring:message code="book.description"/>：</label>
					<form:textarea id="description" path="description"/>
				</p>
				<p id="buttons">
					<input id="reset" type="reset" value="<spring:message code="button.reset"/>">
					<input id="submit" type="submit" value="<spring:message code="button.submit.update"/>">
				</p>
			</fieldset>
		</form:form>	
	</div>
	<br>
  </body>
</html>
