package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
/*import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;*/
import javafx.scene.control.cell.PropertyValueFactory;

public class adminController {
	public Button book;
	public TextField bookRoom;
	public TableView<Room> availTable;
	public TableColumn<Room, String> availRoomNum, availSlot;
	public TableColumn<Room, Integer>availCap;
	Room[] list = Main.list;
	Admin user = (Admin) loginController.user;
	ObservableList<Room> data = FXCollections.observableArrayList();





	    @FXML
	    public void initialize() {


			for(int i=0; i<list.length; i++){
				if (!(list[i].isBooked())){
					data.add(list[i]);
				}
			}

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        availTable.setItems(data);

	        book.setOnAction(arg0 -> {
				handleBookingAction(arg0);
			});


	}





		private void handleBookingAction(ActionEvent arg0) {
			String roomName=bookRoom.getText();
			for(int i=0; i<list.length; i++){
				if (roomName.equals(list[i].getName())){
					user.bookit(list[i]);
					data.remove(list[i]);
					break;
				}
			}


		}

}
