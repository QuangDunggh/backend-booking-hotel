package com.rim.myproject.exception;

public class NotFoundResourceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4357448107867459952L;

	public NotFoundResourceException(String message) {
		super(message);
	}

}
