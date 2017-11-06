package application;

import java.io.Serializable;

public class Day implements Serializable {

	private static final long serialVersionUID = 500L;


	String date;
	Boolean status = false;
	String bookedBy="";
	String Purpose="";

	int[] slots = new int[48];


	public Day(String date) {
		this.date = date;
	}

	public String toString() {
		 return this.date;
	}

	public Boolean isBooked(){
		return this.status;
	}

	public void book(String by, String purpose){
		this.bookedBy = by;
		this.Purpose = purpose;
		this.status=true;
	}
	public void cancel() {
		this.bookedBy="";
		this.status=false;
		this.Purpose="";

	}


}