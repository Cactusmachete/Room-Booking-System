package application;

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

	public String getSlot(){
		return (this.slot+" on "+this.date);
	}

	public String getUser(){
		return this.user;
	}

	public String getPurpose(){
		return (this.purpose+" - "+this.user);
	}

	public int getCapacity(){
		return room.Capacity;
	}

	public String getName(){
		return room.name;
	}

	public String getDate(){
		return this.date;
	}
}
