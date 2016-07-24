package com.dcits.springmvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String,Date>{
	private SimpleDateFormat dateFormat;
	
	public StringToDateConverter(String datePattenrn) {
		this.dateFormat = new SimpleDateFormat(datePattenrn);
	}

	/***
	 * ʵ��convert�������ַ���ת��ΪUser����
	 */
	@Override
	public Date convert(String source) {
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
