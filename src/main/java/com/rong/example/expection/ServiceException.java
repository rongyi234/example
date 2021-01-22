package com.rong.example.expection;


/**
 *
 */
public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = -4809687650133434131L;

	private String code;
	private Object data;

	public String getCode() {
		return this.code;
	}

	public Object getData() {
		return this.data;
	}

	public ServiceException() {
	}

	public ServiceException(Throwable e) {
		super(e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(Integer code, String message) {
		super(message);
		this.code = String.valueOf(code);
	}

	public ServiceException(String code, String message, Object data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}


}
