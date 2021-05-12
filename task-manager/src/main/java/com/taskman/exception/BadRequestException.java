package com.taskman.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -6052968240792581728L;

//	public BadRequestException() {
//		super();
//	}

//	public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
//
//	public BadRequestException(String message, Throwable cause) {
//		super(message, cause);
//	}

	public BadRequestException(String message) {
		super(message);
	}

//	public BadRequestException(Throwable cause) {
//		super(cause);
//	}
	
}
