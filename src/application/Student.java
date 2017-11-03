package application;
import java.util.*;
public class Student extends User {
	private static final long serialVersionUID = 3L;
	ArrayList<String> CourseList;
	public Student(String id, String pwd,String name) {
		super(id, pwd,name);
	}

	public void addcourse(String Coursename) {
		CourseList.add(Coursename);
	}
	
	public void bookroom(Room r) {
		r.status = true;
	}


}



