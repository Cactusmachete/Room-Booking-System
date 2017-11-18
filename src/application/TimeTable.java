package application;
/** The TimeTable Class
* takes the String objects for time, and Student's classes at that current time
* used in studentController
*/

public class TimeTable {
	String slot, mon, tue, wed, thu, fri;

	public TimeTable(String slot, String mon,String tue,String wed, String thu, String fri){
		this.slot = slot;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
	}

	public String getTime(){
		return this.slot;
	}

	public String getMon(){
		return this.mon;
	}

	public String getTue(){
		return this.tue;
	}

	public String getWed(){
		return this.wed;
	}

	public String getThu(){
		return this.thu;
	}

	public String getFri(){
		return this.fri;
	}

}
