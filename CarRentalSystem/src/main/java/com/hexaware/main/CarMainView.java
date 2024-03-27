package com.hexaware.main;

import java.time.LocalDate;
import java.util.*;
import com.hexaware.entity.*;
import com.hexaware.entity.Car.vehicleStatus;
import com.hexaware.exception.CarNotFoundException;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.InvalidEmailFormatException;
import com.hexaware.exception.LeaseNotFoundException;
import com.hexaware.exception.PaymentNotFoundException;
import com.hexaware.dao.ICarLeaseRepositoryImpl;
/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @since 1.0
 * @see CarMainView
 */
/**
 * This class acts like a user interface. 
 * Prompts user for input and performs user desired operations on Database.
 */
public class CarMainView {
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) throws CarNotFoundException, 
	CustomerNotFoundException, LeaseNotFoundException, PaymentNotFoundException
	{
		String nxt;
		String sub;
		ICarLeaseRepositoryImpl imp = new ICarLeaseRepositoryImpl();
		Car car = new Car();
		Customer cus = new Customer();
		Lease lease = new Lease();
		//Payment pay = new Payment();
		do
		{
		System.out.println("Select a database: ");
		System.out.println("1. Car");
		System.out.println("2. Customer");
		System.out.println("3. Lease");
		System.out.println("4. Payment");
		int x = sc.nextInt();
		
			switch(x)
			{
			case 1:
			{
				do
				{
				System.out.println("Enter an Option : ");
				System.out.println("1. Add new car details");
				System.out.println("2. Remove car details");
				System.out.println("3. Get a list of available cars");
				System.out.println("4. Get a list rented cars");
				System.out.println("5. Find a car by its ID");
				System.out.println("6. Update car details");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					/*System.out.println("Enter car ID: ");
					int cid = sc.nextInt();
					car.setCarID(cid);*/
					System.out.println("Enter make (Please use '_' insted of space): ");
					String make = sc.next();
					car.setMake(make);
					System.out.println("Enter Model: ");
					String model = sc.next();
					car.setModel(model);
					System.out.println("Enter manufactured year: ");
					int year = sc.nextInt();
					car.setYear(year);
					System.out.println("Enter daily rate: ");
					float rate = sc.nextFloat();
					car.setDailyRate(rate);
					System.out.print("Enter status (Available or Not_Available): ");
				    String stat = sc.next();
			        stat = stat.toUpperCase();
				    vehicleStatus status = vehicleStatus.valueOf(stat);
				    car.setStatus(status);
					System.out.println("Enter passenger capacity: ");
					int pcap = sc.nextInt();
					car.setPassengerCapacity(pcap);
					System.out.println("Enter engine capacity: ");
					float ecap = sc.nextFloat();
					car.setEngineCapacity(ecap);
					
					imp.addCar(car);
					break;
				}
				case 2:
				{
					System.out.println("Enter car ID: ");
					int cid = sc.nextInt();
					imp.removeCar(cid);
					break;
				}
				case 3:
				{
					List<Car> availableCars = new ArrayList<>();
					availableCars = imp.listAvailableCars();
					ListIterator<Car> it = availableCars.listIterator();
					System.out.println("Car Details:");
					System.out.println("---------------------------------------------------------------------------------------"
							+ "---------------------------------");
					System.out.printf("%-10s %-15s %-15s %-10s %-10s %-15s %-20s %-15s\n", "Car ID", "Make", "Model", 
							"Year", "Rate", "Status", "Passenger Capacity", "Engine Capacity");
					System.out.println("----------------------------------------------------------------------------------------"
							+ "--------------------------------");
					while (it.hasNext()) 
					{
					    Car cars = it.next();
					    System.out.printf("%-10d %-15s %-15s %-10d %-10.2f %-15s %-20d %-15.1f\n", cars.getCarID(), cars.getMake(), cars.getModel(), cars.getYear(), cars.getDailyRate(), cars.getStatus(), cars.getPassengerCapacity(), cars.getEngineCapacity());
					}
					break;
				}
				case 4:
				{
					List<Car> rentedCars = new ArrayList<>();
					rentedCars = imp.listRentedCars();
					ListIterator<Car> iterator = rentedCars.listIterator();
					System.out.println("Car Details:");
					System.out.println("---------------------------------------------------------------------------------------"
							+ "---------------------------------");
					System.out.printf("%-10s %-15s %-15s %-10s %-10s %-15s %-20s %-15s\n", "Car ID", "Make", "Model", 
							"Year", "Rate", "Status", "Passenger Capacity", "Engine Capacity");
					System.out.println("----------------------------------------------------------------------------------------"
							+ "--------------------------------");
					while (iterator.hasNext()) 
					{
					    Car cars = iterator.next();
					    System.out.printf("%-10d %-15s %-15s %-10d %-10.2f %-15s %-20d %-15.1f\n", cars.getCarID(), cars.getMake(), cars.getModel(), cars.getYear(), cars.getDailyRate(), cars.getStatus(), cars.getPassengerCapacity(), cars.getEngineCapacity());
					}
					break;
				}
				case 5:
				{
					System.out.println("Enter car ID: ");
					int cid = sc.nextInt();
					Car getCar = imp.findCarById(cid);
					System.out.println(getCar);
					break;
				}
				case 6:
				{
					System.out.println("Enter car ID: ");
					int cid = sc.nextInt();
					car.setCarID(cid);
					System.out.println("Enter make (Please use '_' insted of space): ");
					String make = sc.next();
					car.setMake(make);
					System.out.println("Enter Model: ");
					String model = sc.next();
					car.setModel(model);
					System.out.println("Enter manufactured year: ");
					int year = sc.nextInt();
					car.setYear(year);
					System.out.println("Enter daily rate: ");
					float rate = sc.nextFloat();
					car.setDailyRate(rate);
					System.out.print("Enter status (Available or Not_Available): ");
				    String stat = sc.next();
			        stat = stat.toUpperCase();
				    vehicleStatus status = vehicleStatus.valueOf(stat);
				    car.setStatus(status);
					System.out.println("Enter passenger capacity: ");
					int pcap = sc.nextInt();
					car.setPassengerCapacity(pcap);
					System.out.println("Enter engine capacity: ");
					float ecap = sc.nextFloat();
					car.setEngineCapacity(ecap);
					
					imp.updateCar(car);
					break;
				}
				default :
				{
					System.out.println("Enter correct Option");
				}
				}
				System.out.println("Anything else with Car database ?");
				sub = sc.next();
				}while(sub.equals("Y")|| sub.equals("y"));
				System.out.println("Thank you !!!");
				break;
			}
			
			case 2:
			{
				do
				{
				System.out.println("Enter an Option : ");
				System.out.println("1. Add new customer details");
				System.out.println("2. Remove customer details");
				System.out.println("3. Get a list of customers");
				System.out.println("4. Find customer by ID");
				System.out.println("5. Update customer details");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter customer's First name: ");
					String fName = sc.next();
					cus.setFirstName(fName);
					System.out.println("Enter customer's Last Name: ");
					String lName = sc.next();
					cus.setLastName(lName);
					System.out.println("Enter Email ID: ");
					String email = sc.next();
					try
					{
						imp.validateEmail(email);
						cus.setEmail(email);
					}
					catch(InvalidEmailFormatException e)
					{
						System.out.println("Error: " + e.getMessage());
			            System.exit(0);
					}
					System.out.println("Enter Phone number: ");
					String pNumber = sc.next();
					cus.setPhoneNumber(pNumber);
					
					imp.addCustomer(cus);
					break;
				}
				case 2:
				{
					System.out.println("Enter customer ID: ");
					int cid = sc.nextInt();
					imp.removeCustomer(cid);
					break;
				}
				case 3:
				{
					List<Customer> customers = new ArrayList<>();
					customers = imp.listCustomers();
					ListIterator<Customer> it = customers.listIterator();
					System.out.println("Customer Details:");
					System.out.println("---------------------------------------------------------------------------------------");
					System.out.printf("%-10s %-15s %-15s %-20s %-13s\n","Customer ID","First Name","Last Name", 
							"Email","Phone Number");
					System.out.println("----------------------------------------------------------------------------------------");
					while (it.hasNext()) 
					{
					    Customer cust = it.next();
					    System.out.printf("%-10d %-15s %-15s %-20s %-13s\n",cust.getCustomerID(),cust.getFirstName(),cust.getLastName(),cust.getEmail(),cust.getPhoneNumber());
					}
					break;
				}
				case 4:
				{
					System.out.println("Enter customer ID: ");
					int cid = sc.nextInt();
					Customer getCust = imp.findCustomerById(cid);
					System.out.println(getCust);
					break;
				}
				case 5:
				{
					System.out.println("Enter customer ID: ");
					int cid = sc.nextInt();
					cus.setCustomerID(cid);
					System.out.println("Enter customer's First name: ");
					String fName = sc.next();
					cus.setFirstName(fName);
					System.out.println("Enter customer's Last Name: ");
					String lName = sc.next();
					cus.setLastName(lName);
					System.out.println("Enter Email ID: ");
					String email = sc.next();
					try
					{
						imp.validateEmail(email);
						cus.setEmail(email);
					}
					catch(InvalidEmailFormatException e)
					{
						System.out.println("Error: " + e.getMessage());
			            System.exit(0);
					}
					System.out.println("Enter Phone number: ");
					String pNumber = sc.next();
					cus.setPhoneNumber(pNumber);
					
					imp.updateCustomer(cus);
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option.");
				}
				}
				System.out.println("Anything else with customer database ?");
				sub = sc.next();
				}while(sub.equals("Y")|| sub.equals("y"));
				System.out.println("Thank you !!!");
				break;
			}
			
			case 3:
			{
				do
				{
				System.out.println("Enter an Option : ");
				System.out.println("1. Create a lease.");
				System.out.println("2. Get Details of a lease.");
				System.out.println("3. Get a list of active lease.");
				System.out.println("4. Get lease history.");
				System.out.println("5. Calculate total cost of a lease.");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter Lease ID: ");
					int lid = sc.nextInt();
					System.out.println("Enter Customer ID: ");
					int custid = sc.nextInt();
					System.out.println("Enter Car ID: ");
					int carid = sc.nextInt();
					System.out.println("Enter Start Date: ");
					String start = sc.next();
					LocalDate startDate = LocalDate.parse(start);
					System.out.println("Enter End Date: ");
					String end = sc.next();
					LocalDate endDate = LocalDate.parse(end);
					
					Lease newLease = imp.createLease(lid, custid, carid, startDate, endDate);
					System.out.println("Checkout the lease you tried to add."+"\n"+newLease);
					break;
				}
				case 2:
				{
					System.out.println("Enter Lease ID: ");
					int lid = sc.nextInt();
					Lease leaseDetail = imp.returnCar(lid);
					System.out.println(leaseDetail);
					
					break;
				}
				case 3:
				{
					List<Lease> leases = new ArrayList<>();
					leases = imp.listActiveLeases();
					ListIterator<Lease> it = leases.listIterator();
					System.out.println("Lease Details:");
					System.out.println("---------------------------------------------------------------------------------------");
					System.out.printf("%-15s %-15s %-15s %-15s %-15s %-10s \n","Lease ID","Car ID","Customer ID", 
		                    "Start Date","End Date","Lease Type");
					System.out.println("----------------------------------------------------------------------------------------");
					while (it.hasNext()) 
					{
					    Lease L = it.next();
					    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-10s \n",
					    		L.getLeaseID(),L.getCarID().getCarID(),L.getCustomerID().getCustomerID(),L.getStartDate(),L.getEndDate(),L.getType());
					}
					break;
				}
				case 4:
				{
					List<Lease> leaseHistory = new ArrayList<>();
					leaseHistory = imp.listLeaseHistory();
					ListIterator<Lease> it = leaseHistory.listIterator();
					System.out.println("Lease History:");
					System.out.println("---------------------------------------------------------------------------------------");
					System.out.printf("%-15s %-15s %-15s %-15s %-15s %-10s \n","Lease ID","Car ID","Customer ID", 
		                    "Start Date","End Date","Lease Type");
					System.out.println("----------------------------------------------------------------------------------------");
					while (it.hasNext()) 
					{
					    Lease L = it.next();
					    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-10s \n",
					    		L.getLeaseID(),L.getCarID().getCarID(),L.getCustomerID().getCustomerID(),L.getStartDate(),L.getEndDate(),L.getType());
					}
					break;
				}
				case 5:
				{
					System.out.println("Enter Lease ID: ");
					int Lid = sc.nextInt();
					imp.calculateTotalCost(Lid);
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option");
				}
				}
				System.out.println("Anything else with lease database ?");
				sub = sc.next();
				}while(sub.equals("Y")|| sub.equals("y"));
				System.out.println("Thank you !!!");
				break;
			}
			
			case 4:
			{
				do
				{
				System.out.println("Enter an Option : ");
				System.out.println("1. Record a Payment.");
				System.out.println("2. Retrieve Payment Details.");
				System.out.println("3. Calculate Total Revenue.");
				System.out.println("4. Get Payment History.");
				int i = sc.nextInt();
				switch(i)
				{
				case 1:
				{
					System.out.println("Enter Lease ID: ");
					int lid = sc.nextInt();
					lease.setLeaseID(lid);
					System.out.println("Enter Amount: ");
					float amount = sc.nextFloat();					
					imp.recordPayment(lease, amount);
					break;
				}
				case 2:
				{
					System.out.println("Enter Payment ID: ");
					int pid = sc.nextInt();
					Payment payDetails = imp.retrievePayment(pid);
					System.out.println(payDetails);
					
					break;
				}
				
				case 3:
				{
					imp.calculateTotalRevenue();
					
					break;
				}
				
				case 4:
				{
					List<Payment> payHistory = new ArrayList<>();
					payHistory = imp.paymentHistory();
					ListIterator<Payment> it = payHistory.listIterator();
					System.out.println("Payment History:");
					System.out.println("----------------------------------------------------------------------");
					System.out.printf("%-15s %-15s %-15s %-15s \n","Payment ID","Lease ID","Payment Date","Amount");
					System.out.println("----------------------------------------------------------------------");
					while (it.hasNext()) 
					{
					    Payment p = it.next();
					    System.out.printf("%-15s %-15s %-15s %-15s \n",
					    		p.getPaymentID(),p.getLeaseID().getLeaseID(),p.getPaymentDate(),p.getAmount());
					}
					break;
				}
				
				default :
				{
					System.out.println("Enter correct Option");
				}
				}
				System.out.println("Anything else with payment ?");
				sub = sc.next();
				}while(sub.equals("Y")|| sub.equals("y"));
				System.out.println("Thank you !!!");
				break;
			}
			
			default :
			{
				System.out.println("Enter correct Option");
			}
			}
		System.out.println("Do you have any work with other databases ???");
		nxt = sc.next();
		}while(nxt.equals("Y")|| nxt.equals("y"));
		System.out.println("Thank you !!!");
		
		System.exit(0);
	}
}
