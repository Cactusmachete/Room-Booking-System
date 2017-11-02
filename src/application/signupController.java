package application;



import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class signupController {
	public ChoiceBox<String> choices;
	public TextField email, name;
	public Label error;
	public PasswordField password;
	public Button button;
	public String a = "";


	@FXML
	public void initialize() {
	    choices.getItems().removeAll(choices.getItems());
	    choices.getItems().addAll("Admin", "Faculty", "Student");
	    choices.getSelectionModel().select("Student");
	    button.setOnAction(this::handleButtonAction);
	    error.setVisible(false);
	}

	 private void handleButtonAction(ActionEvent event) {

		 if(name.getText().equals("") || email.getText().equals("") || password.getText().equals("") ||choices.getValue().equals("")){
			 error.setVisible(true);
		 }
		 else if(email.getText().endsWith("@iiitd.ac.in")==false){
			 error.setVisible(true);
		 }

		 else{
			 error.setVisible(false);
			 a=a+name.getText()+","+email.getText()+","+password.getText()+","+choices.getValue();
		 }

	 }
}
