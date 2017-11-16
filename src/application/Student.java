package application;
import java.util.*;
import java.io.*;
public class Student extends User {
	private static final long serialVersionUID = 3L;
	ArrayList<String> CourseList;
	int numofrequest = 0;
	ArrayList<Request> requests = new ArrayList<Request>();
	public Student(String id, String pwd,String name) {
		super(id, pwd,name);
		File Folder = new File("Request/"+id);
		Folder.mkdir();
	}

	public void addcourse(String Coursename) {
		CourseList.add(Coursename);
	}

	public static void serialize(Student user) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 for(int i=0; i<user.requests.size(); i++){
					Request.serialize(user.requests.get(i));
				}
			 File inp = new File("Student/"+user.email_id+".ser");
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