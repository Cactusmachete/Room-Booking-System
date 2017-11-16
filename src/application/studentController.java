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

public class studentController{
	public Button availGo, viewDate, reqViewDate, reqSubmit, reqGo, logOut, searchGo;
	public Label dateLabel, reqDate;
	public TextField reqCapacity, searchBox;
	public TextArea reqPurposeField;
	public TableView<Room> availTable;
	public TableView<Booking> bookedTable;
	public TableView<Request> reqTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn<Booking, String> bookedRoom, bookedPurpose, bookedSlot;
	public TableColumn<Request, String> reqRoom, reqPurpose, reqStatus, reqSlot;
	public TableColumn<Room, Integer>availCap;
	public TableColumn<Booking, Integer> bookedCap;
	public TableColumn<Request, Integer> reqCap;
	public TableColumn <Room, Room> availBook;
	public TableColumn<Booking, Booking> bookedCancel;
	public TableColumn<Request, Request> reqCancel;
	public ChoiceBox<String> fromHrs, fromMins, toHrs, toMins, reqPrefRoom;
	public ListView<Course> listView;
	static Student user = (Student) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Booking> booked_data = FXCollections.observableArrayList();
	static ObservableList<Request> req_data = FXCollections.observableArrayList();
	static ObservableList<String> room_data = FXCollections.observableArrayList();
	static ObservableList<Course> course_data = FXCollections.observableArrayList();



