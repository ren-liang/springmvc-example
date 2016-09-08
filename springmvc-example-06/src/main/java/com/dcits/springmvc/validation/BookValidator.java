package com.dcits.springmvc.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dcits.springmvc.model.Author;
import com.dcits.springmvc.model.Book;
/***
 * Book表单数据校验
 * @author renliangd
 *
 */
public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book)target;
		ValidationUtils.rejectIfEmpty(errors, "name", "bookname.required");
		ValidationUtils.rejectIfEmpty(errors, "price", "bookprice.required");
		Double price = book.getPrice();
		if (price != null && price < 0) {
			errors.rejectValue("price", "bookprice.negatice");
		}
		Author author = book.getAuthor();
		if (author == null || author.getId() == null || author.getId() < 0) {
			errors.rejectValue("author.id", "authorid.invalid");
		}
	}

}
