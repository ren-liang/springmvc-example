package com.dcits.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/***
 * 使用Java方式配置Spring MVC
 * 在Servlet3.0规范中容器启动时会在类路径查找实现了javax.servletContainerInitializer接口的类如果找到则使用它配置Servlet容器
 * Spring中提供了servletContainerInitializer接口的实现类SpringServletContainerInitializer
 * 而该类又会反过来需找实现WebApplicationInitializer接口的类，AbstractAnnotationConfigDispatcherServletInitializer实现了WebApplicationInitializer接口
 * 因此这中配置方式需要web容器支持Servlet3.0规范
 * @author renliangd
 *
 */
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	
	/***
	 * 配置Spring应用上下文
	 * 这里是指ContextLoadListener应用上下文的配置类
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}
	
	/***
	 * 配置Dispacher Servlet应用上下文
	 * 这里是指DispacherServlet加载应用上下文的配置类
	 * 默认情况DispacherServlet启动时会创建Spring的应用上下文，
	 * 但是我们希望DispacherServlet加载包含Web组件的bean如控制器视图解析器及处理器映射，
	 * 而ContextLoadListener加载应用的其他bean
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}
	
	/***
	 * 定义ServletMapping
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
