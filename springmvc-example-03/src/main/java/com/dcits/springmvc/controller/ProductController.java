package com.dcits.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dcits.springmvc.model.Product;
/***
 * 产品控制器
 * Spring mvc配置文件配置注解扫描包路径，并开启注解
 * 使用@Controller声明该类是一个Controller
 * 使用@RequestMapping指定可以处理哪些 URL请求以及对应的http请求方式,
 * 作用在类级别则表示该类的每个方法的url前缀
 * @author renliangd
 *
 */

@Controller
@RequestMapping(value="product")
public class ProductController {
	private static final Log log = LogFactory.getLog(ProductController.class);
	
	/***
	 * 将 http://localhost:8080/springmvc-example-03/product/inputproduct的url绑定到此方法
	 * 注解@RequestMapping的value用于指定绑定的url，method属性是一个数组类型用于指定http请求类型可以为空则默认处理所有类型,如果只有一个值可以不用大括号
	 * @return 返回视图的逻辑路径
	 */
	//http://localhost:8080/springmvc-example03/product/inputproduct
	@RequestMapping(value = "/inputproduct", method = { RequestMethod.GET, RequestMethod.POST })
	public String inputProduct() {
		log.info("inputproduct被调用了……");
		return "product/productform";
	}
	
	/***
	 * 保存产品信息
	 * @param product form表单提交之后Spring MVC会根据form表单中的数据根据name属性自动进行数据的封装实现数据绑定
	 * 关于请求处理方法：
	 * 		（1）参数类型 
	 * 		   ServletRequest、HttpServletRequest、ServletResponse、HttpSession等Servlet API类型，
	 * 		         同时允许命令或自定义的表单对象，Model、Map、java IO等类型
	 * 		（2）返回值类型
	 * 		   ModelAndView、Model、Map、View、String、void等
	 * @return String类型的逻辑视图的路径
	 */
	//http://localhost:8080/springmvc-example-03/product/saveproduct
	@RequestMapping(value = "saveproduct", method = RequestMethod.POST)
	public String saveProduct(Product product, Model model) {
		log.info("inputproduct被调用了……");
		log.info("接收到的product的值：" + product.toString());
		/*
		 * 调用Service层保存product
		 */
		//Spring MVC 会自动初始化model对象,可以通过model对象向视图添加展示所需的数据
		model.addAttribute("product", product);
		return "product/productdetails";
	}
	
	/***
	 * 保存产品后重定向
	 * 必要的时候使用重定向
	 * 	用户表单提交之后如果用户重新加载页面会出现表单重复提交的现象，避免表单重复提交的实现方式很多，
	 *  在浏览器端我们可以通过js控制表单重复提交，在服务器端可以通过进入表单页面的服务器端生成token表单提交时通过判断token是否有效避免重复提交
	 *  也可以使用表单提交之后重定向实现
	 * @param product
	 * @param model
	 * @return
	 */
	//http://localhost:8080/springmvc-example-03/product/newsaveproduct
	@RequestMapping(value = "newsaveproduct", method = RequestMethod.POST)
	public String saveProductAndRedirect(Product product,RedirectAttributes redirectAttributes) {
		log.info("saveproduct被调用了……");
		log.info("接收到的product的值：" + product.toString());
		/*
		 * 调用Service层保存product
		 */
		//ID是主键
		product.setId(1);
		
		//默认重定向无法轻松的传递参数给目标页面Spring提供了flash属性通过该属性可以方便的给重定向的目标页面传参
		String msg = "保存产品信息成功";
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:product/viewproduct/" + product.getId()+"?param1=123";
	}
	
	/***
	 * 根据ID获取产品信息
	 * 注解@PathVariable
	 * 		获取基于路径的请求参数变量，在请求路径中使用{}限定路径参数，使用@PathVariable将路径参数绑定到方法参数
	 * 
	 * 注解@RequestParam用于从查询字符串中取值
	 *      类似于 String param = request.getParameter("param");
	 *      如果查询参数名和方法名相同接没有必要使用@RequestParam注解 Spring mvc会自动绑定
	 * 
	 * 注解@ModelAttribute可以作用于方法级别或者方法参数上
	 * 		如果作用于方法上Spring MVC在调用目标处理方法前，会先逐个调用在方法级上标注了@ModelAttribute 的方法
	 * 		如果作用于方法参数用于从flash属性或者从model中获取值
	 * 关于往前端传值：可以在方法中定义Model、Map往model、Map中存值或者方法返回ModelAndView；
	 * @param id 产品ID
	 * @param model 模型
	 * @return url
	 */
	//http://localhost:8080/springmvc-example-03/product/viewproduct/1
	@RequestMapping(value = "viewproduct/{id}",method = RequestMethod.GET)
	public String viewProduct(@PathVariable("id") long id,
			@RequestParam(value="param1",required=false,defaultValue="456") String param,
			@ModelAttribute(value = "msg") String msg, Model model) {
		log.info("viewproduct被调用了……");
		log.info("路径变量ID的值：" + id);
		log.info("通过flash属性传递的值：" + msg);
		/**
		 * 调用Service层根据主键ID查找
		 */
		Product product = new Product();
		product.setId(1L);
		product.setName("Spring MVC开发指南");
		product.setDescription("讲述Spring MVC开发技术");
		product.setPrice(23.00);
		model.addAttribute("product", product);
		return "product/productdetails";
	}
	
	/***
	 * 注解@ModelAttribute方法级别注解
	 * 	（1）作用于void的方法每一个请求处理前都会被调用，方法中添加到model会被添加到目标方法的model中
	 * 	（2）作用于有返回值的方法时，每一个请求处理前都会被调用,会隐含的将返回值添加到目标的model中，
	 * 	            如果ModelAttribute的value属性未指定，其key是所添加对象类行的类型隐含表示 例如返回一个Product对象则，对应的key为product
	 *      如果指定了ModelAttribute的value值则添加到model中的值的key为指定的value值
	 *  （3）与@RequestMapping同时注解在方法级别
	 *      这时这个方法的返回值并不是表示一个视图名称，而是model属性的值，视图名称由RequestToViewNameTranslator根据请求url转换为逻辑视图名称。
	 * @param abc
	 * @param model
	 */
    @ModelAttribute  
    public void populateModel(@RequestParam(value="abc",defaultValue="abc",required=false) String abc, Model model) {  
       model.addAttribute("abc", abc);  
    }
    
    /***
     * 注解@RequestMapping和@ModelAttribute同时作用于方法级别
     * 此时返回值不再是逻辑视图的名称而是model中的值而@ModelAttribute用于声明model中值的key
     * 视图由
     * 注解@ModelAttribute("attributeName")指定model中数据的key
     * @rerutn 返回model中数据的值RequestToViewNameTranslator根据请求"/index"转换成对应的逻辑视图"index"
     */
    //http://localhost:8080/springmvc-example-03/product/index
    @RequestMapping(value = "/index")  
    @ModelAttribute("attributeName")
    public String index(){
    	log.info("进入了index……");
    	return "attributeValue";
    }
}
