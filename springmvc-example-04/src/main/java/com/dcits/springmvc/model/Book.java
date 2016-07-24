package com.dcits.springmvc.model;

import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 8073469383839803315L;
	private String name;
	private Double price;
	private String description;
	private Author author;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", description="
				+ description + ", author=" + author + "]";
	}
}
