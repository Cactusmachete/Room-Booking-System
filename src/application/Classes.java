package application;
/**
 * <h1> The Classes Class </h1>
 * This class is used in the Course class and the Student class
 * Stores course acronym in courseName
 * @author Ahilya Sinha
 */

import java.io.Serializable;

public class Classes implements Serializable {

	private static final long serialVersionUID = 33L;
	String courseName;
	String room_name;
	Room room;
	int isWhat;
	String timeFrom, timeTo, startHour="", startMin="", endHour="", endMin="";
	String[] is = {"Class", "Tut", "Lab"};
	String onDay;
	String toString;


	public Classes(String name, String room_name, int isWhat, String timeFrom, String timeTo){
		this.courseName = name;
		this.room_name = room_name;
		this.isWhat = isWhat;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		String[] x = timeFrom.split(":");
		int time = Integer.parseInt(x[0]);
		if(time>7 && time<10){
			startHour = "0";
		}
		else if(time<7){
			time+=12;
		}
		startHour += String.valueOf(time);
		startMin = x[1];

		x = timeTo.split(":");
		time = Integer.parseInt(x[0]);
		if(time>7 && time<10){
			endHour = "0";
		}
		else if(time<7){
			time+=12;
		}
		endHour += String.valueOf(time);
		endMin = x[1];

		for(int i=0; i<Main.list.length; i++){
			if(Main.list[i].getName().equals(this.room_name)){
				this.room = Main.list[i];
			}
		}
		this.toString = this.courseName+" "+ is[isWhat]+" "+this.room_name;
	}
	 /**
     	* Gives the String representation of the class represented by String toStrin
     	*/
	public String toString(){
		return toString;
	}

}
