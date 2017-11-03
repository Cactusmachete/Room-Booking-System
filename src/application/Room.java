package application;
import java.io.*;


public class Room implements Serializable{
	String name;
	int Capacity;
	int[] Monday=new int[48];
	int[] Tuesday= new int[48];
	int[] Wednesday = new int[48];
	int[] Thursday = new int[48];
	int[] Friday = new int[48];
	boolean status = false;
	public Room(String x,int y) {
		this.name = x;
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
	
	public static Room[] deserialize() {
		Room[] list = new Room[2];
		ObjectInputStream in1 = null;
		ObjectInputStream in2 = null;
		try {
			in1 = new ObjectInputStream(new FileInputStream("Room/c11.ser"));
			Room first = (Room)in1.readObject();
			in2 = new ObjectInputStream(new FileInputStream("Room/c01.ser"));
			Room second = (Room)in2.readObject();
			list[0] = first;
			list[1] = second;
			in1.close();
			in2.close();
		
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