package application;
/**
 * <h1>The Faculty Controller Class </h1>
 * The controller class for Faculty's dashboard. 
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

public class facultyController {
	public Button availGo, viewDate, logOut;
	public Label dateLabel;
	public TableView<Room> availTable;
	public TableView<Booking> bookedTable;
	public TableView<Course> courseTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn <Booking, String> bookedRoom, bookedSlot;
	public TableColumn <Course, String> courseName, courseCode;
	public TableColumn<Room, Room>  availBook;
	public TableColumn<Booking, Booking> bookedCancel;
	public TableColumn<Course, Course> courseDetails;
	public TableColumn<Room, Integer>availCap;
	public TableColumn<Booking, Integer> bookedCap;
	static Faculty user = (Faculty) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Booking> booked_data = FXCollections.observableArrayList();
	static ObservableList<Course> course_data = FXCollections.observableArrayList();


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
			        courseDetails.setCellValueFactory(
				            param -> new ReadOnlyObjectWrapper<>(param.getValue())
				        );


				        courseDetails.setCellFactory(param -> new TableCell<Course, Course>() {
				            private final Button bookButton = new Button("View Details");

				            @Override
				            protected void updateItem(Course course, boolean empty) {
				                super.updateItem(course, empty);

				                if (course == null) {
				                    setGraphic(null);
				                    return;
				                }

				                setGraphic(bookButton);
				                bookButton.setOnAction(arg0 ->{
				                	try {
										handleViewDetailAction(arg0, course);
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
							if(booked_data.contains(booking.get(j))==false && booking.get(i).user.equals(user.email_id)){
								booked_data.add(booking.get(j));
							}
						}
					}
			}

			for(int i=0; i<Main.course_list.size(); i++){
				if(Main.course_list.get(i).Instructor.equals(user.name)){
					course_data.add(Main.course_list.get(i));
				}
			}

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));

	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Booking, String>("slot"));

	        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
	        courseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));

	        availTable.setItems(avail_data);
	        bookedTable.setItems(booked_data);
	        courseTable.setItems(course_data);

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


		private void handleViewDetailAction(ActionEvent arg0, Course course) throws IOException {
			viewDetailsController.course = course;
			Main.scene.openDialog("viewDetails");
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
	   	 * Manages room bookings by the Faculty by opening a dialog.
		 * Called when user wants to book a room.
 	    	* @param arg0 of type ActionEvent
		* @param room is room object to be booked.
		* @throws IOException
	    	*/



		private void handleBookingAction(ActionEvent arg0, Room room) throws IOException {
			Main.scene.openDialog("bookRoomFaculty");
			bookRoomControllerFaculty.room=room;

		}
	
		/**
	   	 * Manages room bookings by the Faculty by opening a dialog.
		 * Called when user wants to cancel a booking.
 	    	* @param arg0 of type ActionEvent
		* @param booking object to be cancelled.
		* @throws IOException
	    	*/

		private void handleCancelAction(ActionEvent arg0, Booking booking) throws IOException{
			Main.scene.openDialog("cancelBookingFaculty");
			cancelBookingControllerFaculty.booking = booking;
		}
		/**
	   	 * Books room for faculty and refreshes dashboard to display real time changes.
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
	   	 * Deletes room booking for faculty and refreshes dashboard to display real time changes.
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
				Faculty.serialize(user);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


}
