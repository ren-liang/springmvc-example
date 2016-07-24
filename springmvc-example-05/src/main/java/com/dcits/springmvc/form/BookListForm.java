package com.dcits.springmvc.form;

import java.io.Serializable;
import java.util.List;

import com.dcits.springmvc.model.Book;

public class BookListForm implements Serializable {
	private static final long serialVersionUID = -7538661827735472110L;
	private List<Book> books;
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	@Override
	public String toString() {
		return "BookListForm [books=" + books + "]";
	}
}
