package com.hexaware.exception;
/**
 * User defined Exception 
 * Is thrown when the entered Email is of invalid format.
 */
@SuppressWarnings("serial")
public class InvalidEmailFormatException extends Exception {
	public InvalidEmailFormatException(String msg)
	{
		super(msg);
	}
}
