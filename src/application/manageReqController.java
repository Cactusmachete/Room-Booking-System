package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class manageReqController {
	public Button accept, reject;
	public ChoiceBox<String> room;
	public Label error;
	static ObservableList<String> room_data = FXCollections.observableArrayList();
	static Request request;

	@FXML
	public void initialize(){

		for(int i=0; i<Main.list.length; i++){
			room_data.add(Main.list[i].getName());
			System.out.println(Main.list[i]);
		}

		room.getItems().removeAll();
		room.getItems().addAll(room_data);
		room.getSelectionModel().select("");

		error.setVisible(false);

		accept.setOnAction(arg0 ->{
        	handleAcceptAction(arg0);
        });
		reject.setOnAction(arg0 ->{
        	handleRejectAction(arg0);
        });

	}

	private void handleAcceptAction(ActionEvent arg0) {
		if(room.getValue().isEmpty()){
			error.setVisible(true);
		}

		else{
			error.setVisible(false);
			Room roomGiven = null;
			for(int i=0; i<Main.list.length; i++){
				if(Main.list[i].getName().equals(room.getValue())){
					roomGiven=Main.list[i];
				}
			}
			String slot = request.getSlot();
			roomGiven.Book(request.date, slot.substring(0,2), slot.substring(3,5), slot.substring(8,10),
					slot.substring(11,13), request.Requester.email_id, request.getPurpose().split(" - ")[0]);

			adminController.book(roomGiven);
			request.RequestStatus=1;
			adminController.req_data.remove(request);
			Stage stage = (Stage) reject.getScene().getWindow();
			stage.close();
		}

	}

	private void handleRejectAction(ActionEvent arg0) {
		request.RequestStatus=2;
		adminController.req_data.remove(request);
		Stage stage = (Stage) reject.getScene().getWindow();
		stage.close();

	}

}
