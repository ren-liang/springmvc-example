package com.dcits.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcits.springmvc.form.BookListForm;
import com.dcits.springmvc.form.BookMapForm;
import com.dcits.springmvc.form.BookSetForm;
import com.dcits.springmvc.model.Author;
import com.dcits.springmvc.model.Book;
import com.dcits.springmvc.model.Product;

/***
 * 数据绑定
 * @author renliangd
 *
 */
@Controller
public class DataBindingController {
	/***
	 * 基本类型的数据绑定
	 * 通过查询字符串传参
	 * 注意：
	 * 	  如果参数为空会报500错误，解决方法
	 *   （1）使用@RequestParam注解将required设置为false并指定defaultValue的值
	 *       例如：@RequestParam(value="age",required=false,defaultValue="100")
	 *   （2）将基本类型定义为包装类型
	 * 注解@ResponseBody的作用是通过适当的HttpMessageConverter转换为指定格式后，
	 *    写入到Response对象的body数据区，一般用于返回xml或json时使用
	 * @param price
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/basetype?price=10
	@RequestMapping("basetype")
	@ResponseBody
	public String baseType(double price) {
		return "price:" + price;
	}
	/***
	 * 将基本类型定义为包装类型避免参数为空时报错
	 * @param price
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/wrappertype
	@RequestMapping("wrappertype")
	@ResponseBody
	public String wrapperType(Double price) {
		return "price:" + price;
	}
	
	/***
	 * 数组类型
	 * @param array
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/array?array=a&array=b
	@RequestMapping("array")
	@ResponseBody
	public String array(String [] array) {
		StringBuffer str = new StringBuffer();
		if(array!=null){
			for(String item:array){
				str.append(item + " " );
			}
		}
		return "array:" + str;
	}
	/***
	 * 自定义简单对象类型
	 * @param array
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/book?name=test&price=10&description=miaoshu&author.name=renliang&author.age=18
	@RequestMapping("book")
	@ResponseBody
	public String book(Book book) {
		return "book:" + book;
	}
	
	
	/***
	 * 同属性不同对象绑定
	 * book和product均有name等相同属性
	 * 请求http://localhost:8080/springmvc-example-04/bookandauthor?name=test
	 * 返回：
	 * 		book:Book [name=test, price=null, description=null, author=null]
	 * 		product:Product [name=test, age=null,description=null]
	 * 同时会给两个对象赋值 此时需要使用@InitBinder进行方法注解设置参数的前缀
	 * @param array
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/bookandproduct?book.name=book1&product.name=product1
	@RequestMapping("bookandproduct")
	@ResponseBody
	public String bookAndProduct(Book book,Product product) {
		return "book:" + book + "\n" + "product:" + product;
	}
	@InitBinder("book")
	public void initBook(WebDataBinder dataBinder){
		dataBinder.setFieldDefaultPrefix("book.");
	}
	@InitBinder("product")
	public void initProduct(WebDataBinder dataBinder){
		dataBinder.setFieldDefaultPrefix("product.");
	}
	
	
	/***
	 * List绑定
	 * 绑定List时方法参数不能直接定义成List需要定义一个含有List的包装类
	 * @param bookListForm
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/list?books[0].name=java&books[0].price=10&books[5].name=python&books[5].price=20
	/**
	 * BookListForm [books=[
	 * 						Book [name=java, price=10.0, description=null, author=null], 
	 * 						Book [name=null, price=null, description=null, author=null], 
	 * 						Book [name=null, price=null, description=null, author=null], 
	 * 						Book [name=null, price=null, description=null, author=null], 
	 * 						Book [name=null, price=null, description=null, author=null], 
	 * 						Book [name=python, price=20.0, description=null, author=null]
	 * 						]
	 * 				]
	 */
	@RequestMapping("list")
	@ResponseBody
	public String list(BookListForm bookListForm){
		return "list:"+bookListForm;
	}
	/***
	 * 绑定Set
	 * Spring MVC对于Set的绑定与List的类似需要定义包装类
	 * 注意：如果包装类中的Set的size为0或者Set的size小于绑定数据的size会抛错，所以Sping MVC对于Set的支持并不良好
	 * @param bookSetForm
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/set?books[0].name=java&books[0].price=10&books[1].name=python&books[1].price=20
	@RequestMapping("set")
	@ResponseBody
	public String set(BookSetForm bookSetForm){
		return "set:" + bookSetForm;
	}
	/***
	 * 绑定Map
	 * Spring MVC对于Map的绑定需要定义包装类
	 * @param bookSetForm
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/map?books['a'].name=java&books['a'].price=10&books['b'].name=python&books['b'].price=20
	/***
	 * BookMapForm [books={a=Book [name=java, price=10.0, description=null, author=null], 
	 * 					   b=Book [name=python, price=20.0, description=null, author=null]
	 * 					  }
	 * 				]
	 */
	@RequestMapping("map")
	@ResponseBody
	public String map(BookMapForm BookMapForm) {
		return "map:" + BookMapForm;
	}
	
	/****
	 * 绑定json
	 * 注解@RequestBody会将Request body中的数据反序列化成对象
	 * 绑定json必须引入jackson-mapper-asl.jar否则程序不会 保存，但是请前端发起请求之后返回415
	 * 注解@RequestMapping 
	 * 		属性consumes用于指定请求类型，
	 * 		属性produces用于指定返回类型,
	 * 		配合@ResponseBody注解实现向前端返回json或者xml
	 * @param book
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/json
	//	{
	//		"name": "java",
	//		"price": "12.3",
	//		"description": "java dev",
	//		"author": {
	//			"name": "caillc",
	//			"age": "18"
	//		}
	//	}
	@RequestMapping(value = "json", method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book json(@RequestBody Book book) {
		return book;
	}

	/****
	 * 绑定xml 绑定xml必须引入spring-oxm.jar 
	 * 绑定Xml的类需要使用JAXB注解API进行注解
	 * @param author
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/xml
	//	<?xml version="1.0" encoding="UTF-8"?>
	//	<author>
	//		<name>caillc</name>
	//		<age>18</age>
	//	</author>
	@RequestMapping(value = "xml", method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_XML_VALUE, 
					produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public Author xml(@RequestBody Author author) {
		return author;
	}
		
	/***
	 * 绑定日期类型
	 * 使用自定义的格式化器或者转化器
	 * @param user
	 * @return
	 */
	//http://localhost:8080/springmvc-example-04/date?date=2016-11-20
	@RequestMapping(value = "date")
	@ResponseBody
	public String date(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "日期：" + sdf.format(date);
	}
}
