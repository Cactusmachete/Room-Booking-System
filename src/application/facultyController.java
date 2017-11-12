package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class facultyController {
	public Button availGo, viewDate;
	public Label dateLabel;
	public TableView<Room> availTable;
	public TableView<Booking> bookedTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn <Booking, String> bookedRoom, bookedSlot;
	public TableColumn<Room, Room>  availBook;
	public TableColumn<Booking, Booking> bookedCancel;
	public TableColumn<Room, Integer>availCap;
	public TableColumn<Booking, Integer> bookedCap;
	static Room[] list = Main.list;
	static Faculty user = (Faculty) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Booking> booked_data = FXCollections.observableArrayList();





	    @FXML
	    public void initialize() {

	    	dateLabel.setText(Main.date);

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
			        bookedCancel.setCellFactory(param -> new TableCell<Booking,Booking>() {
			            private final Button deleteButton = new Button("Cancel");

			            @Override
			            protected void updateItem(Booking booking, boolean empty) {
			                super.updateItem(booking, empty);

			                if (booking == null) {
			                    setGraphic(null);
			                    return;
			                }

			                setGraphic(deleteButton);
			                deleteButton.setOnAction(arg0 ->{
			                	try {
									handleCancelAction(arg0, booking);

								} catch (IOException e) {
									e.printStackTrace();
								}

			                });

			            }
			        });


			for(int i=0; i<list.length; i++){
				avail_data.add(list[i]);
				ArrayList<Booking> booking = list[i].getBooking();
					if(booking.size()>0){
						for(int j=0; j<booking.size(); j++){
							if(booked_data.contains(booking.get(i))==false){
								booked_data.add(booking.get(i));
							}
						}
					}
			}

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));

	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Booking, String>("slot"));

	        availTable.setItems(avail_data);
	        bookedTable.setItems(booked_data);

	        availGo.setOnAction(arg0 ->{
            	handleGoAction(arg0);
            });


	        viewDate.setOnAction(arg0 ->{
	        	Main.scene.showCal();
            });


	}


	    private void handleGoAction(ActionEvent arg0) {
	    	dateLabel.setText(Main.date);
	    	avail_data.clear();
			for(int i=0; i<list.length; i++){
					avail_data.add(list[i]);
			}
		}


		private void handleBookingAction(ActionEvent arg0, Room room) throws IOException {
			Main.scene.openDialog("bookRoomFaculty");
			bookRoomControllerFaculty.room=room;

		}

		private void handleCancelAction(ActionEvent arg0, Booking booking) throws IOException{
			Main.scene.openDialog("cancelBookingFaculty");
			cancelBookingControllerFaculty.booking = booking;
		}

		static void book(Room room){

			booked_data.add(room.getBooking().get(room.getBooking().size()-1));
			avail_data.clear();
			for(int i=0; i<list.length; i++){
					avail_data.add(list[i]);

			}
		}

		static void cancel(Booking booking){
			user.cancelBooking(booking.room, Main.date, booking);
			booked_data.remove(booking);
			avail_data.remove(booking.room);
			avail_data.add(booking.room);
		}


}
