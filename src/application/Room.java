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
	
	public static void main(String[] args) {
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
}