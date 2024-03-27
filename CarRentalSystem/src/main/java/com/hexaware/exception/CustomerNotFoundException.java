package com.hexaware.exception;
/**
 * User defined Exception 
 * Is thrown when a record in Customer database is not found.
 */
@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException(String msg)
	{
		super(msg);
	}
}
