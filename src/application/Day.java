package application;

import java.io.Serializable;

public class Day implements Serializable {

	private static final long serialVersionUID = 500L;
	String date;
	int[] Status = new int[48];
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


	
	public Day(String date) {
		this.date = date;
	}
	
	public void bookroom(String start,String end) {
		int initialhour = Integer.valueOf(start.substring(0,2));
		int endhour = Integer.valueOf(end.substring(0,2));
		int initialindex = 2*initialhour;
		int endindex = 2*endhour;
		if(Integer.valueOf(start.substring(2,4))==30) {
			initialindex = initialindex + 1;
		}
		if(Integer.valueOf(end.substring(2,4))==30) {
			endindex = endindex + 1;
		}
		
		try {
			int check = 0;
			for(int i = initialindex;i<endindex+1;i++) {
				if(Status[i]==1) {
					check = 1;
					throw new AlreadyBookedException("");
				}
			}
			if(check==0) {
				for(int i = initialindex;i<endindex+1;i++) {
					Status[i] = 1;
				}
			}
		}
		catch(AlreadyBookedException e) {
			
		}
		
	}
	
	public void cancelbooking(String start,String end) {
		int initialhour = Integer.valueOf(start.substring(0,2));
		int endhour = Integer.valueOf(end.substring(0,2));
		int initialindex = 2*initialhour;
		int endindex = 2*endhour;
		if(Integer.valueOf(start.substring(2,4))==30) {
			initialindex = initialindex + 1;
		}
		if(Integer.valueOf(end.substring(2,4))==30) {
			endindex = endindex + 1;
		}
		for(int i = initialindex;i<endindex+1;i++) {
			Status[i] = 0;
		}
	}
	
	

}

class AlreadyBookedException extends Exception {
	AlreadyBookedException(String s ){
		super(s);
	}
}
