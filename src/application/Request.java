package application;
import java.io.*;
import java.util.*;
public class Request implements Serializable {
	private static final long serialVersionUID = 56L;
	Student Requester;
	int RequestStatus =0; //0 = pending, 1 = accepted, 2 = rejected, 3 = cancelled, 4 = to be deleted
	String purpose;
	int Capacity;
	String room="";
	String date="";
	String date_made;
	String fromHrs, fromMins, toHrs, toMins;
	String[] status={"Pending", "Accepted", "Rejected", "Cancelled", "Deleted"};
	public Request(Student Requester,String purpose,int capacity, String room, String fromHrs, String fromMins, String toHrs, String toMins) {
		this.Requester = Requester;
		this.purpose = purpose;
		this.Capacity = capacity;
		this.room=room;
		this.fromHrs = fromHrs;
		this.fromMins = fromMins;
		this.toHrs = toHrs;
		this.toMins = toMins;
		this.date=Main.date;
		this.date_made=Main.dtf.format(Main.now);

	}

	public String getStatus(){
		return status[RequestStatus];
	}

	public String getName(){
		return this.room;
	}

	public String getPurpose(){
		return this.purpose+" - "+Requester.email_id;
	}

	public Integer getCapacity(){
		return this.Capacity;
	}

	public String getSlot(){
		String slot = fromHrs+":"+fromMins+" - "+toHrs+":"+toMins+" on "+this.date;
		return slot;
	}

	public String getDateMade(){
		return this.date_made;
	}





	public static void serialize(Request request) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 File inp = new File("Request/"+request.Requester.email_id+"/"+request.Requester.numofrequest+".ser");
			 if(request.RequestStatus!=4){
				 inp.delete();
				 inp.createNewFile();
				 out = new ObjectOutputStream(new FileOutputStream(inp, false));
				 out.writeObject(request);
			 }
			 else{
				 inp.delete();
			 }

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

	public static ArrayList<Request> deserialize1() {
		ArrayList<Request> adminrequestlist = new ArrayList<Request>();
		ObjectInputStream in = null;
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Date startDate, endDate;
		long startTime, endTime, diffDays, diffTime;
		try {
			File hello = new File("Request/");
			String[] list = hello.list();
			for(int i = 0;i<list.length;i++) {
				File innerhello = new File("Request/"+list[i]);
				String[] innerlist = innerhello.list();
				if(innerlist.length>0){
					for(int j = 0;j<innerlist.length;j++){
						in = new ObjectInputStream(new FileInputStream("Request/"+list[i]+"/"+innerlist[j]));
						Request yay = (Request) in.readObject();
						start.set(Integer.parseInt(yay.date_made.substring(6,10)),
								Integer.parseInt(yay.date_made.substring(3,5)),
								Integer.parseInt(yay.date_made.substring(1,2)));
						end.set(Integer.parseInt(Main.date.substring(6,10)),
								Integer.parseInt(Main.date.substring(3,5)),
								Integer.parseInt(Main.date.substring(1,2)));
						startDate = start.getTime();
						endDate = end.getTime();
						startTime = startDate.getTime();
						endTime = endDate.getTime();
						diffTime = endTime - startTime;
						diffDays = (diffTime / (1000 * 60 * 60 * 24));
						if(diffDays>5 && yay.RequestStatus==0){
							yay.RequestStatus = 2;
						}
						adminrequestlist.add(yay);
					}
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

	public static ArrayList<Request> deserialize2(String email_id) {
		ArrayList<Request> studentrequestlist = new ArrayList<Request>();
		ObjectInputStream in = null;
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Date startDate, endDate;
		long startTime, endTime, diffDays, diffTime;
		try {
				File innerhello = new File("Request/"+email_id);
				String[] innerlist = innerhello.list();
				if(innerlist.length>0){
					for(int j = 0;j<innerlist.length;j++){
						in = new ObjectInputStream(new FileInputStream("Request/"+email_id+"/"+innerlist[j]));
						Request yay = (Request) in.readObject();
						start.set(Integer.parseInt(yay.date_made.substring(6,10)),
								Integer.parseInt(yay.date_made.substring(3,5)),
								Integer.parseInt(yay.date_made.substring(1,2)));
						end.set(Integer.parseInt(Main.date.substring(6,10)),
								Integer.parseInt(Main.date.substring(3,5)),
								Integer.parseInt(Main.date.substring(1,2)));
						startDate = start.getTime();
						endDate = end.getTime();
						startTime = startDate.getTime();
						endTime = endDate.getTime();
						diffTime = endTime - startTime;
						diffDays = (diffTime / (1000 * 60 * 60 * 24));
						if(diffDays>5 && yay.RequestStatus==0){
							yay.RequestStatus = 2;
						}
						studentrequestlist.add(yay);
					}
				}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return studentrequestlist;

	}

}