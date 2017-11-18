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
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;

public class studentController{
	public Button availGo, viewDate, reqViewDate, reqSubmit, reqGo, logOut, searchGo;
	public Label dateLabel, reqDate, clashError, detailError, timeError, searchError;
	public TextField reqCapacity, searchBox;
	public TextArea reqPurposeField;
	public TableView<Room> availTable;
	public TableView<Booking> bookedTable;
	public TableView<Request> reqTable;
	public TableView<TimeTable> timeTable;
	public TableView<Course> courseTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn<Booking, String> bookedRoom, bookedPurpose, bookedSlot;
	public TableColumn<Request, String> reqRoom, reqPurpose, reqStatus, reqSlot;
	public TableColumn<TimeTable, String> monClass, tueClass, wedClass, thuClass, friClass;
	public TableColumn<TimeTable, String> timeSlot;
	public TableColumn <Course, String> courseName, courseCode;
	public TableColumn<Room, Integer>availCap;
	public TableColumn<Booking, Integer> bookedCap;
	public TableColumn<Request, Integer> reqCap;
	public TableColumn <Room, Room> availBook;
	public TableColumn<Booking, Booking> bookedCancel;
	public TableColumn<Request, Request> reqCancel;
	public TableColumn<Course, Course> courseDetails, courseDel;
	public ChoiceBox<String> fromHrs, fromMins, toHrs, toMins, reqPrefRoom;
	public ListView<Course> listView;
	static Student user = (Student) loginController.user;
	static ObservableList<Room> avail_data = FXCollections.observableArrayList();
	static ObservableList<Booking> booked_data = FXCollections.observableArrayList();
	static ObservableList<Request> req_data = FXCollections.observableArrayList();
	static ObservableList<String> room_data = FXCollections.observableArrayList();
	static ObservableList<Course> course_data = FXCollections.observableArrayList();
	static ObservableList<Course> user_courses = FXCollections.observableArrayList();
	static ObservableList<TimeTable> time_data = FXCollections.observableArrayList();
	static ObservableList<String> time = FXCollections.observableArrayList();




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

		clashError.setVisible(false);
		detailError.setVisible(false);
		timeError.setVisible(false);
		searchError.setVisible(false);

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


		courseDel.setCellValueFactory(
			            param -> new ReadOnlyObjectWrapper<>(param.getValue())
				);

