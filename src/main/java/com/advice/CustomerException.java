package com.advice;

import javax.servlet.ServletException;

/**
 * @Name  :CustomerException
 * @Desc  :TODO
 * @author:zhu
 * @date  :2016年8月11日
 */
public class CustomerException extends ServletException{

	private static final long serialVersionUID = -7531435474168105272L;

	public CustomerException(){
		
	}
	
	public CustomerException(String message){
		super(message);
	}
	
	public CustomerException(Throwable cause) {
		super(cause);
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}
}
