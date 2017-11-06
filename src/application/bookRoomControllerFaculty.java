package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class bookRoomControllerFaculty {
	static Room room;
	public Button bookYes, bookNo;

	 @FXML
	 public void initialize() {

		 bookYes.setOnAction(arg0 -> {
				handleBookingAction(arg0);
			});

		 bookNo.setOnAction(arg0 -> {
				handleCancelAction(arg0);
			});

	 }

	private void handleCancelAction(ActionEvent arg0) {
		Stage stage = (Stage) bookNo.getScene().getWindow();
		 stage.close();

	}

	private void handleBookingAction(ActionEvent arg0) {
		facultyController.book(room);
		Stage stage = (Stage) bookYes.getScene().getWindow();
		stage.close();

	}


	}



