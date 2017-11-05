package application;

public class Day {
	String month;
	String day;
	String year;
	int[] slots = new int[48];
	public Day(String day,String month,String year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public String toString() {
		 return day+"/"+month+"/"+year;
	}
}
