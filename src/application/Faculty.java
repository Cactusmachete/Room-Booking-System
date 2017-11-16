package application;

public class Faculty extends User {
	private static final long serialVersionUID = 2L;
	public Faculty(String id, String pwd,String name) {
		super(id, pwd,name);

	}

	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this.email_id, "");
	}

}