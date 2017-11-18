package application;
/**
 * <h1> The Request class </h1>
 * This class represents an object of type Request. It'll be instantianted everytime a Student requests
 * for a room booking. Further this Request will be accepted or rejected by the admin
 * Requester- the one who requested,Request status - accepted/rejected/cancelled/deleted,purpose- purpose string,
 * @author Rohan Chhokra
 */
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
	/**
	 * returns the status
	 * @return whether it has been accepted or not
	 */
	public String getStatus(){
		return status[RequestStatus];
	}
	/**
	 * returns the name
	 * @return the name of the room
	 */
	public String getName(){
		return this.room;
	}
	/**
	 * returns the purpose
	 * @return the purpose of booking
	 */
	public String getPurpose(){
		return this.purpose+" - "+Requester.email_id;
	}
	/**
	 * returns the capacity of room
	 * @return the capacity of room
	 */
	public Integer getCapacity(){
		return this.Capacity;
	}
	/**
	 * returns the slot
	 * @return the time slot
	 */
	public String getSlot(){
		String slot = fromHrs+":"+fromMins+" - "+toHrs+":"+toMins+" on "+this.date;
		return slot;
	}
	/**
	 * returns the date
	 * @return the date
	 */
	public String getDateMade(){
		return this.date_made;
	}




	/**
	 * function for serializing the object of type Request
	 * @param request paremeter which is to be serialized
	 * @throws IOException
	 */
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
	/**
	 * Function for deserializing all the requests for admin's access
	 * @return ArrayList<Request> for admin
	 */
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
	/**
	 * Deserializing requests for student's access(only his/her own requests)
	 * @param email_id for which the requests are to be deserialized
	 * @return ArrayList<Request> for student's access
	 */
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