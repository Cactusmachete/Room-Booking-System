package application;

public class Admin extends User {
	private static final long serialVersionUID = 2L;
	public Admin(String id, String pwd,String name) {
		super(id, pwd,name);

	}

	public void cancelBooking(){

	}

	public void bookRoom(Room room){
		room.status = true;
		room.bookedBy = this.email_id;
	}



	/*public void acceptRequest(Request request){

	}

	public void rejectRequest(Request request){

	}
*/

}
