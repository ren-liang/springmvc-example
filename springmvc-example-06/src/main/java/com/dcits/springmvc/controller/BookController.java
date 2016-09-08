package com.dcits.springmvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcits.springmvc.model.Author;
import com.dcits.springmvc.model.Book;
import com.dcits.springmvc.service.IAuthorService;
import com.dcits.springmvc.service.IBookService;
import com.dcits.springmvc.validation.BookValidator;
/***
 * Book 控制器
 * @author renliangd
 *
 */
@Controller
@RequestMapping("book")
public class BookController {
	private static final Log log = LogFactory.getLog(BookController.class);
	@Resource(name="bookService")
	private IBookService bookService;
	@Resource(name="authorService")
	private IAuthorService authorService;
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping(value = "/bookinput", method = RequestMethod.GET)
	public String inputBook(Model model) {
		List<Author> authors = authorService.getAllAuthors();
		model.addAttribute("authors", authors);
		model.addAttribute("book", new Book());
		log.info("bookinput");
		return "book/bookaddform";
	}
	/**
	 * 编辑
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/bookedit/{id}", method = RequestMethod.GET)
	public String editBook(Model model,@PathVariable Integer id){
		List<Author> authors = authorService.getAllAuthors();
		Book book = bookService.getBookById(id);
		model.addAttribute("authors", authors);
		model.addAttribute("book", book);
		log.info("bookedit");
		return "book/bookeditform";
	}
	/***
	 * 保存
	 * @param book
	 * @return
	 */
	@RequestMapping(value = "/booksave", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book,Model model,BindingResult bindingResult){
		BookValidator bookValidator = new BookValidator();
		bookValidator.validate(book, bindingResult);
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			log.info(fieldError.getCode());
			List<Author> authors = authorService.getAllAuthors();
			model.addAttribute("authors", authors);
			model.addAttribute("book", book);
			return "book/bookaddform";
		}
		Author author = authorService.getAuthorById(book.getAuthor().getId());
		book.setAuthor(author);
		bookService.save(book);
		log.info("保存成功！");
		return "redirect:/book/booklist";
	}
	/***
	 * 更新
	 * @param book
	 * @return
	 */
	@RequestMapping(value = "/bookupdate", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book book){
		Author author = authorService.getAuthorById(book.getAuthor().getId());
		book.setAuthor(author);
		bookService.update(book);
		log.info("bookupdate");
		return "redirect:/book/booklist";
	}
	/***
	 * 查询所有
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String listBook(Model model){
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		log.info("booklist");
		return "book/booklist";
	}
}
