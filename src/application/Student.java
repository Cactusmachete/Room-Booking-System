package application;
import java.util.*;
import java.io.*;
public class Student extends User {
	private static final long serialVersionUID = 3L;
	ArrayList<String> CourseList;
	Course[] mon = new Course[48];
	Course[] tue = new Course[48];
	Course[] wed = new Course[48];
	Course[] thur = new Course[48];
	Course[] fri = new Course[48];
	int numofrequest = 0;
	ArrayList<Request> requests = new ArrayList<Request>();
	public Student(String id, String pwd,String name) {
		super(id, pwd,name);
		File Folder = new File("Request/"+id);
		Folder.mkdir();
	}

	public void addcourse(String Coursename) {
		CourseList.add(Coursename);
	}

	public static void serialize(Student user) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 for(int i=0; i<user.requests.size(); i++){
					Request.serialize(user.requests.get(i));
				}
			 File inp = new File("Student/"+user.email_id+".ser");
			 inp.delete();
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
			 out.writeObject(user);
		 }

		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 if(out!=null){
				 out.close();
			 }

		 }
	 }
	
	public void AddCourse(Course c) {
		if(AddCoursehelper(mon,c,"Monday")) {
			if(AddCoursehelper(tue,c,"Tuesday")) {
				if(AddCoursehelper(wed,c,"Wednesday")) {
					if(AddCoursehelper(thur,c,"Thursday")) {
						if(AddCoursehelper(fri,c,"Friday")) {
							AddCoursehelpertwo(mon,c,"Monday");
							AddCoursehelper(tue,c,"Tuesday");
							AddCoursehelper(wed,c,"Wednesday");
							AddCoursehelper(thur,c,"Thursday");
							AddCoursehelper(fri,c,"Friday");
						}
					}
				}
				
			}
		}
	}
	
	public boolean AddCoursehelper(Course[] henlo,Course C,String day) {
		ArrayList<Classes> innerhenlo = C.classes.get(day);
		if(innerhenlo!=null) {
			for(int i = 0;i<innerhenlo.size();i++) {
				Classes input = innerhenlo.get(i);
					int initialhour = Integer.valueOf(input.startHour);
					int endhour = Integer.valueOf(input.endHour);
					int initialindex = 2*initialhour;
					int endindex = 2*endhour;
					if(Integer.valueOf(input.startMin)==30) {
						initialindex = initialindex + 1;
					}
					if(Integer.valueOf(input.endMin)==30) {
						endindex = endindex + 1;
					}
				
						
						for(int j = initialindex;j<=endindex;j++) {
							if(henlo[j]!=null) {
								return false;
							}
						}
				
				
					
					
				
				
			}
		}
		
		return true;
	}
	
	public void AddCoursehelpertwo(Course[] henlo,Course C,String day) {
		ArrayList<Classes> innerhenlo = C.classes.get(day);
		if(innerhenlo!=null) {
			for(int i = 0;i<innerhenlo.size();i++) {
				Classes input = innerhenlo.get(i);
				if(input.startHour!=null) {
					int initialhour = Integer.valueOf(input.startHour);
					int endhour = Integer.valueOf(input.endHour);
					int initialindex = 2*initialhour;
					int endindex = 2*endhour;
					if(Integer.valueOf(input.startMin)==30) {
						initialindex = initialindex + 1;
					}
					if(Integer.valueOf(input.endMin)==30) {
						endindex = endindex + 1;
					}
					for(int j = initialindex;j<=endindex;i++) {
						henlo[j] = C;
					}
						
				}
				
					
				
				
			}
		}
		
	}
	
	public void DropCourse(Course c) {
		DropCoursehelper(c,mon);
		DropCoursehelper(c,tue);
		DropCoursehelper(c,wed);
		DropCoursehelper(c,thur);
		DropCoursehelper(c,fri);
	}
	
	public void DropCoursehelper(Course c,Course[] henlo) {
		for(int i = 0;i<henlo.length;i++) {
			if(henlo[i]!=null) {
				if(henlo[i].Name.equals(c.Name)) {
					henlo[i] = null;
				}
			}
			
		}
	}

}

class ClassClashException extends Exception {
	ClassClashException(String s ){
		super(s);
	}
}