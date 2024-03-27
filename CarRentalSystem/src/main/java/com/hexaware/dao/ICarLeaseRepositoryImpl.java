package com.hexaware.dao;
import com.hexaware.entity.*;



import com.hexaware.entity.Car.vehicleStatus;
import com.hexaware.entity.Lease.leaseType;
import com.hexaware.exception.*;
import com.hexaware.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.time.temporal.*;
/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @see ICarLeaseRepository
 * This class implements the ICarLeaseRepository interface
 */
/**
 * This class implements the ICarLeaseRepository interface.
 */
public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {
	/**
	 * Connection variable to store MySql Connection.
	 */
	Connection con = DBConnection.getConnection();
	/**
	 * ResultSet variable to store Results of a query.
	 */
	ResultSet rs;
	/**
	 * PreparedStatement variable to for creating a Sql Query.
	 */
	PreparedStatement ps;
	/**
	 * Statement variable to for creating a Sql Query.
	 */
	Statement stmt;
	
	/**
	 * Creates a new record in Car Database.
	 * @param car Consists of all the values that have to be inserted into the Car Database.
	 * @throws SQLException
	 */
	
	@Override
	public void addCar(Car car)
	{
		try {
			ps = con.prepareStatement("insert into vehicle(make,model,year,dailyrate,status,passengercapacity,enginecapacity)"
					+ " values(?,?,?,?,?,?,?);");
			//ps.setInt(1, car.getCarID()); set to auto_increment in mysql
			ps.setString(1,car.getMake());
			ps.setString(2,car.getModel());
			ps.setInt(3,car.getYear());
			ps.setFloat(4, car.getDailyRate());
			String s = car.getStatus().toString();
			ps.setObject(5,s);
			ps.setInt(6,car.getPassengerCapacity());
			ps.setFloat(7,car.getEngineCapacity());
			
			int rows = ps.executeUpdate();
			if(rows>0)
			{
				System.out.println("Car Details entered successfully ✅.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * This method is only used for JUnit TestCases.
	 * @param car Consists of the values that have to be inserted into the Car database.
	 * @return Returns a boolean value to check whether the insertion was successful or not.
	 */
	
	public boolean addCarTest(Car car)
	{
		boolean check = false;
		try {
			ps = con.prepareStatement("insert into vehicle(make,model,year,dailyrate,status,passengercapacity,enginecapacity)"
					+ " values(?,?,?,?,?,?,?);");
			//ps.setInt(1, car.getCarID()); //set to auto_increment in mysql
			ps.setString(1,car.getMake());
			ps.setString(2,car.getModel());
			ps.setInt(3,car.getYear());
			ps.setFloat(4, car.getDailyRate());
			String s = car.getStatus().toString();
			ps.setObject(5,s);
			ps.setInt(6,car.getPassengerCapacity());
			ps.setFloat(7,car.getEngineCapacity());
			
			int rows = ps.executeUpdate();
			if(rows>0)
			{
				System.out.println("Car Details entered successfully ✅.");
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	/**
	 * Removes a car with a specific ID.
	 * @param carID The ID of the car which is going to be removed.
	 * @throws CarNotFoundException if there is no record with the given ID in the Database.
	 * @return Returns Void
	 */
	@Override
	public void removeCar(int carID) throws CarNotFoundException {
		try {
			ps = con.prepareStatement("delete from vehicle where vehicle_id = ?;");
			ps.setInt(1, carID);
			int rows = ps.executeUpdate();
			if(rows>0)
			{
				System.out.println("Car Details deleted successfully ✅");
			}
			else
				throw new CarNotFoundException ("Given ID does not match any record in the database.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Retrieves the list of available cars.
	 * @return A list of available cars
	 */
	@Override
	public List<Car> listAvailableCars() {
		List<Car> availableCars = new ArrayList<>();
		try {		
			stmt = con.createStatement();
			rs=stmt.executeQuery("select * from vehicle where status = 'available';");
			while(rs.next())
			{
				Car car = new Car();
			    car.setCarID(rs.getInt("vehicle_id"));
			    car.setMake(rs.getString("make"));
			    car.setModel(rs.getString("model"));
			    car.setYear(rs.getInt("year"));
			    car.setDailyRate(rs.getFloat("dailyRate"));
			    car.setStatus(vehicleStatus.AVAILABLE);
			    car.setPassengerCapacity(rs.getInt("passengerCapacity"));
			    car.setEngineCapacity(rs.getInt("engineCapacity"));
			    
			    availableCars.add(car);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return availableCars;
	}
	
	/**
	 * Retrieves the list of Rented Cars.
	 * @return A list of Rented Cars.
	 */
	@Override
	public List<Car> listRentedCars() {
		List<Car> rentedCars = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select distinct v.* from vehicle v join lease l on v.vehicle_id = l.vehicle_id;");
			while(rs.next())
			{
				Car car = new Car();
				car.setCarID(rs.getInt("vehicle_id"));
			    car.setMake(rs.getString("make"));
			    car.setModel(rs.getString("model"));
			    car.setYear(rs.getInt("year"));
			    car.setDailyRate(rs.getFloat("dailyRate"));
			    String status = rs.getString("status");
			    vehicleStatus carStatus = vehicleStatus.valueOf(status); // To set the Enum value.
			    car.setStatus(carStatus);
			    car.setPassengerCapacity(rs.getInt("passengerCapacity"));
			    car.setEngineCapacity(rs.getInt("engineCapacity"));
			    
				rentedCars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rentedCars;
	}

	/**
	 * Finds car by its ID.
	 * @param CarID The ID of car that have to fetched.
	 * @return Returns a class object that consists of the details of the car.
	 * @throws CarNotFoundException If no car with given Id exists.
	 */
	@Override
	public Car findCarById(int carID) throws CarNotFoundException {
		Car car = new Car();
		try {
			ps = con.prepareStatement("select * from vehicle where vehicle_id = ?;"
					,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, carID);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				throw new CarNotFoundException ("The given ID does not match any record in the database.");
			}
			rs.beforeFirst();
			while(rs.next())
			{
				car.setCarID(carID);
				car.setMake(rs.getString("make"));
			    car.setModel(rs.getString("model"));
			    car.setYear(rs.getInt("year"));
			    car.setDailyRate(rs.getFloat("dailyRate"));
			    String status = rs.getString("status");
			    vehicleStatus carStatus = vehicleStatus.valueOf(status); // To set the Enum value.
			    car.setStatus(carStatus);
			    car.setPassengerCapacity(rs.getInt("passengerCapacity"));
			    car.setEngineCapacity(rs.getInt("engineCapacity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}

	/**
	 * Updates an existing car record.
	 * @param car A car object that contains car Details.
	 * @return Void type return
	 * @throws CarNotFoundException If car with the given ID does not exist.
	 */
	@Override
	public void updateCar(Car car) throws CarNotFoundException {
		try {
			ps = con.prepareStatement("update vehicle set make = ?,model = ?,year = ?,"
					+ "dailyrate = ?,status = ?,passengercapacity = ?,"
					+ "enginecapacity = ? where vehicle_id = ?;");
			ps.setString(1, car.getMake());
			ps.setString(2, car.getModel());
			ps.setInt(3, car.getYear());
			ps.setFloat(4, car.getDailyRate());
			vehicleStatus s = car.getStatus();
			String status = s.toString();
			ps.setString(5, status);
			ps.setInt(6, car.getPassengerCapacity());
			ps.setFloat(7, car.getEngineCapacity());
			ps.setInt(8, car.getCarID());
			
			int rows = ps.executeUpdate();
			if(rows>0) System.out.println("Car details updated successfully ✅.");
			else throw new CarNotFoundException ("The given ID does not match any record.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creates a new record in Customer Database.
	 * @param customer Consists of all the values that have to be inserted into the Customer Database.
	 * @throws SQLException
	 */
	@Override
	public void addCustomer(Customer customer) {
		try {
			ps = con.prepareStatement("insert into customer(first_name,last_name,email,phone_number) values(?,?,?,?);");
			//ps.setInt(1, customer.getCustomerID());
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhoneNumber());
			
			int rows = ps.executeUpdate();
			if(rows>0) System.out.println("Customer details added successfully ✅.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Only used for JUnit test cases.
	 * @param customer
	 * @return Boolean to check whether the operation was successful or not.
	 */
	public boolean addCustomerTest(Customer customer) {
		boolean check = false;
		try {
			ps = con.prepareStatement("insert into customer(first_name,last_name,email,phone_number) values(?,?,?,?);");
			//ps.setInt(1, customer.getCustomerID());
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhoneNumber());
			
			int rows = ps.executeUpdate();
			if(rows>0) {System.out.println("Customer details added successfully ✅.");
			check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	

	/**
	 * Removes record of a customer with specific ID.
	 * @param customerID The ID of the customer that have to be deleted.
	 * @return void type return.
	 * @throws CustomerNotFoundException if customer with the given ID does not exist.
	 */
	@Override
	public void removeCustomer(int customerID) throws CustomerNotFoundException{
		try {
			ps = con.prepareStatement("delete from customer where customer_id = ?;");
			ps.setInt(1, customerID);
			
			int rows = ps.executeUpdate();
			if(rows>0) System.out.println("Customer details removed from database successfully ✅.");
			else throw new CustomerNotFoundException ("Cannot find the customer with the ID: "+customerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the list of customers.
	 * @return A List that consists of customers details.
	 */
	@Override
	public List<Customer> listCustomers() {
		List<Customer> customers = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from customer;");
			while(rs.next())
			{
				Customer cus = new Customer();
				cus.setCustomerID(rs.getInt("customer_id"));
				cus.setFirstName(rs.getString("first_name"));
				cus.setLastName(rs.getString("last_name"));
				cus.setEmail(rs.getString("email"));
				cus.setPhoneNumber(rs.getString("phone_number"));
				
				customers.add(cus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	/**
	 * Retrieves the record of a specific customer
	 * @param customerID The ID of the customer whom we have to retrieve.
	 * @return Customer class object that consists of the details of customer.
	 * @throws CustomerNotFoundException If the record with the given ID does not exist.
	 */

	@Override
	public Customer findCustomerById(int customerID) throws CustomerNotFoundException{
		Customer cus = new Customer();
		try {
			ps = con.prepareStatement("select * from customer where customer_id = ?;"
					,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, customerID);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				throw new CustomerNotFoundException ("Cannot find the customer with the ID: "+customerID);
			}
			rs.beforeFirst();
			while(rs.next())
			{	
				cus.setCustomerID(rs.getInt("customer_id"));
				cus.setFirstName(rs.getString("first_name"));
				cus.setLastName(rs.getString("last_name"));
				cus.setEmail(rs.getString("email"));
				cus.setPhoneNumber(rs.getString("phone_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cus;
	}

	/**
	 * Updates the record of a specific customer
	 * @param customerID The ID of the customer whom we have to update.
	 * @return Void type return.
	 * @throws CustomerNotFoundException If the record with the given ID does not exist.
	 */
	
	@Override
	public void updateCustomer(Customer customer) throws CustomerNotFoundException{
		try {
			ps = con.prepareStatement("update customer set first_name = ?,last_name = ?,"
					+ "email = ?,phone_number = ? where customer_id = ?;");
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPhoneNumber());
			ps.setInt(5, customer.getCustomerID());
			int rows = ps.executeUpdate();
			if(rows>0) 
				System.out.println("Customer details updated successfully ✅.");
			else 
				throw new CustomerNotFoundException ("Cannot find the customer with the ID: "+customer.getCustomerID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new lease record
	 * @param leaseID ID of the new lease.
	 * @param customerID ID of customer who is leasing the vehicle.
	 * @param carID Car ID
	 * @param startDate Starting date of lease.
	 * @param endDate end date of lease.
	 */

	@Override
	public Lease createLease(int leaseID,int customerID, int carID, LocalDate startDate, LocalDate endDate) {
		Lease lease = new Lease();
		Customer cus = new Customer();
		Car car = new Car();
		lease.setLeaseID(leaseID);
		cus.setCustomerID(customerID);
		lease.setCustomerID(cus);
		car.setCarID(carID);
		lease.setCarID(car);
		lease.setStartDate(startDate);
		lease.setEndDate(endDate);
		try {
			PreparedStatement pps = con.prepareStatement("select status from vehicle where vehicle_id = ?;");
			pps.setInt(1, lease.getCarID().getCarID());
			ResultSet rrs = pps.executeQuery();
			String s = null;
			while(rrs.next())
			{ 
				s = rrs.getString("status");
				s=s.toUpperCase();
			}
			System.out.println(s);		
			if (!"AVAILABLE".equals(s)) 
			{
	            System.out.println("You can't lease this car as it is not available right now.");
	            System.exit(0);
			}
			ps = con.prepareStatement("insert into lease values(?,?,?,?,?,?);");
			ps.setInt(1, lease.getLeaseID());
			ps.setInt(2, lease.getCarID().getCarID());
			ps.setInt(3, lease.getCustomerID().getCustomerID());
			ps.setObject(4, lease.getStartDate());
			ps.setObject(5, lease.getEndDate());
			long daysInBetween = ChronoUnit.DAYS.between(startDate, endDate);
			if(daysInBetween <= 28)
			{
				lease.setType(Lease.leaseType.DAILYLEASE);
				
			}
			else
			{
				lease.setType(Lease.leaseType.MONTHLYLEASE);
			}
			String type = lease.getType().toString();
			ps.setObject(6, type);
			int rows = ps.executeUpdate();
			if(rows > 0) System.out.println("New Lease successfully create ✅.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lease;
		
	}

	/**
	 * Just the same method that was created for Junit test cases.
	 * @param leaseID
	 * @param customerID
	 * @param carID
	 * @param startDate
	 * @param endDate
	 * @return boolean to check whether the creation was successful or not.
	 */
	public boolean createLeaseTest(int leaseID,int customerID, int carID, LocalDate startDate, LocalDate endDate) {
		Lease lease = new Lease();
		Customer cus = new Customer();
		Car car = new Car();
		boolean check = false;
		lease.setLeaseID(leaseID);
		cus.setCustomerID(customerID);
		lease.setCustomerID(cus);
		car.setCarID(carID);
		lease.setCarID(car);
		lease.setStartDate(startDate);
		lease.setEndDate(endDate);
		
		try {
			ps = con.prepareStatement("insert into lease values(?,?,?,?,?,?);");
			ps.setInt(1, lease.getLeaseID());
			ps.setInt(2, lease.getCarID().getCarID());
			ps.setInt(3, lease.getCustomerID().getCustomerID());
			ps.setObject(4, lease.getStartDate());
			ps.setObject(5, lease.getEndDate());
			long daysInBetween = ChronoUnit.DAYS.between(startDate, endDate);
			if(daysInBetween <= 28)
			{
				lease.setType(Lease.leaseType.DAILYLEASE);
				
			}
			else
			{
				lease.setType(Lease.leaseType.MONTHLYLEASE);
			}
			String type = lease.getType().toString();
			ps.setObject(6, type);
			int rows = ps.executeUpdate();
			if(rows > 0) System.out.println("New Leasw successfully create ✅.");
			check = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
		
	}
	
	/**
	 * Retrieves the details of a specific lease
	 * @param LeaseID The ID of the lease which we have to retrieve.
	 * @return Lease class object that consists of the details of Lease.
	 * @throws LeaseNotFoundException If the record with the given ID does not exist.
	 * */
	
	@Override
	public Lease returnCar(int LeaseID) throws LeaseNotFoundException{
		Lease lease = new Lease();
		Car car = new Car();
		Customer cus = new Customer();
		try {
			ps = con.prepareStatement("select * from lease where lease_id = ?;"
					,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, LeaseID);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				throw new LeaseNotFoundException ("Cannot find the Lease with the ID: "+LeaseID);
			}
			rs.beforeFirst();
			while(rs.next())
			{
				lease.setLeaseID(LeaseID);
				car.setCarID(rs.getInt("vehicle_id"));
				lease.setCarID(car);
				cus.setCustomerID(rs.getInt("customer_id"));
				lease.setCustomerID(cus);
				String s1 = rs.getString("startDate");
				String s2 = rs.getString("endDate");
				LocalDate startDate = LocalDate.parse(s1);
				LocalDate endDate = LocalDate.parse(s2);
				lease.setStartDate(startDate);
				lease.setEndDate(endDate);
				String t = rs.getString("type");
				t = t.toUpperCase();
			    leaseType type = leaseType.valueOf(t);
			    lease.setType(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lease;
	}

	/**
	 * Retrieves list of active leases.
	 * @return List that consists of all the active leases.
	 */
	
	@Override
	public List<Lease> listActiveLeases() {
		List<Lease> activeLeases = new ArrayList<>();
		Lease lease = new Lease();
		Customer cus = new Customer();
		Car car = new Car();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from lease;");
			while(rs.next())
			{
				String s1 = rs.getString("startDate");
				String s2 = rs.getString("endDate");
				LocalDate startDate = LocalDate.parse(s1);
				LocalDate endDate = LocalDate.parse(s2);
				LocalDate currentDate = LocalDate.now();
				if(currentDate.isAfter(startDate) && currentDate.isBefore(endDate))
				{
					lease.setLeaseID(rs.getInt("lease_id"));
					car.setCarID(rs.getInt("vehicle_id"));
					lease.setCarID(car);
					cus.setCustomerID(rs.getInt("customer_id"));
					lease.setCustomerID(cus);			
					lease.setStartDate(startDate);
					lease.setEndDate(endDate);
					String t = rs.getString("type");
				    leaseType type = leaseType.valueOf(t);
				    lease.setType(type);
				    
				    activeLeases.add(lease);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activeLeases;
	}

	/**
	 * Retrieves list of all leases.
	 * @return List that consists of all the leases.
	 */
	
	@Override
	public List<Lease> listLeaseHistory() {
		List<Lease> leaseHistory = new ArrayList<>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from lease;");
			while(rs.next())
			{
				Lease lease = new Lease();
				Customer cus = new Customer();
				Car car = new Car();
					lease.setLeaseID(rs.getInt("lease_id"));
					car.setCarID(rs.getInt("vehicle_id"));
					lease.setCarID(car);
					cus.setCustomerID(rs.getInt("customer_id"));
					lease.setCustomerID(cus);
					String s1 = rs.getString("startDate");
					String s2 = rs.getString("endDate");
					LocalDate startDate = LocalDate.parse(s1);
					LocalDate endDate = LocalDate.parse(s2);
					lease.setStartDate(startDate);
					lease.setEndDate(endDate);
					String t = rs.getString("type");
				    leaseType type = leaseType.valueOf(t);
				    lease.setType(type);
				    
				    leaseHistory.add(lease);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leaseHistory;
	}

	/**
	 * Calculates the cost of a specific lease.
	 * @return Void type but prints the total cost
	 * @throws LeaseNotFoundException if a lease with given ID is not found.
	 */
	
	@Override
	public void calculateTotalCost(int leaseID) throws LeaseNotFoundException{
		try {
			ps = con.prepareStatement("select v.make,v.model,v.dailyrate,l.startdate,l.enddate from lease l "
					+ "join vehicle v on v.vehicle_id = l.vehicle_id where l.lease_id = ?;"
					,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1,leaseID);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				throw new LeaseNotFoundException ("Cannot find the Lease with the ID: "+leaseID);
			}
			rs.beforeFirst();
			while(rs.next())
			{
				System.out.println("Car Name: "+rs.getString("make"));
				System.out.println("Car Model: "+rs.getString("model"));
				float rate = rs.getFloat("dailyrate");
				String s1 = rs.getString("startdate");
				String s2 = rs.getString("enddate");
				LocalDate startdate = LocalDate.parse(s1); 
				LocalDate enddate = LocalDate.parse(s2); 
				long days = ChronoUnit.DAYS.between(startdate, enddate);
				System.out.println("Total Days of lease: "+days);
				System.out.println("Total Cost: "+ (rate*days));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Records a new payment.
	 * @param lease Lease class that consists of lease details.
	 * @param amount The amount that the customer had paid.
	 * @throws LeaseNotFoundException if a lease with given ID is not found.
	 */
	@Override
	public void recordPayment(Lease lease, float amount) throws LeaseNotFoundException{
		try {
			ps = con.prepareStatement("insert into payment(lease_id,paymentdate,amount) values(?,?,?);");
			ps.setInt(1,lease.getLeaseID());
			ps.setObject(2, LocalDate.now());
			ps.setFloat(3, amount);
			int rows = ps.executeUpdate();
			if(rows>0)
			{
				System.out.println("Payment recorded successfully ✅.");
			}
			else
				throw new LeaseNotFoundException ("Cannot find the lease with ID: "+lease.getLeaseID());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LeaseNotFoundException ("Cannot find the lease with ID: "+lease.getLeaseID());
		}
	}

	
	/**
	 * Retrieves the details of a specific payment
	 * @param paymentID Consists of ID of a specific payment.
	 * @return Payment class object that consists of all the details.
	 * @throws PaymentNotFoundException if a Payment with given ID is not found.
	 */
	@Override
	public Payment retrievePayment(int paymentID) throws PaymentNotFoundException{
		Payment pay = new Payment();
		Lease lease = new Lease();
		try {
			ps = con.prepareStatement("select * from payment where payment_id = ?;"
					,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, paymentID);
			rs = ps.executeQuery();
			if(!rs.next())
			{
				throw new PaymentNotFoundException ("Cannot find the Lease with the ID: "+paymentID);
			}
			rs.beforeFirst();
			while(rs.next())
			{
				pay.setPaymentID(rs.getInt("payment_id"));
				lease.setLeaseID(rs.getInt("lease_id"));
				pay.setLeaseID(lease);
				String s = rs.getString("paymentdate");
				LocalDate date = LocalDate.parse(s);
				pay.setPaymentDate(date);
				pay.setAmount(rs.getFloat("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pay;
	}
	/**
	 * Calculates Total Revenue formed on leases.
	 * @return Void type return.
	 */
	@Override
	public void calculateTotalRevenue() {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select sum(amount) from payment;");
			while(rs.next())
			{
				System.out.println("Total Revenue till date: "+rs.getFloat(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Returns a List of all Payments
	 * @return A list consisting of all the Payment records.
	 */
	public List<Payment> paymentHistory() {
		List<Payment> payHistory = new ArrayList<>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from payment;");
			while(rs.next())
			{
				Lease lease = new Lease();
				Payment pay = new Payment();
				//Car car = new Car();
					pay.setPaymentID(rs.getInt("payment_id"));
					lease.setLeaseID(rs.getInt("lease_id"));
					pay.setLeaseID(lease);
					String s = rs.getString("paymentdate");
					LocalDate date = LocalDate.parse(s);
					pay.setPaymentDate(date);
				    pay.setAmount(rs.getFloat("amount"));
				    
				    payHistory.add(pay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payHistory;
	}
	/**
	 * Validates whether the email is of right format.
	 * @param email That consists the customers email.
	 * @throws InvalidEmailFormatException when the email format is wrong.
	 */
	public void validateEmail(String email) throws InvalidEmailFormatException 
    {
        if (email == null || !email.contains("@"))
        {
            throw new InvalidEmailFormatException("Email address must contain '@'.");
        }
        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) 
        {
            throw new InvalidEmailFormatException("Invalid email format.");
        }
        System.out.println("Email address validation successful.");
    }
}
