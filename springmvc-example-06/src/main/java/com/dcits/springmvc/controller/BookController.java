package com.dcits.springmvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dcits.springmvc.model.Author;
import com.dcits.springmvc.model.Book;
import com.dcits.springmvc.service.IAuthorService;
import com.dcits.springmvc.service.IBookService;
import com.dcits.springmvc.validation.BookValidator;
/***
 * Book ������
 * @author renliangd
 *
 */
@RestController
@RequestMapping("books")
@Api(tags = "books",description = "�鼮")
public class BookController {
	private static final Log log = LogFactory.getLog(BookController.class);
	@Resource(name="bookService")
	private IBookService bookService;
	@Resource(name="authorService")
	private IAuthorService authorService;
	
	/***
	 * ����
	 * @param book
	 * @return
	 */
	@ApiOperation(value = "�����鼮")
	@RequestMapping(method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public Map<String,String> add(@RequestBody Book book,Model model,BindingResult bindingResult){
		Map<String,String> res = new HashMap<String, String>();
		BookValidator bookValidator = new BookValidator();
		bookValidator.validate(book, bindingResult);
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			log.info(fieldError.getCode());
			res.put("msg",fieldError.getDefaultMessage());
			return res;
		}
		Author author = authorService.getAuthorById(book.getAuthor().getId());
		book.setAuthor(author);
		bookService.save(book);
		log.info("����ɹ���");
		res.put("msg", "����ɹ���");
		return res;
	}
	
	/**
	 * ɾ��ĳ��ָ���鼮����Ϣ
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "ɾ��ĳ��ָ���鼮����Ϣ")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, String> delete(@PathVariable Integer id){
		Map<String,String> res = new HashMap<String, String>();
		bookService.deleteById(id);
		log.info("bookdelete");
		res.put("msg", "ɾ���ɹ���");
		return res;
	}
		
	/***
	 * ����
	 * @param book
	 * @return
	 */
	@ApiOperation(value = "�����鼮")
	@RequestMapping(method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
	public Book update(@RequestBody Book book){
		Author author = authorService.getAuthorById(book.getAuthor().getId());
		book.setAuthor(author);
		bookService.update(book);
		log.info("bookupdate");
		return book;
	}
	
	/**
	 * ����ID��ѯ
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "��ȡĳ��ָ���鼮����Ϣ")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public Book get(@PathVariable Integer id){
		Book book = bookService.getBookById(id);
		log.info("bookinfo");
		return book;
	}
	
	/***
	 * ��ѯ����
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "�鼮�б�")
	@RequestMapping(method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public List<Book> list(){
		List<Book> books = bookService.getAllBooks();
		log.info("booklist");
		return books;
	}
}
