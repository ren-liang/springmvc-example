package com.dcits.springmvc.form;

import java.util.Map;

import com.dcits.springmvc.model.Book;

public class BookMapForm {
	private Map<String, Book> books;

	public Map<String, Book> getBooks() {
		return books;
	}

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "BookMapForm [books=" + books + "]";
	}

}
