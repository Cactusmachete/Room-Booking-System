package application;
/**
 * <h1>The Admin Controller Class </h1>
 * The controller class for Admin's dashboard. 
 * @author Ahilya Sinha
 */
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class adminController {
	public Button availGo, viewDate, logOut;
	public Label dateLabel;
	public TableView<Room> availTable;
	public TableView<Booking> bookedTable;
	public TableView<Request> reqTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn <Booking, String> bookedRoom, bookedPurpose, bookedSlot;
	public TableColumn <Request, String> reqRoom, reqPurpose, reqSlot;
	public TableColumn<Room, Room>  availBook;
	public TableColumn<Request, Request> reqManage;
	public TableColumn<Booking, Booking> bookedCancel;
	public TableColumn<Room, Integer>availCap;
	public TableColumn<Booking, Integer> bookedCap;
	public TableColumn<Request, Integer> reqCapacity;
	static Admin user = (Admin) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Booking> booked_data = FXCollections.observableArrayList();
	static ObservableList<Request> req_data = FXCollections.observableArrayList();


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

	        reqManage.setCellValueFactory(
		            param -> new ReadOnlyObjectWrapper<>(param.getValue())
		        );

		        reqManage.setCellFactory(param -> new TableCell<Request, Request>() {
		            private final Button bookButton = new Button("Manage");

		            @Override
		            protected void updateItem(Request request, boolean empty) {
		                super.updateItem(request, empty);

		                if (request == null) {
		                    setGraphic(null);
		                    return;
		                }

		                setGraphic(bookButton);
		                bookButton.setOnAction(arg0 ->{
		                	try {
								handleManageAction(arg0, request);
							} catch (IOException e) {
								e.printStackTrace();
							}
		                });
		            }
		        });



	        for(int i=0; i<Main.list.length; i++){
					avail_data.add(Main.list[i]);
					ArrayList<Booking> booking = Main.list[i].getBooking();
					if(booking.size()>0){
						for(int j=0; j<booking.size(); j++){
							if(booked_data.contains(booking.get(j))==false){
								booked_data.add(booking.get(j));
							}
						}
					}
			}

	        Admin.requests = Request.deserialize1();
	        for(int i=0; i<Admin.requests.size(); i++){
	        	if(Admin.requests.get(i).RequestStatus==0){
	        		req_data.add(Admin.requests.get(i));
	        	}
	        }

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));

	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Booking, String>("slot"));
	        bookedPurpose.setCellValueFactory(new PropertyValueFactory<Booking, String>("purpose"));

	        reqRoom.setCellValueFactory(new PropertyValueFactory<Request, String>("name"));
	        reqCapacity.setCellValueFactory(new PropertyValueFactory<Request, Integer>("capacity"));
	        reqSlot.setCellValueFactory(new PropertyValueFactory<Request, String>("slot"));
	        reqPurpose.setCellValueFactory(new PropertyValueFactory<Request, String>("purpose"));

	        bookedTable.setItems(booked_data);
	        availTable.setItems(avail_data);
	        reqTable.setItems(req_data);

	        availGo.setOnAction(arg0 ->{
            	handleGoAction(arg0);
            });


	        viewDate.setOnAction(arg0 ->{
	        	Main.scene.showCal();
            });

	        logOut.setOnAction(arg0 ->{
	        	try {
					handleLogOutAction(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}
            });


	    }
	     	/**
		* Opens a new dialog where the Admin can manage the booking requests by students.
 		* @param arg0 of type ActionEvent
		* @param request of type request passes the request object to be managed to the opened dialog.
		* @throws IOException
		*/

	    private void handleManageAction(ActionEvent arg0, Request request) throws IOException {
			Main.scene.openDialog("manage_req");
			manageReqController.request=request;

		}
	
	    /**
	    * Refreshes the date and list of available rooms to the new date chosen by the User.
 	    * @param arg0 of type ActionEvent
	    */

	    private void handleGoAction(ActionEvent arg0) {
	    	dateLabel.setText(Main.date);
	    	avail_data.clear();
			for(int i=0; i<Main.list.length; i++){
					avail_data.add(Main.list[i]);
			}
		}
		 /**
	   	 * Manages room bookings by the Admin by opening a dialog.
		 * Called when user wants to book a room.
 	    	* @param arg0 of type ActionEvent
		* @param room is room object to be booked.
		* @throws IOException
	    	*/


		private void handleBookingAction(ActionEvent arg0, Room room) throws IOException {
			Main.scene.openDialog("bookRoomAdmin");
			bookRoomControllerAdmin.room=room;

		}
		/**
	   	 * Manages room bookings by the Admin by opening a dialog.
		 * Called when user wants to cancel a booking.
 	    	* @param arg0 of type ActionEvent
		* @param booking object to be cancelled.
		* @throws IOException
	    	*/


		private void handleCancelAction(ActionEvent arg0, Booking booking) throws IOException{
			Main.scene.openDialog("cancelBooking");
			cancelBookingControllerAdmin.booking = booking;
		}
	
		/**
	   	 * Books room for admin and refreshes dashboard to display real time changes.
		* @param room object to be booked.
	    	*/

		static void book(Room room){
			booked_data.add(room.getBooking().get(room.getBooking().size()-1));
			avail_data.clear();
			for(int i=0; i<Main.list.length; i++){
					avail_data.add(Main.list[i]);
			}
		}
	
		/**
	   	 * Books room for admin and refreshes dashboard to display real time changes.
		* @param booking object to be cancelled.
	    	*/

		static void cancel(Booking booking){
			user.cancelBooking(booking.room, Main.date, booking);
			booked_data.remove(booking);
			avail_data.remove(booking.room);
			avail_data.add(booking.room);
		}
		
		/**
	   	 * Handles the logout action when user clicks the log out button.
		* @param arg0 ActionEvent
		* @throws IOException
	    	*/


		private void handleLogOutAction(ActionEvent arg0) throws IOException {
			Main.scene.change("logged_out");
			try {
				Admin.serialize(user);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}


