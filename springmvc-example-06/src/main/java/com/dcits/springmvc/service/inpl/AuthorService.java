package com.dcits.springmvc.service.inpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcits.springmvc.dao.IAuthorDao;
import com.dcits.springmvc.model.Author;
import com.dcits.springmvc.service.IAuthorService;
@Service
public class AuthorService implements IAuthorService {
	@Autowired
	private IAuthorDao authorDao;
	
	@Override
	public List<Author> getAllAuthors() {
		return authorDao.selectByProperties(new Author());
	}

	@Override
	public Author getAuthorById(Integer id) {
		return authorDao.selectByPrimaryKey(id);
	}

}
