package application;
/**
 * <h1> The Admin Class </h1>
 * This class extends User class 
 * requests in an ArrayList<Request which stores the Request the admin has received from the students
 * @author Rohan Chhokra
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Admin extends User {
	private static final long serialVersionUID = 2L;
	static ArrayList<Request> requests = new ArrayList<Request>();
	public Admin(String id, String pwd,String name) {
		super(id, pwd,name);

	}

	/**
	 * This is an Admin specific function called bookRoom and does exactly what the function name denotes
	 *
	 * @param room 
	 * @param date Data for booking the room
	 * @param fromHrs HH from HH:MM where HH denotes hours for starting time
	 * @param fromMins MM from HH:MM where MM denotes hours for starting time
	 * @param toHrs HH from HH:MM where HH denotes hours for ending time
	 * @param toMins MM from HH:MM where MM denotes hours for ending time
	 */
	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this.email_id, "");
	}
    /**
     * Serializes Admin class into a file at Admin/Admin.email_id
     * @param user Serializes the user passed on as argument
     * @throws IOException
     */
	public static void serialize(Admin user) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 for(int i=0; i<requests.size(); i++){
					Request.serialize(requests.get(i));
				}
			 File inp = new File("Admin/"+user.email_id+".ser");
			 inp.delete();
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
			 out.writeObject(user);
		 }

		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 out.close();
		 }
	 }



	

}