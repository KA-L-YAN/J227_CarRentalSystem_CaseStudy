package com.hexaware.exception;
/**
 * User defined Exception 
 * Is thrown when a record in Car database is not found.
 */
@SuppressWarnings("serial")
public class CarNotFoundException extends Exception{
	public CarNotFoundException(String msg)
	{
		super(msg);
	}
}
