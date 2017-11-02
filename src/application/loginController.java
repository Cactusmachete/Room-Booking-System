package application;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;

public class loginController {
	public ChoiceBox<String> choices;
	public TextField email;
	public PasswordField password;
	public Button signin, signup;
	public Label error;


	@FXML
	public void initialize(){
	    choices.getItems().removeAll(choices.getItems());
	    choices.getItems().addAll("Admin", "Faculty", "Student");
	    choices.getSelectionModel().select("Student");
	    signin.setOnAction(arg0 -> {
			try {
				handleSignInAction(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	    error.setVisible(false);

	    signup.setOnAction(arg0 -> {
			try {
				handleSignUpAction(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	    error.setVisible(false);
	}


	private void handleSignUpAction(ActionEvent arg0) throws IOException {
		error.setVisible(false);
		Main.scene.change("signup");

	}


	private void handleSignInAction(ActionEvent arg0) throws IOException{

		if (password.getText().isEmpty() || email.getText().isEmpty() || choices.getValue().isEmpty()){
			error.setVisible(true);
		}

		else if(email.getText().endsWith("@iiitd.ac.in")==false){
			 error.setVisible(true);
		 }
		else{
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream(choices.getValue()+"/"+email.getText()+".ser"));
				if(choices.getValue()=="Admin") {
					Admin input = (Admin) in.readObject();
					if(!input.password.equals(password.getText())) {
						throw new InvalidPasswordException(null);
					}
				}
				else if(choices.getValue()=="Student") {
					Student input = (Student) in.readObject();
					if(!input.password.equals(password.getText())) {
						throw new InvalidPasswordException(null);
					}
				}
				else {
					Faculty input = (Faculty) in.readObject();
					if(!input.password.equals(password.getText())) {
						throw new InvalidPasswordException(null);
					}
				}

				if(choices.getValue().equals("Student")){
					Main.scene.change("student_dash");
				}

				else if(choices.getValue().equals("Faculty")){
					Main.scene.change("faculty_dash");
				}

				else{
					Main.scene.change("admin_dash");
				}

			}
			catch(FileNotFoundException e)  {
				Main.scene.change("signup");
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			catch(InvalidPasswordException e) {
				error.setVisible(true);
			}

			finally{
				in.close();
			}




		}


	}
}
class InvalidPasswordException extends Exception {
	InvalidPasswordException(String s ){
		super(s);
	}
}
