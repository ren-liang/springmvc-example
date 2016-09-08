package com.dcits.springmvc.service;

import java.util.List;

import com.dcits.springmvc.model.Author;

public interface IAuthorService {
	List<Author> getAllAuthors();
	Author getAuthorById(Integer id);
}
