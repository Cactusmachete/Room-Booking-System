package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {

    static Parent root;
    static nextScene scene;
    static Stage stage;
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


	 public void openDialog(String x) throws IOException{
		 final Stage dialog = new Stage();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.initOwner(Main.stage);
	        Parent root= FXMLLoader.load(getClass().getResource(x+".fxml"));
	        nextScene scene = new nextScene(root);
	        Stage stage = Main.stage;
	        dialog.setScene(scene);
	        dialog.show();

	 }



}
