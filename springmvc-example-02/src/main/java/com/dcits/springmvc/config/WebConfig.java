package com.dcits.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/***
 * ����Dispacher ServletӦ�������ĳ�ʼ��������
 * @author renliang
 *
 */
@Configuration
//����Spring MVC
@EnableWebMvc
//���ð�ɨ��·��������ע��
@ComponentScan("com.dcits.springmvc.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/***
	 * JSP��ͼ������
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	/***
	 * ��̬��Դ�Ĵ���
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
