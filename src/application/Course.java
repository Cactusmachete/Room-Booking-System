package application;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
		Course first = new Course("Mandatory,Discrete Mathematics,CSE121,Dhongoon Chang,4,DM,-,1:30-3:00$C21,$4:00-5:30$S01,-,11:30-1:00$C21$$4:00-5:30$LR1$$4:00-5:30$LR2$$4:00-5:30$LR3, mlem");
		Course second = new Course("Mandatory,Advanced Programming,CSE201,Vivek Kumar,4,AP,11:00-12:00$C21$$12:00-1:00$LR1$$12:00-1:00$LR2$$12:00-1:00$S01$$12:00-1:00$S02,-,11:00-12:00$C21,11:00-12:00$C21$/$12:00-1:00$L21$/$12:00-1:00$L22$/$12:00-1:00$L23,-, nomlem");
		try {
			serialize(first);
			serialize(second);
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