package com.dcits.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dcits.springmvc.controller.HelloWorldController;
/***
 * 使用MockMvc进行测试
 * @author renliang
 *
 */
public class HelloWorldControllerTest {
	/***
	 * 1、mockMvc.perform执行一个请求；
	 * 2、MockMvcRequestBuilders.get("/helloworld")构造一个请求
	 * 3、ResultActions.andExpect添加执行完成后的断言
	 * @throws Exception
	 */
	@Test
	public void testHellWorld() throws Exception {
		HelloWorldController controller = new HelloWorldController();
		//执行一个请求；
		MockMvc mocMvc = MockMvcBuilders.standaloneSetup(controller).build();
		ResultActions resultActions = mocMvc.perform(MockMvcRequestBuilders.get("/helloworld")).andExpect(MockMvcResultMatchers.view().name("index"));
	}

}
