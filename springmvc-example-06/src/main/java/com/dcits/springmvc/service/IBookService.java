package com.dcits.springmvc.service;

import java.util.List;

import com.dcits.springmvc.model.Book;

public interface IBookService {

	void save(Book book);

	Book getBookById(Integer id);

	void update(Book book);

	List<Book> getAllBooks();

	void deleteById(Integer id);

}
