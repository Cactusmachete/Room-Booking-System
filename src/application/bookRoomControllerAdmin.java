package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class bookRoomControllerAdmin {
	static Room room;
	public Button bookYes, bookNo;
	public ChoiceBox<String> fromHrs, fromMins, toHrs, toMins;
	static ObservableList<String> Hrs = FXCollections.observableArrayList();
	static ObservableList<String> Mins = FXCollections.observableArrayList();

	 @FXML
	 public void initialize() {

		 /*Hrs.removeAll();
		 Hrs.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				 "14", "15", "16", "17", "18", "19", "20","21","22","23");*/

		 fromHrs.getItems().removeAll(fromHrs.getItems());
		 fromMins.getItems().removeAll(fromMins.getItems());
		 toHrs.getItems().removeAll(toHrs.getItems());
		 toMins.getItems().removeAll(toMins.getItems());

		 fromMins.getItems().addAll("00", "30");
		 toMins.getItems().addAll("00", "30");
		 fromHrs.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				 "14", "15", "16", "17", "18", "19", "20","21","22","23");
		 toHrs.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				 "14", "15", "16", "17", "18", "19", "20","21","22","23");



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
		adminController.user.bookRoom(room, Main.date, fromHrs.getValue(), fromMins.getValue(), toHrs.getValue(), toMins.getValue());
		adminController.book(room);
		Stage stage = (Stage) bookYes.getScene().getWindow();
		stage.close();

	}


	}



