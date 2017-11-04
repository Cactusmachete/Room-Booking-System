package application;

import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

    static Parent root;
    static nextScene scene;
    static Stage stage;
    static ArrayList<String> room_list = new ArrayList<String>(Arrays.asList("c01","c11"));
	 @Override
	    public void start(Stage primaryStage) throws Exception {

		    root= FXMLLoader.load(getClass().getResource("login.fxml"));
	        scene = new nextScene(root);
	        stage = primaryStage;
	        stage.setScene(scene);
	        stage.show();
	    }


	public static void main(String[] args) {
		launch(args);

	}
	
}


class nextScene extends Scene{

	public nextScene(Parent arg0) {
		super(arg0);

	}

	 public void change(String x) throws IOException{
		 Main.root= FXMLLoader.load(getClass().getResource(x+".fxml"));
	     Main.scene = new nextScene(Main.root);
	     Main.stage.setScene(Main.scene);
	     Main.stage.show();
	 }



}
