package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class cancelBookingControllerFaculty {
	public Button cancelYes;
	public Button cancelNo;
	static Room room;

	@FXML
	 public void initialize() {

		 cancelYes.setOnAction(arg0 -> {
				handleCancelAction(arg0);
			});


		 cancelNo.setOnAction(arg0 -> {
				handleNoAction(arg0);
			});


	 }

	 private void handleCancelAction(ActionEvent arg0) {

		facultyController.cancel(room);
		Stage stage = (Stage) cancelYes.getScene().getWindow();
		stage.close();



	 }

	 private void handleNoAction(ActionEvent arg0) {

		 Stage stage = (Stage) cancelNo.getScene().getWindow();
		 stage.close();

	 }





}
