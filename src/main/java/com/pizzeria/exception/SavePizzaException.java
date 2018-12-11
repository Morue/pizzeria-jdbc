package com.pizzeria.exception;

/*
 * Credit Mélanie Diligent
 */

public class SavePizzaException extends StockageException {

	private static final long serialVersionUID = 1L;

	public SavePizzaException(String msg) {
		super(msg);
	}
}
