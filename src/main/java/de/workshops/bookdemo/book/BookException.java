package de.workshops.bookdemo.book;

import lombok.Getter;

@Getter
public class BookException extends Exception {

	private String errorCode;
	
	public BookException() {
		super();
	}

	public BookException(String errorCode) {
		this.errorCode = errorCode;
	}
}
