package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class studentController{
	public TextField capacity, prefRoom, date;
	public TextArea purpose;
	public Button request;
	public ChoiceBox<String> timeFrom, timeTo;
	public TableView<Room> availTable, bookedTable;
	public TableColumn<Room, String> availRoomNum, availSlot, bookedRoom, bookedPurpose, bookedSlot;
	public TableColumn<Room, Integer>availCap, bookedCap;
	Room[] list = Main.list;
	Student user = (Student) loginController.user;
	ObservableList<Room> avail_data = FXCollections.observableArrayList();
	ObservableList<Room> booked_data = FXCollections.observableArrayList();



	@FXML
	public void initialize(){


			for(int i=0; i<list.length; i++){
				if (!(list[i].isBooked())){
					avail_data.add(list[i]);
				}

				if(list[i].getName().equals(user.email_id)){
					booked_data.add(list[i]);
				}
			}


	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        availTable.setItems(avail_data);

	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        bookedPurpose.setCellValueFactory(new PropertyValueFactory<Room, String>("purpose"));
	        bookedTable.setItems(booked_data);


	        request.setOnAction(arg0 -> {
				handleRequestAction(arg0);
			});



	}



	private void handleRequestAction(ActionEvent arg0) {


	}

}