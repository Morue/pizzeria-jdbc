package com.pizzeria.exception;

/*
 * Credit Mélanie Diligent
 */

public class UpdatePizzaException extends StockageException {

	private static final long serialVersionUID = 1L;

	public UpdatePizzaException (String msg) {
		
		super(msg);
	}
}