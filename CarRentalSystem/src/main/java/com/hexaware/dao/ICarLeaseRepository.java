package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.exception.CarNotFoundException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.LeaseNotFoundException;
import com.hexaware.exception.PaymentNotFoundException;

import java.util.*;
import java.time.*;
/**
 * Interface with abstract methods.
 */
public interface ICarLeaseRepository {
	
	/***
	 * Interface Implementation.
	 * Abstract Methods for Car Management.
	 * @throws CarNotFoundException
	 */
	public void addCar(Car car);
	public void removeCar(int carID) throws CarNotFoundException;
	public List<Car> listAvailableCars();
	public List<Car> listRentedCars();
	public Car findCarById(int carID) throws CarNotFoundException;
	public void updateCar(Car car) throws CarNotFoundException; // Additional Method for Updating Customer Details.
	
	/***
	 * Abstract Methods for Customer Management.
	 * @throws CustomerNotFoundException
	 */
	public void addCustomer(Customer customer);
	public void removeCustomer(int customerID) throws CustomerNotFoundException;
	public List<Customer> listCustomers();
	public Customer findCustomerById(int customerID) throws CustomerNotFoundException;
	public void updateCustomer(Customer customer) throws CustomerNotFoundException; // Additional Method for Updating Customer Details.
	
	/***
	 * Abstract Methods for Lease Management.
	 * @throws LeaseNotFoundException
	 */
	public Lease createLease(int leaseID,int customerID,int carID, LocalDate startDate, LocalDate endDate);
	public Lease returnCar(int LeaseID) throws LeaseNotFoundException;
	public List<Lease> listActiveLeases();
	public List<Lease> listLeaseHistory();
	public void calculateTotalCost(int leaseID) throws LeaseNotFoundException; // Additional Method that calculates the total cost of a lease based on the type.
	
	/***
	 * Abstract Methods for Payment Handling.
	 * @throws LeaseNotFoundException,PaymentNotFoundException
	 */
	public void recordPayment(Lease lease, float amount) throws LeaseNotFoundException;
	public Payment retrievePayment(int paymentID) throws PaymentNotFoundException;  // Additional Method for Payment Details retrieval.
	public void calculateTotalRevenue(); // Additional Method for calculating total payment revenue.
}
