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
public class Payment {
	private int paymentID;
	private Lease leaseID;
	private LocalDate paymentDate;
	private float amount;
	/**
	 * A non parameterized constructor.
	 */
	public Payment()
	{
		
	}
	/**
	 * A parameterized constructor.
	 */
	public Payment(int paymentID, Lease leaseID, LocalDate paymentDate, float amount) {
		super();
		this.paymentID = paymentID;
		this.leaseID = leaseID;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}
	/**
	 * Getter Method for Payment ID.
	 * @return An integer Payment ID.
	 */
	public int getPaymentID() {
		return paymentID;
	}
	/**
	 * Setter Method for Payment ID.
	 * @param paymentID An integer which has Payment ID.
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	/**
	 * Getter Method for Lease ID.
	 * @return A class object that consists of Lease ID.
	 */
	public Lease getLeaseID() {
		return leaseID;
	}
	/**
	 * Setter Method for Lease ID.
	 * @param leaseID A class object that consists of Lease ID.
	 */
	public void setLeaseID(Lease leaseID) {
		this.leaseID = leaseID;
	}
	/**
	 * Getter Method for Payment Date.
	 * @return A Date Payment Date.
	 */
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	/**
	 * Setter Method for Payment Date.
	 * @param paymentDate A Date Payment Date.
	 */
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * Getter Method for Amount.
	 * @return A Floating point number Amount.
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * Setter Method for Amount.
	 * @param amount A Floating point number Amount.
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * To String method of the class Lease.
	 * @return A string of entire information about the Payment.
	 */
	@Override
	public String toString() {
		return String.format("Payment ID: %-15s | Lease ID: %-15s | Payment Date %-15s | Amount: %-10.2f", 
				paymentID,leaseID.getLeaseID(),paymentDate,amount);
	}
	
}
