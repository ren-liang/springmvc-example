package com.dcits.springmvc.service.inpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.springmvc.dao.IBookDao;
import com.dcits.springmvc.model.Book;
import com.dcits.springmvc.service.IBookService;
@Service
public class BookService implements IBookService {
	@Autowired
	private IBookDao bookDao;
	
	@Override
	public void save(Book book) {
		bookDao.insertSelective(book);
	}

	@Override
	public Book getBookById(Integer id) {
		return bookDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(Book book) {
		bookDao.updateByPrimaryKeySelective(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.selectByProperties(new Book());
	}
	
	@Override
	public void deleteById(Integer id) {
		bookDao.deleteByPrimaryKey(id);
	}

}
