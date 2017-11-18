package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Faculty extends User {
	private static final long serialVersionUID = 2L;
	public Faculty(String id, String pwd,String name) {
		super(id, pwd,name);

	}

	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this.email_id, "");
	}

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