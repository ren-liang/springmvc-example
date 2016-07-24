package com.dcits.springmvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class StringToDateFormatter implements Formatter<Date> {
	private SimpleDateFormat dateFormat;
	/**
	 * 构造器 创建SimpleDateFormat
	 * @param datePattenrn 日期格式化规则的字符串
	 */
	public StringToDateFormatter(String datePattenrn) {
		this.dateFormat = new SimpleDateFormat(datePattenrn);
	}

	/***
	 * 将对象转化为字符串
	 */
	@Override
	public String print(Date date, Locale locale) {
		return "";
	}
	
	/***
	 * 将字符串格式化为指定对象
	 */
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		return dateFormat.parse(text);
	}

}
