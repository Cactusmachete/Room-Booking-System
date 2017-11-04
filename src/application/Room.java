package application;
import java.io.*;


public class Room implements Serializable{


	private static final long serialVersionUID = 10L;
	String name;
    String slot;
    String bookedBy;

	int Capacity;
	int[] Monday=new int[48];
	int[] Tuesday= new int[48];
	int[] Wednesday = new int[48];
	int[] Thursday = new int[48];
	int[] Friday = new int[48];
	Boolean status = false;

	public Room(String x,int y) {
		this.name = x;
		bookedBy = null;
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
		return (this.slot);
	}


	public Boolean isBooked(){
		return this.status;
	}

	public String BookedBy(){
		return this.bookedBy;
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


}
