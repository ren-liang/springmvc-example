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
 * ��Ʒ������
 * Spring mvc�����ļ�����ע��ɨ���·����������ע��
 * ʹ��@Controller����������һ��Controller
 * ʹ��@RequestMappingָ�����Դ�����Щ URL�����Լ���Ӧ��http����ʽ,
 * �������༶�����ʾ�����ÿ��������urlǰ׺
 * @author renliangd
 *
 */

@Controller
@RequestMapping(value="product")
public class ProductController {
	private static final Log log = LogFactory.getLog(ProductController.class);
	
	/***
	 * �� http://localhost:8080/springmvc-example-03/product/inputproduct��url�󶨵��˷���
	 * ע��@RequestMapping��value����ָ���󶨵�url��method������һ��������������ָ��http�������Ϳ���Ϊ����Ĭ�ϴ�����������,���ֻ��һ��ֵ���Բ��ô�����
	 * @return ������ͼ���߼�·��
	 */
	//http://localhost:8080/springmvc-example03/product/inputproduct
	@RequestMapping(value = "/inputproduct", method = { RequestMethod.GET, RequestMethod.POST })
	public String inputProduct() {
		log.info("inputproduct�������ˡ���");
		return "product/productform";
	}
	
	/***
	 * �����Ʒ��Ϣ
	 * @param product form���ύ֮��Spring MVC�����form���е����ݸ���name�����Զ��������ݵķ�װʵ�����ݰ�
	 * ��������������
	 * 		��1���������� 
	 * 		   ServletRequest��HttpServletRequest��ServletResponse��HttpSession��Servlet API���ͣ�
	 * 		         ͬʱ����������Զ���ı�����Model��Map��java IO������
	 * 		��2������ֵ����
	 * 		   ModelAndView��Model��Map��View��String��void��
	 * @return String���͵��߼���ͼ��·��
	 */
	//http://localhost:8080/springmvc-example-03/product/saveproduct
	@RequestMapping(value = "saveproduct", method = RequestMethod.POST)
	public String saveProduct(Product product, Model model) {
		log.info("inputproduct�������ˡ���");
		log.info("���յ���product��ֵ��" + product.toString());
		/*
		 * ����Service�㱣��product
		 */
		//Spring MVC ���Զ���ʼ��model����,����ͨ��model��������ͼ���չʾ���������
		model.addAttribute("product", product);
		return "product/productdetails";
	}
	
	/***
	 * �����Ʒ���ض���
	 * ��Ҫ��ʱ��ʹ���ض���
	 * 	�û����ύ֮������û����¼���ҳ�����ֱ��ظ��ύ�����󣬱�����ظ��ύ��ʵ�ַ�ʽ�ܶ࣬
	 *  ������������ǿ���ͨ��js���Ʊ��ظ��ύ���ڷ������˿���ͨ�������ҳ��ķ�����������token���ύʱͨ���ж�token�Ƿ���Ч�����ظ��ύ
	 *  Ҳ����ʹ�ñ��ύ֮���ض���ʵ��
	 * @param product
	 * @param model
	 * @return
	 */
	//http://localhost:8080/springmvc-example-03/product/newsaveproduct
	@RequestMapping(value = "newsaveproduct", method = RequestMethod.POST)
	public String saveProductAndRedirect(Product product,RedirectAttributes redirectAttributes) {
		log.info("saveproduct�������ˡ���");
		log.info("���յ���product��ֵ��" + product.toString());
		/*
		 * ����Service�㱣��product
		 */
		//ID������
		product.setId(1);
		
		//Ĭ���ض����޷����ɵĴ��ݲ�����Ŀ��ҳ��Spring�ṩ��flash����ͨ�������Կ��Է���ĸ��ض����Ŀ��ҳ�洫��
		String msg = "�����Ʒ��Ϣ�ɹ�";
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:product/viewproduct/" + product.getId()+"?param1=123";
	}
	
