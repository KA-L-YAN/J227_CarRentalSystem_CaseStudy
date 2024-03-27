package com.hexaware.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.*;
import com.hexaware.entity.*;
import com.hexaware.dao.ICarLeaseRepositoryImpl;
/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @since 1.0
 */
/**
 * This class is responsible for checking whether the records are successfully inserted or not.
 */

public class CarCreatedTest {
static ICarLeaseRepositoryImpl imp;
	/**
	 * Static variable of type ICarLeaseRepositoryImpl to access methods.
	 */
	@BeforeClass
	public static void setUp()
	{
		/**
		 * Set Up method.
		 */
		System.out.println("In setUp");
        imp = new ICarLeaseRepositoryImpl();
	}
	
	/**
	 * Test Case for adding car.
	 */
	@Test
    public void testAddCarSuccess(){
		
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Camry");
        car.setYear(2023);
        car.setDailyRate(1700);
        car.setStatus(Car.vehicleStatus.AVAILABLE);
        car.setPassengerCapacity(5);
        car.setEngineCapacity(2.5f);
        
        boolean check = imp.addCarTest(car);
        
        assertTrue(check);
	}
	/**
	 * Test Case for adding car.
	 * @throws NullPointerException if null values are added
	 */
	@Test(expected = NullPointerException.class)
    public void testAddCarFail() throws NullPointerException{
		
        imp.addCarTest(null);
        
	}
	/**
	 * Test Case for adding Customer.
	 */
	@Test
    public void testAddCustomerSuccess() {
        Customer cus = new Customer();
        cus.setFirstName("Blake");
        cus.setLastName("Flame");
        cus.setEmail("blake@gmail.com");
        cus.setPhoneNumber("74569812301");
        boolean check = imp.addCustomerTest(cus);
        assertTrue(check);
        cus = null;
	}
	/**
	 * Test Case for adding Customer.
	 * @throws NullPointerException if null values are added
	 */
	@Test(expected = NullPointerException.class)
    public void testAddCustomerFail() throws NullPointerException{
        imp.addCustomerTest(null);
	}
	/**
	 * Test Case for adding Lease.
	 * @throws SQLException
	 */
	 @Test
	public void testCreateLeaseSuccess() throws SQLException {
	    int leaseID = 1001;
        int customerID = 205;
        int carID = 102;
        LocalDate startDate = LocalDate.of(2024, 4, 1);
        LocalDate endDate = LocalDate.of(2024, 4, 10);
        boolean check = imp.createLeaseTest(leaseID, customerID, carID, startDate, endDate);
        assertTrue(check);
	    }
	
	 
	@AfterClass
	public static void tearDown() {
		System.out.println("From tearDown");
		imp =null;
	}
}
