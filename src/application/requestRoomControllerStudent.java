package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class requestRoomControllerStudent {
	static Room room;
	public Button requestYes, requestNo;

	 @FXML
	 public void initialize() {

		requestYes.setOnAction(arg0 -> {
				handleBookingAction(arg0);
			});

		requestNo.setOnAction(arg0 -> {
				handleCancelAction(arg0);
			});

	 }


	private void handleCancelAction(ActionEvent arg0) {
		Stage stage = (Stage) requestNo.getScene().getWindow();
		 stage.close();

	}

	private void handleBookingAction(ActionEvent arg0) {
		studentController.book(room);
		Stage stage = (Stage) requestYes.getScene().getWindow();
		stage.close();

	}


	}



