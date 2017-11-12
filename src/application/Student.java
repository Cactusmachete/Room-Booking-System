package application;
import java.util.*;
import java.io.*;
public class Student extends User {
	private static final long serialVersionUID = 3L;
	ArrayList<String> CourseList;
	ArrayList<Request> RequestList;
	int numofrequest = 0;
	public Student(String id, String pwd,String name) {
		super(id, pwd,name);
		File Folder = new File("Request/"+id);
		Folder.mkdir();
	}

	public void addcourse(String Coursename) {
		CourseList.add(Coursename);
	}

}