		courseDel.setCellFactory(param -> new TableCell<Course, Course>() {
            private final Button deleteButton = new Button("Drop");

            @Override
            protected void updateItem(Course course,  boolean empty) {
                super.updateItem(course,  empty);

                if (course == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(arg0 ->{
   						handleDropAction(arg0, course);
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



        listView.setCellFactory(param -> new ListCell<Course>(){
        	private final Button addButton = new Button("Join");
        	private final GridPane pane = new GridPane();
        	private final Label data = new Label();
        	private final Label spacer = new Label();


        	@Override
        	public void updateItem(Course course, boolean empty) {
        		super.updateItem(course, empty);

        		data.setPrefWidth(listView.getWidth()*0.85);
        		spacer.setPrefWidth(listView.getWidth()*0.05);
        		data.setTextAlignment(TextAlignment.JUSTIFY);
        		pane.prefWidthProperty().bind(listView.widthProperty());
        		data.setWrapText(true);
        		pane.getChildren().clear();
        	    pane.add(data, 0, 0);
        	    pane.add(spacer, 1,0);
        		pane.add(addButton, 2, 0);

                setText(null);
        		setEditable(false);
        		if (course != null) {
        			data.setText(course.toString());
        			addButton.setOnAction(arg0 ->{
                		handleAddCourseAction(arg0, course);
                	});
        			setGraphic(pane);
        		} else {
        			setGraphic(null);
        		}
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
			user_courses.addAll(user.CourseList);


			time.addAll("08:30", "09:00", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
					"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
					"17:30", "18:00");

			makelist();


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

	        monClass.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("mon"));
	        tueClass.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("tue"));
	        wedClass.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("wed"));
	        thuClass.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("thu"));
	        friClass.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("fri"));
	        timeSlot.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("time"));
	        timeTable.setItems(time_data);

	        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
	        courseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
	        courseTable.setItems(user_courses);

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

	private void makelist() {
		time_data.clear();
		for(int i=0; i<time.size(); i++){
			String mon, tue, wed, thu, fri;
			if(user.mon[i+18]==null){mon="";}
			else{
				mon = user.mon[i+18].toString;
			}
			if(user.tue[i+18]==null){tue="";}
			else{
				tue = user.tue[i+18].toString;
			}
			if(user.wed[i+18]==null){wed="";}
			else{
				wed = user.wed[i+18].toString;
			}
			if(user.thur[i+18]==null){thu="";}
			else{
				thu = user.thur[i+18].toString;
			}
			if(user.fri[i+18]==null){fri="";}
			else{
				fri = user.fri[i+18].toString;
			}
			TimeTable table = new TimeTable(time.get(i), mon, tue,wed, thu, fri);
			time_data.add(table);
		}

	}

	private void handleDropAction(ActionEvent arg0, Course course) {
		user.DropCourse(course);
		user.CourseList.remove(course);
		user_courses.remove(course);
		course_data.add(course);
		makelist();
		timeTable.setItems(time_data);

	}

	private void handleViewDetailAction(ActionEvent arg0, Course course) throws IOException {
		viewDetailsController.course = course;
		Main.scene.openDialog("viewDetails");
	}

	private void handleAddCourseAction(ActionEvent arg0, Course course) {
		try {
			clashError.setVisible(false);
			user.AddCourse(course);
			user.CourseList.add(course);
			user_courses.add(course);
			makelist();
			timeTable.setItems(time_data);
			course_data.remove(course);
		} catch (ClassClashException e) {
			clashError.setVisible(true);
		}
		finally{
			searchBox.setText("");
		}
	}

	private void handleSearchAction(ActionEvent arg0) {
		clashError.setVisible(false);
		searchError.setVisible(false);
		if(searchBox.getText().isEmpty() || searchBox.getText().matches("\\s+")){
			searchError.setVisible(true);
		}
		else{
			searchError.setVisible(false);
			course_data.clear();
			for(int i=0; i<Main.course_list.size(); i++){
				String[] terms = searchBox.getText().split("\\s+");
				for(int j=0; j<terms.length; j++){
					if(user_courses.contains(Main.course_list.get(i))==false &&
							Main.course_list.get(i).post_conditions.toLowerCase().contains(terms[j].toLowerCase())){
							course_data.add(Main.course_list.get(i));
							break;
					}
				}
			}
			searchBox.setText("");
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

		if(reqPurposeField.getText().isEmpty() || reqCapacity.getText().isEmpty() || isNumeric(reqCapacity.getText())==false
				|| fromHrs.getValue()==null || fromMins.getValue()==null || toHrs.getValue()==null ||
				toMins.getValue()==null){
			timeError.setVisible(false);
			detailError.setVisible(true);
		}

		else{
			int fromhrs = Integer.parseInt(fromHrs.getValue());
			int frommin = Integer.parseInt(fromMins.getValue());
			int tohrs = Integer.parseInt(toHrs.getValue());
			int tomins = Integer.parseInt(toMins.getValue());
			if(fromhrs>tohrs || (fromhrs==tohrs && frommin>=tomins)){
				detailError.setVisible(false);
				timeError.setVisible(true);
			}

			else{
				timeError.setVisible(false);
				detailError.setVisible(false);
				Request request = new Request(user, reqPurposeField.getText(), Integer.parseInt(reqCapacity.getText()),
						reqPrefRoom.getValue(), fromHrs.getValue(), fromMins.getValue(),
						toHrs.getValue(), toMins.getValue());
				System.out.println(request.getName());
				user.requests.add(request);
				req_data.add(request);
				reqPurposeField.setText("");
				reqCapacity.setText("");
				fromHrs.setValue(null);
				fromMins.setValue(null);
				toHrs.setValue(null);
				toMins.setValue(null);
				reqPrefRoom.setValue("");
			}
		}
	}


	private boolean isNumeric(String text) {
		return text != null && text.matches("[-+]?\\d*\\.?\\d+");

	}

	private void handleLogOutAction(ActionEvent arg0) throws IOException {
		Main.scene.change("logged_out");
		try {
			Student.serialize(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleGoAction(ActionEvent arg0){

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
