package com.hexaware.entity;

/**
 * @author PAVAN KALYAN
 * @version 1.0
 * @since 1.0
 */
/**
 *  The entity class for Car Database.
 */
public class Car {
	private int carID;
	private String make;
	private String model;
	private int year;
	public float dailyRate;
	public vehicleStatus status;
	private int passengerCapacity;
	public float engineCapacity;

	public enum vehicleStatus {
		AVAILABLE, NOT_AVAILABLE;
	}
	/**
	 * A non parameterized constructor.
	 */
	public Car() {

	}
	/**
	 * A parameterized constructor.
	 */
	public Car(int vehicleID, String make, String model, int year, float dailyRate, vehicleStatus status,
			int passengerCapacity, float engineCapacity) {
		super();
		this.carID = vehicleID;
		this.make = make;
		this.model = model;
		this.year = year;
		this.dailyRate = dailyRate;
		this.status = status;
		this.passengerCapacity = passengerCapacity;
		this.engineCapacity = engineCapacity;
	}
	/**
	 * A getter method for Car ID.
	 * @return An integer Car ID
	 */
	public int getCarID() {
		return carID;
	}
	/**
	 * Setter method for Car ID
	 * @param vehicleID
	 */
	public void setCarID(int vehicleID) {
		this.carID = vehicleID;
	}
	/**
	 * Getter method for Car Make.
	 * @return String Car Make.
	 */
	public String getMake() {
		return make;
	}
	/**
	 * Setter method for Car Make
	 * @param make that consists of car name.
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * Getter Method for Car Model.
	 * @return A String Consists of Car Model.
	 */
	public String getModel() {
		return model;
	}
	/**
	 * Setter Method for Car Model.
	 * @param model That consist of car Model.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * Getter method of Made Year.
	 * @return An integer that consists of year value.
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Setter method for Made Year
	 * @param year An integer that consists of year value.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * Getter Method for Daily Rate.
	 * @return A floating point number that consists of Daily Rate value.
	 */
	public float getDailyRate() {
		return dailyRate;
	}
	/**
	 * Setter Method for Daily Rate.
	 * @param dailyRate A floating point number that consists of Daily Rate value.
	 */

	public void setDailyRate(float dailyRate) {
		this.dailyRate = dailyRate;
	}
	/**
	 * Getter Method for Status.
	 * @return An Enum value that consists of Car Status.
	 */
	public vehicleStatus getStatus() {
		return status;
	}
	/**
	 * Setter Method for Status.
	 * @param status An Enum value that consists of Car Status.
	 */
	public void setStatus(vehicleStatus status) {
		this.status = status;
	}
	/**
	 * Getter Method for Status.
	 * @return An Integer value that consists of Passenger Capacity.
	 */
	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	/**
	 * Setter Method for Status.
	 * @param passengerCapacity An Integer value that consists of Passenger Capacity.
	 */
	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}
	/**
	 * Getter Method for Status.
	 * @return A floating point number value that consists of Engine Capacity.
	 */
	public float getEngineCapacity() {
		return engineCapacity;
	}
	/**
	 * Setter Method for Status.
	 * @return engineCapacity A floating point number value that consists of Engine Capacity.
	 */
	public void setEngineCapacity(float engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	/**
	 * To String method of the class.
	 * @return A string of entire information about the Car.
	 */
	@Override
    public String toString() 
	{
        return String.format("Car ID: %-5d | Make: %-12s | Model: %-10s | Year: %-5d | Daily Rate: %-8.2f "
        		+ "| Status: %-15s | Passenger Capacity: %-3d | Engine Capacity: %-5.1f",
                             carID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity);
    }

}
