package application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.*;
public class Course implements Serializable {
	private static final long serialVersionUID = 456L;
	String ManEl;
	String Name;
	String Code;
	String Instructor;
	int Credits;
	String Acronym;
	String Room;
	int[] Monday = new int[48];;
	int[] Tuesday = new int[48];
	int[] Wedneday = new int[48];
	int[] Thursday = new int[48];
	int[] Friday = new int[48];
	boolean istut;
	boolean islab;
	String tut;
	String lab; 
	ArrayList<String> PostConditions;
	public Course(String a,String b,String c,String d,int e,String f,String g,boolean h,boolean i) {
		this.ManEl = a;
		this.Name = b;
		this.Code = c;
		this.Instructor = d;
		this.Credits = e;
		this.Acronym = f;
		this.Room = g;
		this.istut = h;
		this.islab = i;
	}
	public static void serialize(Course course) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 File inp = new File("Course/"+course.Name+".ser");
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
			 out.writeObject(course);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 out.close();
		 }
	 }
	
	public static ArrayList<Course> deserialize() {
		ArrayList<Course> courselist = new ArrayList<Course>();
		ObjectInputStream in = null;
		try {
			File hello = new File("Course/");
			String[] list = hello.list();
			for(int i = 0;i<list.length;i++) {
				in = new ObjectInputStream(new FileInputStream("Course/"+list[i]));
				Course input = (Course) in.readObject();
				courselist.add(input);
			}
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return courselist;
	}
}
