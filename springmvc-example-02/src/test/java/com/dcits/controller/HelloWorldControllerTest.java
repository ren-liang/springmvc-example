package com.dcits.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dcits.springmvc.controller.HelloWorldController;
/***
 * ʹ��MockMvc���в���
 * @author renliang
 *
 */
public class HelloWorldControllerTest {
	/***
	 * 1��mockMvc.performִ��һ������
	 * 2��MockMvcRequestBuilders.get("/helloworld")����һ������
	 * 3��ResultActions.andExpect���ִ����ɺ�Ķ���
	 * @throws Exception
	 */
	@Test
	public void testHellWorld() throws Exception {
		HelloWorldController controller = new HelloWorldController();
		//ִ��һ������
		MockMvc mocMvc = MockMvcBuilders.standaloneSetup(controller).build();
		ResultActions resultActions = mocMvc.perform(MockMvcRequestBuilders.get("/helloworld")).andExpect(MockMvcResultMatchers.view().name("index"));
	}

}
