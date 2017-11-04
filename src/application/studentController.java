package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class studentController{
	public TextField capacity, prefRoom, date;
	public TextArea purpose;
	public Button submit;
	public ChoiceBox<String> timeFrom, timeTo;
	public TableView<Room> availTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn<Room, Integer>availCap;
	Room[] list = Main.list;
	Student user = (Student) loginController.user;












	@FXML
	public void initialize(){




			ObservableList<Room> data = FXCollections.observableArrayList();

			for(int i=0; i<list.length; i++){
				if (!(list[i].isBooked())){
					data.add(list[i]);
				}
			}
	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        availTable.setItems(data);


	}

}
