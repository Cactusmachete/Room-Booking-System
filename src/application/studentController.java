package application;

import java.io.IOException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class studentController{

	static String date = "06/11/2017";
	public TableView<Room> availTable, bookedTable;
	public TableColumn<Room, String> availRoomNum, availSlot, bookedRoom, bookedPurpose, bookedSlot;
	public TableColumn<Room, Integer>availCap, bookedCap;
	public TableColumn <Room, Room> availBook, bookedCancel;
	Room[] list = Main.list;
	static Student user = (Student) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Room> booked_data = FXCollections.observableArrayList();



	@FXML
	public void initialize(){

		availBook.setCellValueFactory(
	            param -> new ReadOnlyObjectWrapper<>(param.getValue())
	        );
	        availBook.setCellFactory(param -> new TableCell<Room, Room>() {
	            private final Button bookButton = new Button("Book");

	            @Override
	            protected void updateItem(Room room, boolean empty) {
	                super.updateItem(room, empty);

	                if (room == null) {
	                    setGraphic(null);
	                    return;
	                }

	                setGraphic(bookButton);
	                bookButton.setOnAction(arg0 ->{
	                	try {
							handleRequestAction(arg0, room);

						} catch (IOException e) {
							e.printStackTrace();
						}

	                });

	            }
	        });


        bookedCancel.setCellValueFactory(
            param -> new ReadOnlyObjectWrapper<>(param.getValue())
        );
        bookedCancel.setCellFactory(param -> new TableCell<Room, Room>() {
            private final Button deleteButton = new Button("Cancel");

            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);

                if (room == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(arg0 ->{
                	try {
						handleCancelAction(arg0, room);

					} catch (IOException e) {
						e.printStackTrace();
					}

                });

            }

        });



			for(int i=0; i<list.length; i++){
				if (!(list[i].isBooked(date))){
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

	}



	private void handleRequestAction(ActionEvent arg0, Room room) throws IOException {
		Main.scene.openDialog("requestRoomStudent");
		requestRoomControllerStudent.room = room;

	}

	private void handleCancelAction(ActionEvent arg0, Room room) throws IOException{
		Main.scene.openDialog("cancelBookingStudent");
		cancelBookingControllerStudent.room = room;
	}

	static void cancel(Room room){

		user.cancelBooking(room, date);
		booked_data.remove(room);
		avail_data.add(room);
	}

	static void book(Room room){
		room.Book(date, user.email_id, "" );
		avail_data.remove(room);
		booked_data.add(room);
	}

}
