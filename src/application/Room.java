package application;
/**
 * <h1>The Room Class</h1>
 * Class which represents an object of type Room, name,slot,capacity,Dates whwich is a HashMap
 *
 * @author Rohan Chhokra
 *
 */
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
	/**
	 * Function to serialize a room in the file Room/Room.name.ser
	 * @param room which has to be serialized
	 * @throws IOException
	 */
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
			Day day = new Day(key, this);
			Dates.put(key,day);

		}
		return Dates.get(key);
	}

	public static void main() {
		String[] list = {"C01", "C02", "C03", "C04", "C11", "C12", "C13", "C14", "C21", "C22", "C23", "C24",
				"LR1", "LR2", "LR3", "S01", "S02"};
		for(int i=0; i<list.length; i++){
			Room room = new Room(list[i], 100);
			try {
				serialize(room);

			}
			catch(IOException e) {
				e.printStackTrace();
			}

		}

	}
	/**
	 * returns the room name
	 * @return room name
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * returns the room capacity
	 * @return room capacity
	 */
	public int getCapacity(){
		return this.Capacity;
	}
	/**
	 * returns the slots to be displayed
	 * @return the slots
	 */
	public String getSlot(){
		Day day = this.getInstance(Main.date);
		String slots = day.getSlots();
		return slots;
	}

	/**
	 * returns today's bookings
	 * @return arraylist of type Booking
	 */
	public ArrayList<Booking> getBooking(){
		Day day = this.getInstance(Main.date);
		return day.getBooking();
	}

	/**
	 * Function to book a room
	 * @param date String date
	 * @param fromHrs HH where HH:MM where HH denotes hours for starting time
	 * @param fromMins MM where HH:MM where HH denotes hours for starting time
	 * @param toHrs HH where HH:MM where HH denotes hours for ending time
	 * @param toMins MM where HH:MM where HH denotes hours for ending time
	 * @param user user who made the request
	 * @param purpose purpose of booking
	 */
	public void Book(String date, String fromHrs, String fromMins, String toHrs, String toMins, String user, String purpose){
		Day day = this.getInstance(date);
		day.bookRoom(fromHrs, fromMins, toHrs, toMins, user, purpose, this);
		day.getSlots();

	}
	/**
	 * Function deserialize all the rooms
	 * @return returns a list of all deserialized rooms
	 */
	public static Room[] deserialize() {
		Room[] list_o = new Room[17];
		ObjectInputStream in = null;
		try {
			File hello = new File("Room/");
			String[] list = hello.list();
			if(list.length>0){
				for(int i = 0;i<list.length;i++) {


						in = new ObjectInputStream(new FileInputStream("Room/"+list[i]));
						Room yay = (Room) in.readObject();
						list_o[i]=yay;
					}

				}

		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return list_o;

	}

	/**
	 * Function to cancel a booking
	 * @param date date string
	 * @param booking booking object to be cancelled
	 */
	public void cancelBooking(String date, Booking booking) {
		Day day = this.getInstance(date);
		day.cancelbooking(booking);

	}


}
