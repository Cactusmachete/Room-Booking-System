package application;

public class Admin extends User {
	private static final long serialVersionUID = 2L;
	public Admin(String id, String pwd,String name) {
		super(id, pwd,name);

	}

	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this, "");
	}



	/*public void acceptRequest(Request request){

	}

	public void rejectRequest(Request request){

	}
*/

}