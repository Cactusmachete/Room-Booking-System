package application;
import java.io.*;
import java.util.*;


public class Room implements Serializable{


	private static final long serialVersionUID = 10L;
	String name;
    String slot;


	int Capacity;
	Map<String, Day> Dates = new HashMap<String, Day>();
	Boolean status = false;

	public Room(String x,int y) {
		this.name = x;
		this.slot = "all";
		this.Capacity = y;
	}

	public static void serialize(Room room) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 File inp = new File("Room/"+room.name+".ser");
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
			 out.writeObject(room);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 out.close();
		 }
	 }


	public Day getInstance(String key){
		if(Dates.containsKey(key)==false){
			Day day = new Day(key);
			Dates.put(key,day);

		}
		return Dates.get(key);
	}

	public static void main() {
		Room first = new Room("C01",120);
		Room second = new Room("C21",100);
		try {
			serialize(first);
			serialize(second);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

	public String getName(){
		return this.name;
	}

	public int getCapacity(){
		return this.Capacity;
	}

	public String getSlot(){
		Day day = this.getInstance(Main.date);
		String slots = day.getSlots();
		return slots;
	}


	public ArrayList<Booking> getBooking(){
		Day day = this.getInstance(Main.date);
		return day.getBooking();
	}

	public void Book(String date, String fromHrs, String fromMins, String toHrs, String toMins, User user, String purpose){
		Day day = this.getInstance(date);
		day.bookRoom(fromHrs, fromMins, toHrs, toMins, user, purpose, this);
		day.getSlots();

	}

	public static Room[] deserialize() {
		Room[] list = new Room[2];
		ObjectInputStream in1 = null;
		try {
			in1 = new ObjectInputStream(new FileInputStream("Room/C21.ser"));
			Room first = (Room)in1.readObject();
			in1.close();
			in1 = new ObjectInputStream(new FileInputStream("Room/C01.ser"));
			Room second = (Room)in1.readObject();
			list[0] = first;
			list[1] = second;
			in1.close();

		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void cancelBooking(String date, Booking booking) {
		Day day = this.getInstance(date);
		day.cancelbooking(booking);

	}


}