package application;

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


	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this.email_id, "");
	}

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



	/*public void acceptRequest(Request request){

	}

	public void rejectRequest(Request request){

	}
*/

}