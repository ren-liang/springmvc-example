package com.dcits.springmvc.base.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtil {
	public static Map<String, String> getBaseExceptionInfo(Throwable e) {
		Throwable ee = getBaseException(e);

		Map<String, String> exceptionInfo = getInfo(ee);

		StringBuffer causeMessage = new StringBuffer();
		int length = e.getCause().getStackTrace().length;
		for (int i = 0; i < length; i++) {
			String message = e.getCause().getStackTrace()[i].toString();
			causeMessage.append(message).append("\n");
			if (message.contains("digitalchina")) {
				break;
			}
		}
		exceptionInfo.put("causeMessage", causeMessage.toString());
		return exceptionInfo;
	}

	public static Map<String, String> getThrowableInfo(Throwable e) {
		Throwable ee = getThrowable(e);

		return getInfo(ee);
	}

	public static Throwable getThrowable(Throwable e) {
		Throwable exceptionObj = null;
		Throwable ee = e;
		while (ee != null) {
			exceptionObj = ee;
			ee = ee.getCause();
		}
		return exceptionObj;
	}

	public static Throwable getBaseException(Throwable e) {
		Throwable exceptionObj = null;
		Throwable ee = e;
		while (ee != null) {
			exceptionObj = ee;
			if ((exceptionObj instanceof BaseException)) {
				return (BaseException) exceptionObj;
			}
			ee = ee.getCause();
		}
		return exceptionObj;
	}

	private static Map<String, String> getInfo(Throwable e) {
		String exType = e.getClass().toString();
		String rtnCode = null;
		String rtnMessage = null;

		if ((e instanceof BaseException)) {
			BaseException exception = (BaseException) e;
			rtnCode = exception.getErrorCode();
			rtnMessage = exception.getErrorMessage();
			if ((rtnMessage == null) || ("".equals(rtnMessage))) {
				rtnMessage = e.getCause().getMessage();
			}
		} else if ((e instanceof Exception)) {
			Exception exception = (Exception) e;
			rtnCode = "1000";
			rtnMessage = exception.getMessage();
		} else {
			rtnCode = "1000";
			rtnMessage = e.getMessage();
		}
		if ((rtnCode == null) || ("".equals(rtnCode))) {
			rtnCode = "2005";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("rtnMessage", rtnMessage);
		map.put("rtnCode", rtnCode);
		map.put("exceptionType", exType);
		return map;
	}
}