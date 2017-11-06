package application;

import java.io.IOException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class facultyController {
	public TextField availDate;
	public Button availGo;
	public TableView<Room> availTable, bookedTable;
	public TableColumn<Room, String> availRoomNum, availSlot, bookedRoom, bookedSlot;
	public TableColumn<Room, Integer>availCap, bookedCap;
	public TableColumn<Room,Room> bookedCancel, availBook;
	public static String date="06/11/2017";

	Room[] list = Main.list;
	static Faculty user = (Faculty) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Room> booked_data = FXCollections.observableArrayList();





	    @FXML
	    public void initialize() {

	    	availGo.setOnAction(arg0 ->{
            	handleGoAction(arg0);

            });

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
								handleBookingAction(arg0, room);

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
				if (list[i].isBooked(date)==false){
					avail_data.add(list[i]);
				}
				if(list[i].BookedBy(date).equals(user.email_id)==true){
					booked_data.add(list[i]);
				}
			}

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));


	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));





	        availTable.setItems(avail_data);
	        bookedTable.setItems(booked_data);


	}


	    private void handleGoAction(ActionEvent arg0) {
	    	avail_data.clear();
			date = availDate.getText();
			for(int i=0; i<list.length; i++){
				if (!(list[i].isBooked(date))){
					avail_data.add(list[i]);
				}
				if(list[i].BookedBy(date).equals(user.email_id)==true){
					if(booked_data.contains(list[i])==false){
						booked_data.add(list[i]);
					}

				}
			}

		}


		private void handleBookingAction(ActionEvent arg0, Room room) throws IOException {
			Main.scene.openDialog("bookRoomFaculty");
			bookRoomControllerFaculty.room=room;

		}

		private void handleCancelAction(ActionEvent arg0, Room room) throws IOException{
			Main.scene.openDialog("cancelBookingFaculty");
			cancelBookingControllerFaculty.room = room;
		}


		static void book(Room room){
			room.Book(date, user.email_id, "" );
			avail_data.remove(room);
			booked_data.add(room);
		}

		static void cancel(Room room){
			user.cancelBooking(room, date);
			booked_data.remove(room);
			avail_data.add(room);
		}



}
