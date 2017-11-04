package application;

import java.io.IOException;

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
    User meh;
    static Room[] list;

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
		launch(args);
		for (int i=0; i<list.length; i++){
			Room.serialize(list[i]);
		}


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