	@FXML
	public void initialize(){
			dateLabel.setText(Main.date);
			reqDate.setText(Main.date);
			fromHrs.getItems().removeAll(fromHrs.getItems());
			 fromMins.getItems().removeAll(fromMins.getItems());
			 toHrs.getItems().removeAll(toHrs.getItems());
			 toMins.getItems().removeAll(toMins.getItems());

			 fromMins.getItems().addAll("00", "30");
			 toMins.getItems().addAll("00", "30");
			 fromHrs.getItems().addAll("00", "01", "02", "03", "04", "05",
					 "06", "07", "08", "09", "10", "11", "12", "13",
					 "14", "15", "16", "17", "18", "19", "20","21","22","23");
			 toHrs.getItems().addAll("00", "01", "02", "03", "04", "05", "06",
					 "07", "08", "09", "10", "11", "12", "13",
					 "14", "15", "16", "17", "18", "19", "20","21","22","23");



		user.requests = Request.deserialize2(user.email_id);

		bookedCancel.setCellValueFactory(
			            param -> new ReadOnlyObjectWrapper<>(param.getValue())
				);

		bookedCancel.setCellFactory(param -> new TableCell<Booking, Booking>() {
            private final Button deleteButton = new Button("Cancel");

            @Override
            protected void updateItem(Booking booking,  boolean empty) {
                super.updateItem(booking,  empty);

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

		reqCancel.setCellValueFactory(
	            param -> new ReadOnlyObjectWrapper<>(param.getValue())
	        );



        reqCancel.setCellFactory(param -> new TableCell<Request, Request>() {
            private final Button deleteButton = new Button("Cancel");
            private final Button deleteButton2 = new Button("Delete");

            @Override
            protected void updateItem(Request request,  boolean empty) {
                super.updateItem(request,  empty);

                if (request == null) {
                    setGraphic(null);
                    return;
                }

                else if(request.RequestStatus>0){
                	setGraphic(deleteButton2);
                	deleteButton2.setOnAction(arg0 ->{
                		handleRequestDeleteAction(arg0, request);
                	});
                	return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(arg0 ->{
                	handleRequestCancelAction(arg0, request);
                });
            }




        });


			for(int i=0; i<Main.list.length; i++){
				avail_data.add(Main.list[i]);
				ArrayList<Booking> booking = Main.list[i].getBooking();
				if(booking.size()>0){
					for(int j=0; j<booking.size(); j++){
						if(booked_data.contains(booking.get(j))==false &&
								booking.get(j).getUser().equals(user.email_id)){
							booked_data.add(booking.get(j));
						}
					}
				}
				room_data.add(Main.list[i].getName());
			}


			user.requests=Request.deserialize2(user.email_id);
			req_data.addAll(user.requests);
			/*for(int i=0; i<user.requests.size(); i++){
				req_data.add(user.requests.get(i));
			}*/

			reqPrefRoom.getItems().removeAll();
			reqPrefRoom.getItems().addAll(room_data);
			reqPrefRoom.getItems().add("");
			reqPrefRoom.getSelectionModel().select("");

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        availTable.setItems(avail_data);

	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Booking, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Booking, String>("slot"));
	        bookedPurpose.setCellValueFactory(new PropertyValueFactory<Booking, String>("purpose"));
	        bookedTable.setItems(booked_data);

	        reqRoom.setCellValueFactory(new PropertyValueFactory<Request, String>("name"));
	        reqCap.setCellValueFactory(new PropertyValueFactory<Request, Integer>("capacity"));
	        reqSlot.setCellValueFactory(new PropertyValueFactory<Request, String>("slot"));
	        reqPurpose.setCellValueFactory(new PropertyValueFactory<Request, String>("purpose"));
	        reqStatus.setCellValueFactory(new PropertyValueFactory<Request, String>("status"));
	        reqTable.setItems(req_data);



	        availGo.setOnAction(arg0 ->{
            	handleGoAction(arg0);
            });

	        reqGo.setOnAction(arg0 ->{
            	handleGoAction(arg0);
            });


	        viewDate.setOnAction(arg0 ->{
	        	Main.scene.showCal();
            });

	        reqViewDate.setOnAction(arg0 ->{
	        	Main.scene.showCal();
            });

	        reqSubmit.setOnAction(arg0 ->{
            	handleRequestAction(arg0);
            });

	        logOut.setOnAction(arg0 ->{
            	try {
					handleLogOutAction(arg0);
				} catch (IOException e) {
					e.printStackTrace();
				}
            });

	        searchGo.setOnAction(arg0 ->{
	        	handleSearchAction(arg0);
            });


	        listView.setItems(course_data);

	}

	private void handleSearchAction(ActionEvent arg0) {

		course_data.clear();
		for(int i=0; i<Main.course_list.size(); i++){
			String[] terms = searchBox.getText().split("\\s+");
			for(int j=0; j<terms.length; j++){
				if(Main.course_list.get(i).post_conditions.contains(terms[j])){
					course_data.add(Main.course_list.get(i));
					break;
				}
			}
		}
	}

	private void handleRequestDeleteAction(ActionEvent arg0, Request request) {
		req_data.remove(request);
		request.RequestStatus=4;

	}

	private void handleRequestCancelAction(ActionEvent arg0, Request request) {
		user.requests.remove(request);
		user.requests.add(request);
		req_data.remove(request);
		request.RequestStatus=3;
		req_data.add(request);
	}

	private void handleRequestAction(ActionEvent arg0) {
		Request request = new Request(user, reqPurposeField.getText(), Integer.parseInt(reqCapacity.getText()),
						reqPrefRoom.getValue(), fromHrs.getValue(), fromMins.getValue(),
						toHrs.getValue(), toMins.getValue());
		System.out.println(request.getName());
		user.requests.add(request);
		req_data.add(request);
	}


	private void handleLogOutAction(ActionEvent arg0) throws IOException {
		Main.scene.change("logged_out");
		try {
			Student.serialize(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleGoAction(ActionEvent arg0) {
    	dateLabel.setText(Main.date);
    	reqDate.setText(Main.date);
    	avail_data.clear();
		for(int i=0; i<Main.list.length; i++){
				avail_data.add(Main.list[i]);
		}
	}

	private void handleCancelAction(ActionEvent arg0, Booking booking) throws IOException{
		Main.scene.openDialog("cancelBookingStudent");
		cancelBookingControllerStudent.booking = booking;
	}

	static void cancel(Booking booking){
		user.cancelBooking(booking.room, Main.date, booking);
		booked_data.remove(booking);
		avail_data.remove(booking.room);
		avail_data.add(booking.room);
	}

}
