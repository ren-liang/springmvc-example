package com.dcits.springmvc.config;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;

/**
 * API 文档生成配置
 * <p>
 * Title: ApiConfig
 * <p>
 * Description:
 * <p>
 * Company: DCITS
 * 
 * @author renliangd
 */

@EnableSwagger2
public class ApplicationSwaggerConfig extends WebMvcConfigurationSupport {
	@Autowired
	private TypeResolver typeResolver;


	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)/*.groupName("移动办税API")*/.select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class).apiInfo(apiInfo());
	}

	/**
	 * UI配置
	 * 
	 * @return 配置信息
	 */
	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl",// url
				"none", // docExpansion => none | list
				"alpha", // apiSorter => alpha
				"schema", // defaultModelRendering => schema
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, // enableJsonEditor
				true, // showRequestHeaders => true | false
				60000L); // requestTimeout => in milliseconds, defaults to null
							// (uses jquery xh timeout)
	}
	/**
	 * 文档信息
	 * @return 文档信息
	 */
    private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("API文档", 
				"外部接口文档", "1.0", "urn:tos", 
				ApiInfo.DEFAULT_CONTACT, "Apache 2.0", 
				"http://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
    /**
     * 联系方式
     * @return 联系方式
     */
    @SuppressWarnings("unused")
	private Contact getContact(){
    	return new Contact("renliangd","www.dcits.com","renliangd@dcits.com");    	
    }
}
