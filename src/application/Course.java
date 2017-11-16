package application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.ObjectInputStream;
import java.io.*;
import java.util.*;
public class Course implements Serializable {
	private static final long serialVersionUID = 456L;
	String ManEl;
	String Name;
	String Code;
	String Instructor;
	int Credits;
	String Acronym;
	String post_conditions;
	HashMap<String, ArrayList<Classes>> classes = new HashMap<String, ArrayList<Classes>>();
	String[] day = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

	public Course(String details) {
		String[] det  = details.split(",");
		this.ManEl = det[0];
		this.Name = det[1];
		this.Code = det[2];
		this.Instructor = det[3];
		this.Credits = Integer.parseInt(det[4]);
		this.Acronym = det[5];
		this.post_conditions = det[11];
		for(int i=0; i<5; i++){
			ArrayList<Classes> list = new ArrayList<Classes>();
			if(!(det[i+6].equals("-"))){
				String[] classes = det[i+6].split("\\$");
				for(int j=0; j<classes.length; j++){
					if(classes[j].isEmpty()){
						//input is a tutorial
						String[] x = classes[j+1].split("-");
						Classes tut = new Classes(this.Code, classes[j+2], 1, x[0], x[1]);
						list.add(tut);
						j=j+2;
					}

					else if(classes[j].equals("/")){
						//input is a lab
						String[] x = classes[j+1].split("-");
						Classes lab = new Classes(this.Code, classes[j+2], 2, x[0], x[1]);
						list.add(lab);
						j=j+2;
					}
					else{
						//input is a lecture
						String[] x = classes[j].split("-");
						Classes lecture = new Classes(this.Code, classes[j+1], 0, x[0], x[1]);
						list.add(lecture);
						j=j+1;
					}
				}
				this.classes.put(day[i], list);
			}
		}

/*
		for(int i=0; i<5; i++){
			ArrayList<Classes> list = this.classes.get(day[i]);
			System.out.println(day[i]);
			if (list!=null){
				for(int j=0; j<list.size(); j++){
					System.out.println(list.get(j));
				}
			}
			System.out.println();
		}*/
	}

	public static void serialize(Course course) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 File inp = new File("Course/"+course.Code+".ser");
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


	public static void main() throws IOException  {
		try {
			String filename = "courses.csv";
			List<String> courselines = Files.readAllLines(Paths.get(filename));
			for(int i = 0;i<courselines.size();i++) {
				Course output = new Course(courselines.get(i));
				serialize(output);
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

	public String toString(){
		return (this.ManEl+"\n"+this.Instructor+"\n"+this.post_conditions);
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