package application;
import java.util.*;

public class Course {
	String name;
	ArrayList<String> postcond;
	ArrayList<String> prereq;
	String instructor;
	String[] timetable;
	public Course(String name,ArrayList<String> postcond,ArrayList<String> prereq,String instructor,String[] timetable) {
		this.name = name;
		this.postcond = postcond;
		this.prereq = prereq;
		this.instructor = instructor;
		this.timetable = timetable;
	}
}
