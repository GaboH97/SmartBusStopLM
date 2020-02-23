package com.smartbusstopbackend.app.exceptions;

public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("El usuario no fue encontrado");
	}
	
}
