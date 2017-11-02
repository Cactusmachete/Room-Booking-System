package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class signupController {
	public ChoiceBox<String> choices;

	@FXML
	public void initialize() {
	    choices.getItems().removeAll(choices.getItems());
	    choices.getItems().addAll("Admin", "Faculty", "Student");
	    choices.getSelectionModel().select("Student");
	}
}
