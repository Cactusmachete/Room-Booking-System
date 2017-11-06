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
	public Button button, signin;



	@FXML
	public void initialize(){
	    choices.getItems().removeAll(choices.getItems());
	    choices.getItems().addAll("Admin", "Faculty", "Student");
	    choices.getSelectionModel().select("Student");
	    button.setOnAction(arg0 -> {
			try {
				handleButtonAction(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	    error.setVisible(false);

	    signin.setOnAction(arg0 -> {
			try {
				handleSignInAction(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	    error.setVisible(false);
	}

	 private void handleSignInAction(ActionEvent arg0) throws IOException {
		 Main.scene.change("login");

	}

	private void handleButtonAction(ActionEvent event) throws IOException  {

		 if(name.getText().equals("") || email.getText().equals("") || password.getText().equals("") ||choices.getValue().equals("")){
			 error.setVisible(true);
		 }
		 else if(email.getText().endsWith("@iiitd.ac.in")==false){
			 error.setVisible(true);
		 }

		 else{
			 error.setVisible(false);
			 Main.scene.change("login");
			 if(choices.getValue()=="Admin") {
				 Admin input = new Admin(email.getText(),password.getText(),name.getText());
				 try {
					serialize(input,choices.getValue());
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 if(choices.getValue()=="Student") {
				 Student input = new Student(email.getText(),password.getText(),name.getText());
				 try {
					serialize(input,choices.getValue());
				} catch (IOException e) {

					e.printStackTrace();
				}
			 }
			 if(choices.getValue()=="Faculty") {
				 Faculty input = new Faculty(email.getText(),password.getText(),name.getText());
				 try {
					serialize(input,choices.getValue());
				} catch (IOException e) {

					e.printStackTrace();
				}
			 }


		 }

		 password.setText("");




	 }

	 public static void serialize(User input,String type) throws IOException  {
		 ObjectOutputStream out = null;
		 try {
			 File inp = new File(type+"/"+input.email_id+".ser");
			 inp.createNewFile();
			 out = new ObjectOutputStream(new FileOutputStream(inp, false));
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