package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Day implements Serializable {

	private static final long serialVersionUID = 500L;
	String date;
	int[] Status = new int[48];
	ArrayList<Booking> booking = new ArrayList<Booking>();
	int[] slots = new int[48];



	public Day(String date) {
		this.date = date;
		int year = Integer.valueOf(date.substring(6,10));
		int month = Integer.valueOf(date.substring(3,5));
		int day =  Integer.valueOf(date.substring(0,2));
		Date current = new Date(year,month,day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		int dayoftheweek = cal.get(Calendar.DAY_OF_WEEK);
		
	}

	public String toString() {
		 return this.date;
	}



	public void bookRoom(String startHour,String startMin, String endHour, String endMin, User user, String purpose, Room room) {
		int initialhour = Integer.valueOf(startHour);
		int endhour = Integer.valueOf(endHour);
		int initialindex = 2*initialhour;
		int endindex = 2*endhour;
		if(Integer.valueOf(startMin)==30) {
			initialindex = initialindex + 1;
		}
		if(Integer.valueOf(endMin)==30) {
			endindex = endindex + 1;
		}
		try {
			int check = 0;
			for(int i = initialindex;i<=endindex;i++) {
				if(Status[i]==1) {
					check = 1;
					throw new AlreadyBookedException("");
				}
			}
			if(check==0) {
				for(int i = initialindex;i<=endindex;i++) {
					Status[i] = 1;

				}
			}
			String slot = startHour+":"+startMin+" - "+endHour+":"+endMin;
			System.out.println(slot);
			Booking book = new Booking(slot,user.email_id, purpose, room);
			booking.add(book);
		}
		catch(AlreadyBookedException e) {

		}

	}

	public ArrayList<Booking> getBooking(){
		return this.booking;
	}

	public String getSlots() {
		String ans = "";
		int i = 0;
		while(i<47) {
			if(Status[i]==1 && Status[i+1]==1) {
				i = i + 1;
			}
			else {
				String start="";
				if(ans.isEmpty()==false){
					start=", ";
				}
				int j = i + 1;
				int start1 = i/2;
				if(start1<10){ start =start+ "0"+start1;}
				else{start = start + start1;}

				if(i%2==1) {
					start = start+":30";
				}
				else {
					start = start+ ":00";
				}
				ans = ans + start+" - ";
				while(j<47) {
					if(Status[j]==1) {
						break;
					}
					else {
						j = j + 1;
					}
				}
				int end1 = j/2;
				if(end1<10){ ans =ans+ "0"+end1;}
				else{ans = ans + end1;}
				if(j%2==1) {
					ans = ans+":30";
				}
				else {
					ans = ans+":00";

				}
				i = j + 1;


			}
		}
		return ans;
	}


	public void cancelbooking(Booking booking) {
		String slot = booking.getSlot();
		int initialhour = Integer.valueOf(slot.substring(0,2));
		int endhour = Integer.valueOf(slot.substring(8,10));
		int initialindex = 2*initialhour;
		int endindex = 2*endhour;
		if(Integer.valueOf(slot.substring(3,5))==30) {
			initialindex = initialindex + 1;
		}
		if(Integer.valueOf(slot.substring(11,13))==30) {
			endindex = endindex + 1;
		}
		for(int i = initialindex;i<endindex+1;i++) {
			Status[i] = 0;
		}

		this.booking.remove(booking);
	}
	
	

}

class AlreadyBookedException extends Exception {
	AlreadyBookedException(String s ){
		super(s);
	}
}
