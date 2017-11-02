package application;
import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final String email_id, password,name;

	public User(String id, String pwd,String name){
		this.email_id = id;
		this.password = pwd;
		this.name = name;
	}

	public Boolean validateCredentials(String id, String pwd){
		return (email_id.equals(id) && password.equals(pwd));
	}

	public void viewRoomAvail(){

	}

}
