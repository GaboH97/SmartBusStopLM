package com.smartbusstopbackend.app.exceptions;

public class CardNotFoundException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public CardNotFoundException() {
		super("No se encontro tarjeta");
	}
}
