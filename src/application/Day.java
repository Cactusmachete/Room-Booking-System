package application;
/**
 * <h1> The Day Class </h1>
 * This class represents a Day of working here at IIIT Delhi, date is a date string, Status represents the status of
 * day from 00:00 to 11:30, booking is an arraylist of all the bookings made that day
 * @author Rohan Chhokra
 */
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Day implements Serializable {

	private static final long serialVersionUID = 500L;
	String date;
	int[] Status = new int[48];
	String[] day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	ArrayList<Booking> booking = new ArrayList<Booking>();

	public Day(String date, Room room) {
		this.room = room;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date d=null;
		this.date = date;
		try {
			d = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Main.c.setTime(d);
		int ok = Main.c.get(Calendar.DAY_OF_WEEK);
		if(ok!=1 && ok!=7){
			for(int i=0; i<Main.course_list.size(); i++){
				Course course = Main.course_list.get(i);
				ArrayList<Classes> mlem = course.classes.get(day[ok-2]);
				if(mlem!=null){
					for(int j=0; j<mlem.size(); j++){
						if(mlem.get(j)!=null){
							Classes z = mlem.get(j);
							if(z.room!=null){
								if(z.room.name.equals(this.room.name)){
									this.bookRoom(z.startHour, z.startMin, z.endHour, z.endMin, z.courseName, "", z.room);

								}
							}
						}
					}
				}
			}
		}
	}

	public String toString() {
		 return this.date;
	}



	/**
	 * Function to book room during the day
	 * @param startHour HH from HH:MM where HH denotes hours for starting time
	 * @param startMin  MM from HH:MM where HH denotes hours for starting time
	 * @param endHour	HH from HH:MM where HH denotes hours for ending time
	 * @param endMin	MM from HH:MM where HH denotes hours for ending time
	 * @param user		username who made the booking
	 * @param purpose	purpose of booking
	 * @param room		Room object which is to be booked
	 */
	public void bookRoom(String startHour,String startMin, String endHour, String endMin, String user, String purpose, Room room) {
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
				if(this.Status[i]==1) {
					check = 1;
					throw new AlreadyBookedException("");
				}
			}
			if(check==0) {
				for(int i = initialindex;i<=endindex;i++) {
					this.Status[i] = 1;

				}
			}
			String slot = startHour+":"+startMin+" - "+endHour+":"+endMin;
			/*System.out.println(slot);*/
			Booking book = new Booking(slot,user, purpose, room);
			booking.add(book);
		}
		catch(AlreadyBookedException e) {

		}

	}

	public ArrayList<Booking> getBooking(){
		return this.booking;
	}
	/**
	 * Function which returns the slot for room-free time which will be displayed on the screen under 'View Room'
	 * @return the slot
	 */
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

	/**
	 * Function to cancel a booking
	 * @param booking which is to be cancelled
	 */
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
/**
 * Exception class for Already Booked Room Situations
 * @author Rohan Chhokra
 *
 */
class AlreadyBookedException extends Exception {
	AlreadyBookedException(String s ){
		super(s);
	}
}
