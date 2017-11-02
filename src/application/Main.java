package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	 @Override
	    public void start(Stage primaryStage) throws Exception {

	        // just load fxml file and display it in the stage:

		    Parent root= FXMLLoader.load(getClass().getResource("signup.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }


	public static void main(String[] args) {
		launch(args);
	}
}
