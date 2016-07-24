package com.dcits.springmvc.model;

import java.io.Serializable;
/***
 * 产品实体类
 * @author renliangd
 *
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1512283108521506769L;
	//主键ID
	private long id;
	//名称
	private String name;
	//价格
	private double price;
	//描述
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", description="
				+ description + "]";
	}
}
