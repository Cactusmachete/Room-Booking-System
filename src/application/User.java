package application;
/**
* <h1> The User class <h1>
* This class will be further extended to the three 
* types of Users-> Student,Admin and Faculty. It implements Serializable
* hence all the types of Users extending User class are Serializable as well
* email_id,password and name are a User's credentials as the name suggests
* @author Rohan Chhokra
* 
*/
import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final String email_id, password,name;
	
	public User(String id, String pwd,String name){
		this.email_id = id;
		this.password = pwd;
		this.name = name;
	}
	/**
	 * This function is used to validate credentials given as an input
	 * during the login action.
	 * @param id email id input given during the login session
	 * @param pwd password input given during the login session
	 * @return this is a boolean return representing whether
	 * the credentials match or not
	 */
	public Boolean validateCredentials(String id, String pwd){
		return (email_id.equals(id) && password.equals(pwd));
	}

	/**
	 * This function is used to cancel a booking being done by the User.
	 * @param room - the room which needs to be cancelled
	 * @param date - the date of booking
	 * @param booking -  the object of type Booking which has to
	 * be cancelled 
	 */
	public void cancelBooking(Room room, String date, Booking booking){
		room.cancelBooking(date, booking);

	}

}