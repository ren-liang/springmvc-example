package com.dcits.springmvc.form;

import java.util.HashSet;
import java.util.Set;

import com.dcits.springmvc.model.Book;

public class BookSetForm {
	public BookSetForm(){
		books = new HashSet<Book>();
		books.add(new Book());
		books.add(new Book());
	}
	private Set<Book> books;

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "BookSetForm [books=" + books + "]";
	}

}
