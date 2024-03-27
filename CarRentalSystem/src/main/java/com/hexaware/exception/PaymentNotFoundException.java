package com.hexaware.exception;
/**
 * User defined Exception 
 * Is thrown when a record in Payment database is not found.
 */
@SuppressWarnings("serial")
public class PaymentNotFoundException extends Exception{
	public PaymentNotFoundException(String msg)
	{
		super(msg);
	}
}
