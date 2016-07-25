##基于xml配置的Spring MVC
---
####在web.xml配置DispacherServlet，映射路径、spring mvc配置文件加载路径
```
<servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
	    <param-name>contextConfigLocation</param-name>
	    <param-value>classpath*:spring/spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```
####配置spring-mvc.xml配置文件
  - 配置包扫描路径并启用注解
```
  <!-- control所在的包路径  -->
  <context:component-scan base-package="com.dcits.springmvc.controller" />
  <!-- 启用注解 -->
  <mvc:annotation-driven enable-matrix-variables="true"/> 
```
  - 视图解析器
```
  <!-- 视图解析器 -->
  <bean
  	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  	<property name="prefix" value="/WEB-INF/" />
  	<property name="suffix" value=".jsp" />
  </bean>
```
  - 对静态资源文件的处理
```
  <!-- 容器默认的DefaultServletHandler处理 所有静态内容与无RequestMapping处理的URL -->
  <mvc:default-servlet-handler />
  <mvc:resources location="/" mapping="/**/*.js" />
  <mvc:resources location="/" mapping="/**/*.css" />
  <mvc:resources location="/" mapping="/**/*.png" />
  <mvc:resources location="/" mapping="/**/*.gif" />
```
####开发自己的controller
```
  @Controller
  public class HelloWorldController {
  	//http://localhost:8080/springmvc-example-01/helloworld
  	@RequestMapping(value="helloworld")
  	public String show(Model model) {
  		//业务逻辑层返回数据模型
  		model.addAttribute("world", "World");
  		return "index";
  	}
  }
```
####视图 index.jsp
```
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Spring MVC Demo</title>
  </head>
  <body>
	  Hello ${world} <br>
  </body>
</html>
```
访问地址:http://localhost:8080/springmvc-example-01/helloworld
