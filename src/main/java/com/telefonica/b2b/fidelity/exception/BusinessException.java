package com.telefonica.b2b.fidelity.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 874387341803198776L;

    public BusinessException(String message, Throwable throwable) {
	super(message, throwable);
    }

    public BusinessException(String message) {
	super(message);
    }

    public BusinessException() {
	super();
    }

}
