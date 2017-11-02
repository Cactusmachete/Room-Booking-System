package application;

public class User {
	protected final String email_id, password;

	public User(String id, String pwd){
		this.email_id = id;
		this.password = pwd;
	}

	public Boolean validateCredentials(String id, String pwd){
		return (email_id.equals(id) && password.equals(pwd));
	}

	public void viewRoomAvail(){

	}

}
