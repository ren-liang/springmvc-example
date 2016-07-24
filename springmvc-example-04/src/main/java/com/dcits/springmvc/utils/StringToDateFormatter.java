package com.dcits.springmvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class StringToDateFormatter implements Formatter<Date> {
	private SimpleDateFormat dateFormat;
	/**
	 * ������ ����SimpleDateFormat
	 * @param datePattenrn ���ڸ�ʽ��������ַ���
	 */
	public StringToDateFormatter(String datePattenrn) {
		this.dateFormat = new SimpleDateFormat(datePattenrn);
	}

	/***
	 * ������ת��Ϊ�ַ���
	 */
	@Override
	public String print(Date date, Locale locale) {
		return "";
	}
	
	/***
	 * ���ַ�����ʽ��Ϊָ������
	 */
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		return dateFormat.parse(text);
	}

}
