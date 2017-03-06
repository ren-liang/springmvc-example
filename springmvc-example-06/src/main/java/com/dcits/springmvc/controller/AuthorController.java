package com.dcits.springmvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dcits.springmvc.model.Author;
import com.dcits.springmvc.service.IAuthorService;

@RestController
@RequestMapping("authos")
@Api(tags = "authos",description = "作者")
public class AuthorController {
	private static final Log log = LogFactory.getLog(AuthorController.class);
	@Resource(name="authorService")
	private IAuthorService authorService;
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取某个指定作者的信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public Author get(@PathVariable Integer id){
		Author author = authorService.getAuthorById(id);
		log.info("authorinfo");
		return author;
	}
	
	/***
	 * 查询所有
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "作者列表")
	@RequestMapping(method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public List<Author> list(){
		List<Author> authors = authorService.getAllAuthors();
		log.info("authorlist");
		return authors;
	}
}
