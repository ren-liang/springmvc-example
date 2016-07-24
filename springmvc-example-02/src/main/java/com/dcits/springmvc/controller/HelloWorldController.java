package com.dcits.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/****
 * Controller
 * @author renliangd
 *
 */
@Controller
public class HelloWorldController {
	//http://localhost:8080/springmvc-example-02/helloworld
	@RequestMapping(value="helloworld")
	public String show(Model model) {
		//业务逻辑层返回数据模型
		model.addAttribute("world", "World");
		return "index";
	}
}
