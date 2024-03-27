package com.hexaware.test;

import java.sql.SQLException;

import org.junit.*;

import com.hexaware.dao.ICarLeaseRepositoryImpl;
import com.hexaware.entity.Car;
import com.hexaware.entity.Car.vehicleStatus;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.exception.*;
/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @since 1.0
 * This class is responsible for testing whether exceptions are being handled or not.
 */
/**
 *  The test class file for Exception Handling.
 */
public class ExceptionHandlingTest {
	/**
	 * Static variable to access methods
	 */
	static ICarLeaseRepositoryImpl imp;
	/**
	 * Set Up method
	 */
	@BeforeClass
	public static void setUp()
	{
		System.out.println("In setUp");
        imp = new ICarLeaseRepositoryImpl();
	}
	
	/**
	 * For Remove car method
	 * @throws CarNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = CarNotFoundException.class)
    public void testRemoveCarNotFound() throws CarNotFoundException, SQLException {
		System.out.println("CarNotFoundException Testing in Remove");
        imp.removeCar(999);
    }
	/**
	 * For Find Car by ID method
	 * @throws CarNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = CarNotFoundException.class)
    public void testFindCarByIdNotFound() throws CarNotFoundException, SQLException {
		System.out.println("CarNotFoundException Testing for Finding Car");
        imp.findCarById(999);
    }
	/**
	 * For Update Car method
	 * @throws CarNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = CarNotFoundException.class)
    public void testUpdateCarNotFound() throws CarNotFoundException, SQLException {
		System.out.println("CarNotFoundException Testing for Updating Car");
		Car car = new Car();
		car.setCarID(999);
		car.setMake("Honda");
		car.setModel("civic");
		car.setDailyRate(2500);
		car.setPassengerCapacity(4);
		car.setEngineCapacity(3);
		String s = "AVAILABLE";
		vehicleStatus status = vehicleStatus.valueOf(s);
		car.setStatus(status);
		car.setEngineCapacity(12);
        imp.updateCar(car);
        car = null;
    }
	/**
	 * For Remove customer method
	 * @throws CustomerNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = CustomerNotFoundException.class)
    public void testRemoveCustomerNotFound() throws CustomerNotFoundException, SQLException {
		System.out.println("CustomerNotFoundException Testing in Remove");
        imp.removeCustomer(999);
    }
	/**
	 * For Find customer by ID method
	 * @throws CustomerNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = CustomerNotFoundException.class)
    public void testFindCustomerByIdNotFound() throws CustomerNotFoundException, SQLException {
		System.out.println("CustomerNotFoundException Testing for Retrieving");
        imp.findCustomerById(999);
    }
	/**
	 * For Update Customer method
	 * @throws CustomerNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = CustomerNotFoundException.class)
    public void testUpdateCustomerNotFound() throws CustomerNotFoundException, SQLException {
		System.out.println("CustomerNotFoundException Testing for Updating details");
		Customer cus = new Customer();
		cus.setCustomerID(999);
		cus.setFirstName("Raju");
		cus.setLastName("Bhai");
		cus.setEmail("raj@gmail.com");
		cus.setPhoneNumber("1234568957");
        imp.updateCustomer(cus);
        cus = null;
    }
	/**
	 * Test case for Return Car method
	 * @throws LeaseNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = LeaseNotFoundException.class)
    public void testReturnCarNotFound() throws LeaseNotFoundException, SQLException {
		System.out.println("LeaseNotFoundException Testing in returnCar method");
        imp.returnCar(999);
    }
	/**
	 * Test case for Calculate Cost method
	 * @throws LeaseNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = LeaseNotFoundException.class)
    public void testCalculateCostNotFound() throws LeaseNotFoundException, SQLException {
		System.out.println("LeaseNotFoundException Testing in calculateTotalCost method");
        imp.calculateTotalCost(999);

    }
	/**
	 * Test case for Record Payment Method
	 * @throws LeaseNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = LeaseNotFoundException.class)
    public void testRecordPaymentNotFound() throws LeaseNotFoundException, SQLException {
		System.out.println("LeaseNotFoundException Testing in Record method");
		Lease l = new Lease();
		l.setLeaseID(999);
		float amount = 11111;
        imp.recordPayment(l,amount);
        l=null;
    }
	/**
	 * Test case for Retrieve Payment method
	 * @throws PaymentNotFoundException
	 * @throws SQLException
	 */
	@Test(expected = PaymentNotFoundException.class)
    public void testRetrievePaymentNotFound() throws PaymentNotFoundException, SQLException {
		System.out.println("PaymentNotFoundException Testing ");
        imp.retrievePayment(999);
    }
	/**
	 * After class
	 */
	@AfterClass
	public static void tearDown() {
		System.out.println("From tearDown");
		imp =null;
	}
}