	/***
	 * ����ID��ȡ��Ʒ��Ϣ
	 * ע��@PathVariable
	 * 		��ȡ����·�����������������������·����ʹ��{}�޶�·��������ʹ��@PathVariable��·�������󶨵���������
	 * 
	 * ע��@RequestParam���ڴӲ�ѯ�ַ�����ȡֵ
	 *      ������ String param = request.getParameter("param");
	 *      �����ѯ�������ͷ�������ͬ��û�б�Ҫʹ��@RequestParamע�� Spring mvc���Զ���
	 * 
	 * ע��@ModelAttribute���������ڷ���������߷���������
	 * 		��������ڷ�����Spring MVC�ڵ���Ŀ�괦����ǰ��������������ڷ������ϱ�ע��@ModelAttribute �ķ���
	 * 		��������ڷ����������ڴ�flash���Ի��ߴ�model�л�ȡֵ
	 * ������ǰ�˴�ֵ�������ڷ����ж���Model��Map��model��Map�д�ֵ���߷�������ModelAndView��
	 * @param id ��ƷID
	 * @param model ģ��
	 * @return url
	 */
	//http://localhost:8080/springmvc-example-03/product/viewproduct/1
	@RequestMapping(value = "viewproduct/{id}",method = RequestMethod.GET)
	public String viewProduct(@PathVariable("id") long id,
			@RequestParam(value="param1",required=false,defaultValue="456") String param,
			@ModelAttribute(value = "msg") String msg, Model model) {
		log.info("viewproduct�������ˡ���");
		log.info("·������ID��ֵ��" + id);
		log.info("ͨ��flash���Դ��ݵ�ֵ��" + msg);
		/**
		 * ����Service���������ID����
		 */
		Product product = new Product();
		product.setId(1L);
		product.setName("Spring MVC����ָ��");
		product.setDescription("����Spring MVC��������");
		product.setPrice(23.00);
		model.addAttribute("product", product);
		return "product/productdetails";
	}
	
	/***
	 * ע��@ModelAttribute��������ע��
	 * 	��1��������void�ķ���ÿһ��������ǰ���ᱻ���ã���������ӵ�model�ᱻ��ӵ�Ŀ�귽����model��
	 * 	��2���������з���ֵ�ķ���ʱ��ÿһ��������ǰ���ᱻ����,�������Ľ�����ֵ��ӵ�Ŀ���model�У�
	 * 	            ���ModelAttribute��value����δָ������key������Ӷ������е�����������ʾ ���緵��һ��Product�����򣬶�Ӧ��keyΪproduct
	 *      ���ָ����ModelAttribute��valueֵ����ӵ�model�е�ֵ��keyΪָ����valueֵ
	 *  ��3����@RequestMappingͬʱע���ڷ�������
	 *      ��ʱ��������ķ���ֵ�����Ǳ�ʾһ����ͼ���ƣ�����model���Ե�ֵ����ͼ������RequestToViewNameTranslator��������urlת��Ϊ�߼���ͼ���ơ�
	 * @param abc
	 * @param model
	 */
    @ModelAttribute  
    public void populateModel(@RequestParam(value="abc",defaultValue="abc",required=false) String abc, Model model) {  
       model.addAttribute("abc", abc);  
    }
    
    /***
     * ע��@RequestMapping��@ModelAttributeͬʱ�����ڷ�������
     * ��ʱ����ֵ�������߼���ͼ�����ƶ���model�е�ֵ��@ModelAttribute��������model��ֵ��key
     * ��ͼ��
     * ע��@ModelAttribute("attributeName")ָ��model�����ݵ�key
     * @rerutn ����model�����ݵ�ֵRequestToViewNameTranslator��������"/index"ת���ɶ�Ӧ���߼���ͼ"index"
     */
    //http://localhost:8080/springmvc-example-03/product/index
    @RequestMapping(value = "/index")  
    @ModelAttribute("attributeName")
    public String index(){
    	log.info("������index����");
    	return "attributeValue";
    }
}
