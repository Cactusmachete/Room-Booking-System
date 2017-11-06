package application;

public class Faculty extends User{
	private static final long serialVersionUID = 4L;
	public Faculty(String id, String pwd,String name) {
		super(id,pwd,name);
	}

	public void bookRoom(Room room, String date){
		room.Book(date, this.email_id, "" );
	}
}