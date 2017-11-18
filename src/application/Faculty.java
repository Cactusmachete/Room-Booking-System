package application;
/**
 * <h1> The Faculty Class </h1>
 * This class extends User class 
 * @author Rohan Chhokra
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Faculty extends User {
	private static final long serialVersionUID = 2L;
	public Faculty(String id, String pwd,String name) {
		super(id, pwd,name);

	}
	/**
	 * This is an Faculty specific function called bookRoom and does exactly what the function name denotes
	 *
	 * @param room 
	 * @param date Data for booking the room
	 * @param fromHrs HH where HH:MM where HH denotes hours for starting time
	 * @param fromMins MM where HH:MM where MM denotes hours for starting time
	 * @param toHrs HH where HH:MM where HH denotes hours for ending time
	 * @param toMins MM where HH:MM where MM denotes hours for ending time
	 */
	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this.email_id, "");
	}
	/**
	 * A serializing function for object of type Faculty.
	 * @param user, the user to be serialized.
	 * @throws IOException
	 */
	public static void serialize(Faculty user) throws IOException {
		ObjectOutputStream out = null;
		 try {
			 File inp = new File("Faculty/"+user.email_id+".ser");
			 inp.delete();
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
			 out.writeObject(user);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 if(out!=null){
				 out.close();
			 }
		 }
	}

}