package application;

public class Faculty extends User{
	private static final long serialVersionUID = 4L;
	public Faculty(String id, String pwd,String name) {
		super(id,pwd,name);
	}

	public void bookRoom(Room room){
		room.status = true;
		room.bookedBy = this.email_id;
	}
}
