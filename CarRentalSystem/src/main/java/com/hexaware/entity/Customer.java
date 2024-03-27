package com.hexaware.entity;
/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @since 1.0
 * The entity class for Customer Database.
 */
/**
 * The entity class for Customer Database.
 */
public class Customer {
	private int customerID;
	private String firstName;
	private String lastName;
	public String email;
	public String phoneNumber;
	/**
	 * A non parameterized constructor.
	 */
	public Customer()
	{
		
	}
	/**
	 * A parameterized constructor.
	 */
	public Customer(int customerID, String firstName, String lastName, String email, String phoneNumber) {
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Getter Method for Customer ID.
	 * @return An integer customer ID.
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * Setter Method for Customer ID.
	 * @param customerID An integer which has customer ID.
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * Getter Method for First Name.
	 * @return First Name of the CUstomer.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter Method for First Name.
	 * @param firstName First Name of the CUstomer.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Getter Method for Last Name.
	 * @return Last Name of the CUstomer.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter Method for Last Name.
	 * @param lastName Last Name of the CUstomer.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Getter Method for Email.
	 * @return Email of the CUstomer.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter Method for Email.
	 * @param email Email of the CUstomer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter Method for Phone Number.
	 * @return Phone Number of the CUstomer.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Setter Method for Phone Number.
	 * @param phoneNumber Phone Number of the CUstomer.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * To String method of the class.
	 * @return A string of entire information about the Customer.
	 */
	@Override
	public String toString() {
		return String.format("Customer ID: %-5d | First Name: %-12s | Last Name: %-12s | Email: %-25s | Phone Number: %-12s",
				customerID,firstName,lastName,email,phoneNumber);
	}
	
}
