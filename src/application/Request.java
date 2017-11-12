package application;
import java.io.*;
import java.util.*;
public class Request implements Serializable {
	private static final long serialVersionUID = 56L;
	Student Requester;
	boolean RequestStatus;
	String purpose;
	int Capacity;
	public Request(Student Requester,String purpose,int capacity) {
		this.Requester = Requester;
		this.purpose = purpose;
		this.Capacity = capacity;
		Requester.RequestList.add(this);
		Requester.numofrequest++;
	}
	public static void serialize(Request request) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 File inp = new File("Request/"+request.Requester.email_id+"/"+request.Requester.numofrequest+".ser");
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
			 out.writeObject(request);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 out.close();
		 }
	 }
	public static ArrayList<Request> deserialize() {
		ArrayList<Request> adminrequestlist = new ArrayList<Request>();
		ObjectInputStream in = null;
		try {
			File hello = new File("Request/");
			String[] list = hello.list();
			for(int i = 0;i<list.length;i++) {
				File innerhello = new File("Reqest/"+list[i]);
				String[] innerlist = innerhello.list();
				for(int j = 0;j<innerlist.length;j++){
					in = new ObjectInputStream(new FileInputStream("Request/"+list[i]+j));
					Request yay = (Request) in.readObject();
					adminrequestlist.add(yay);
				}
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return adminrequestlist;

	}

}