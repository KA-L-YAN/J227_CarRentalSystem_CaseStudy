package com.hexaware.entity;
import java.time.LocalDate;
/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @since 1.0
 * The entity class for Customer Database.
 */
/**
 * The entity class for Customer Database.
 */
public class Lease {
	private int leaseID;
	private Car carID;
	private Customer customerID;
	private LocalDate startDate;
	private LocalDate endDate;
	private leaseType type;
	/**
	 * Enum Declaration
	 */
	public enum leaseType
	{
		DAILYLEASE, MONTHLYLEASE;
	}
	/**
	 * A non parameterized constructor.
	 */
	public Lease()
	{
		
	}
	/**
	 * A parameterized constructor.
	 */
	public Lease(int leaseID, Car vehicleID, Customer customerID, LocalDate startDate, LocalDate endDate,
			leaseType type) {
		super();
		this.leaseID = leaseID;
		this.carID = vehicleID;
		this.customerID = customerID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}
	/**
	 * Getter Method for Lease ID.
	 * @return An integer lease ID.
	 */
	public int getLeaseID() {
		return leaseID;
	}
	/**
	 * Setter Method for Lease ID.
	 * @param leaseID An integer which has Lease ID.
	 */
	public void setLeaseID(int leaseID) {
		this.leaseID = leaseID;
	}
	/**
	 * Getter Method for Car ID.
	 * @return A class object that consists of Car ID.
	 */
	public Car getCarID() {
		return carID;
	}
	/**
	 * Setter Method for Car ID.
	 * @param carID A class object that consists of Car ID.
	 */
	public void setCarID(Car carID) {
		this.carID = carID;
	}
	/**
	 * Getter Method for Customer ID.
	 * @return A class object that consists of Customer ID.
	 */
	public Customer getCustomerID() {
		return customerID;
	}
	/**
	 * Setter Method for Customer ID.
	 * @param customerID A class object that consists of Customer ID.
	 */
	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}
	/**
	 * Getter Method for Start Date.
	 * @return A Date Start Date.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	/**
	 * Setter Method for Start Date.
	 * @param startDate A Date Start Date.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	/**
	 * Getter Method for End Date.
	 * @return A Date End Date.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}
	/**
	 * Setter Method for End Date.
	 * @param endDate A Date End Date.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	/**
	 * Getter Method for Lease Type.
	 * @return A Enum value Lease Type.
	 */
	public leaseType getType() {
		return type;
	}
	/**
	 * Setter Method for Lease Type.
	 * @param type A Enum value Lease Type.
	 */
	public void setType(leaseType type) {
		this.type = type;
	}

	/**
	 * To String method of the class Lease.
	 * @return A string of entire information about the Lease.
	 */
	@Override
	public String toString() {
		return String.format("Lease ID: %-5d | Car ID: %-5s | Customer ID: %-5s | Start Date: %-8s | End Date: %-8s | Lease Type: %-10s", 
				leaseID,carID.getCarID(),customerID.getCustomerID(),startDate,endDate,type);
	}
	
}
