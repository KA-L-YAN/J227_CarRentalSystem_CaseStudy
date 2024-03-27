package com.hexaware.exception;
/**
 * User defined Exception 
 * Is thrown when a record in Lease database is not found.
 */
@SuppressWarnings("serial")
public class LeaseNotFoundException extends Exception{
	public LeaseNotFoundException(String msg)
	{
		super(msg);
	}
}
