package com.dcits.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/***
 * ʹ��Java��ʽ����Spring MVC
 * ��Servlet3.0�淶����������ʱ������·������ʵ����javax.servletContainerInitializer�ӿڵ�������ҵ���ʹ��������Servlet����
 * Spring���ṩ��servletContainerInitializer�ӿڵ�ʵ����SpringServletContainerInitializer
 * �������ֻᷴ��������ʵ��WebApplicationInitializer�ӿڵ��࣬AbstractAnnotationConfigDispatcherServletInitializerʵ����WebApplicationInitializer�ӿ�
 * ����������÷�ʽ��Ҫweb����֧��Servlet3.0�淶
 * @author renliangd
 *
 */
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	
	/***
	 * ����SpringӦ��������
	 * ������ָContextLoadListenerӦ�������ĵ�������
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}
	
	/***
	 * ����Dispacher ServletӦ��������
	 * ������ָDispacherServlet����Ӧ�������ĵ�������
	 * Ĭ�����DispacherServlet����ʱ�ᴴ��Spring��Ӧ�������ģ�
	 * ��������ϣ��DispacherServlet���ذ���Web�����bean���������ͼ��������������ӳ�䣬
	 * ��ContextLoadListener����Ӧ�õ�����bean
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}
	
	/***
	 * ����ServletMapping
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
