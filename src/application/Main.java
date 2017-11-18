package application;
/**
 * <h1> The Main Class </h1>
 * The program is launched from here. 
 * @author Ahilya Sinha
 */


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	static Calendar c = Calendar.getInstance();
    static Parent root;
    static nextScene scene;
    static Stage stage;
    static Room[] list;
    static ArrayList<Course> course_list;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static LocalDateTime now = LocalDateTime.now();
    static String date =  dtf.format(now) ;


	 @Override
	    public void start(Stage primaryStage) throws Exception {

		    root= FXMLLoader.load(getClass().getResource("login.fxml"));
	        scene = new nextScene(root);
	        stage = primaryStage;
	        stage.setScene(scene);
	        stage.show();
	    }


	public static void main(String[] args) throws IOException {
		Room.main();
		list = Room.deserialize();
		Course.main();
		course_list = Course.deserialize();
		launch(args);
		for (int i=0; i<list.length; i++){
			Room.serialize(list[i]);
		}
	}

}
/**
 * <h1> Custom Scene Class </h1>
 * Custom Class with two new methods.
 * @author Ahilya Sinha
 */

class nextScene extends Scene{

	public nextScene(Parent arg0) {
		super(arg0);

	}
	/**
	 * This new method helps us change the fxml file currently being used in the program. i.e, 
	 * it changes the scene.
	 *
	 * @param x fileName (of fileName.fxml) to which the scene has to be changed.
 	*/
	 public void change(String x) throws IOException{
		 Main.root= FXMLLoader.load(getClass().getResource(x+".fxml"));
	     Main.scene = new nextScene(Main.root);
	     Main.stage.setScene(Main.scene);
	     Main.stage.show();
	 }

	 /**
	 * This new method helps us open a dialog.
	 *
	 * @param x fileName (of fileName.fxml) which the dialog box has to be set to.
 	 */
	 public void openDialog(String x) throws IOException{
		 final Stage dialog = new Stage();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.initOwner(Main.stage);
	        Parent root= FXMLLoader.load(getClass().getResource(x+".fxml"));
	        nextScene scene = new nextScene(root);
	        dialog.setScene(scene);
	        dialog.show();

	 }
	 /**
	 * This method opens the Calender from https://github.com/SirGoose3432 's repositories in a dialog box.
 	 */

	 public void showCal(){

	  final Stage dialog = new Stage();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.initOwner(Main.stage);
	        dialog.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
	        dialog.show();

	 }

}

