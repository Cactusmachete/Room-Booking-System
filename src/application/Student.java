package application;
import java.util.*;
import java.io.*;
public class Student extends User {
	private static final long serialVersionUID = 3L;
	ArrayList<Course> CourseList = new ArrayList<Course>();
	Classes[] mon = new Classes[48];
	Classes[] tue = new Classes[48];
	Classes[] wed = new Classes[48];
	Classes[] thur = new Classes[48];
	Classes[] fri = new Classes[48];
	int numofrequest = 0;
	ArrayList<Request> requests = new ArrayList<Request>();
	public Student(String id, String pwd,String name) {
		super(id, pwd,name);
		File Folder = new File("Request/"+id);
		Folder.mkdir();
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


	public void AddCourse(Course c) throws ClassClashException {
		if(AddCoursehelper(mon,c,"Monday")) {
			if(AddCoursehelper(tue,c,"Tuesday")) {
				if(AddCoursehelper(wed,c,"Wednesday")) {
					if(AddCoursehelper(thur,c,"Thursday")) {
						if(AddCoursehelper(fri,c,"Friday")) {
							AddCoursehelpertwo(mon,c,"Monday");
							AddCoursehelpertwo(tue,c,"Tuesday");
							AddCoursehelpertwo(wed,c,"Wednesday");
							AddCoursehelpertwo(thur,c,"Thursday");
							AddCoursehelpertwo(fri,c,"Friday");
						}
					}
				}
			}
		}
	}


	public boolean AddCoursehelper(Classes[] henlo,Course C,String day) throws ClassClashException {
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
								if(henlo[j].courseName.equals(C.getName())==false){
									throw new ClassClashException("");
								}
							}
					}
			}
		}
		return true;
	}

	public void AddCoursehelpertwo(Classes[] henlo,Course C,String day) {
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
					for(int j = initialindex;j<endindex;j++) {
						if(henlo[j]!=null/* && henlo[j+1]!=null*/){
							if(henlo[j].toString.endsWith(innerhenlo.get(i).room_name)==false && henlo[j].isWhat==innerhenlo.get(i).isWhat){
								henlo[j].toString+=","+innerhenlo.get(i).room_name;
							}
						}
						else{
							henlo[j] = innerhenlo.get(i);
						}

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


	public void DropCoursehelper(Course c,Classes[] henlo) {
		for(int i = 0;i<henlo.length;i++) {
			if(henlo[i]!=null) {
				if(henlo[i].courseName.equals(c.Name)) {
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