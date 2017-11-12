package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class youSureController {

	public Label changeDate;
	public Button ok;

	@FXML
	public void initialize(){
		changeDate.setText(Main.date);
		 ok.setOnAction(arg0 ->{

			 Stage stage = (Stage) ok.getScene().getWindow();
			 stage.close();
         });
	}




}
