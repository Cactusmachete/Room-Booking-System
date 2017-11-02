package application;
import java.io.*;


import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class signupController {
	public ChoiceBox<String> choices;
	public TextField email, name;
	public Label error;
	public PasswordField password;
	public Button button;
	public String a = "";


	@FXML
	public void initialize(){
	    choices.getItems().removeAll(choices.getItems());
	    choices.getItems().addAll("Admin", "Faculty", "Student");
	    choices.getSelectionModel().select("Student");
	    button.setOnAction(this::handleButtonAction);
	    error.setVisible(false);
	}

	 private void handleButtonAction(ActionEvent event)  {

		 if(name.getText().equals("") || email.getText().equals("") || password.getText().equals("") ||choices.getValue().equals("")){
			 error.setVisible(true);
		 }
		 else if(email.getText().endsWith("@iiitd.ac.in")==false){
			 error.setVisible(true);
		 }

		 else{
			 error.setVisible(false);
			 a=a+name.getText()+","+email.getText()+","+password.getText()+","+choices.getValue();
			 if(choices.getValue()=="Admin") {
				 Admin input = new Admin(email.getText(),password.getText(),name.getText());
				 try {
					serialize(input,choices.getValue());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 if(choices.getValue()=="Student") {
				 Student input = new Student(email.getText(),password.getText(),name.getText());
				 try {
					serialize(input,choices.getValue());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 if(choices.getValue()=="Faculty") {
				 Faculty input = new Faculty(email.getText(),password.getText(),name.getText());
				 try {
					serialize(input,choices.getValue());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
		
		 
		
	 }
	 
	 public static void serialize(User input,String type) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 out = new ObjectOutputStream(new FileOutputStream("/"+type+"/"+input.name+".ser"));
			 out.writeObject(input);
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 finally {
			 out.close();
		 }
	 }
}
