package application;
/**
 * <h1> The Faculty Class </h1>
 * This class extends User class 
 * @author Rohan Chhokra
 */
public class Faculty extends User {
	private static final long serialVersionUID = 2L;
	public Faculty(String id, String pwd,String name) {
		super(id, pwd,name);

	}
	/**
	 * This is an Faculty specific function called bookRoom and does exactly what the function name denotes
	 *
	 * @param room 
	 * @param date Data for booking the room
	 * @param fromHrs HH where HH:MM where HH denotes hours for starting time
	 * @param fromMins MM where HH:MM where MM denotes hours for starting time
	 * @param toHrs HH where HH:MM where HH denotes hours for ending time
	 * @param toMins MM where HH:MM where MM denotes hours for ending time
	 */
	public void bookRoom(Room room, String date, String fromHrs, String fromMins, String toHrs, String toMins){
		room.Book(date,fromHrs, fromMins, toHrs, toMins, this.email_id, "");
	}

}