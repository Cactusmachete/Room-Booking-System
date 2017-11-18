package application;
/**
 * <h1>Booking class</h1>
 * This class represents an object of type Booking. 
 * slot represents the time sot, User contains the user name, purpose contains the purpose string, and room is 
 * the parameter of room being booked
 */
import java.io.Serializable;

public class Booking implements Serializable {

	private static final long serialVersionUID = 600L;
	String slot;
	String user;
	String purpose;
	Room room;
	String date;

	public Booking(String slot, String user, String purpose, Room room){
		this.slot = slot;
		this.user = user;
		this.purpose = purpose;
		this.room = room;
		this.date=Main.date;
	}
	/**
	 * getter for slot
	 * @return time slot
	 */
	public String getSlot(){
		return (this.slot+" on "+this.date);
	}
	/**
	 * getter for User
	 * @return user who booked
	 */
	public String getUser(){
		return this.user;
	}
	/**
	 * getter for purpose
	 * @return Purpose string
	 */
	public String getPurpose(){
		return (this.purpose+" - "+this.user);
	}
	/**
	 * getter for Capacity of room
	 * @return capacity of room booked
	 */
	public int getCapacity(){
		return room.Capacity;
	}
	/**
	 * getter for room name
	 * @return room name
	 */
	public String getName(){
		return room.name;
	}
	/**
	 * getter for date
	 * @return date string
	 */
	public String getDate(){
		return this.date;
	}
}
