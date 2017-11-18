package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

public class viewDetailsController {
	public Label nameLabel, acroLabel, instLabel, creditLabel, postLabel;
	public AnchorPane parent;
	static Course course;

	@FXML
	public void initialize(){
		nameLabel.setText(course.Name);
		acroLabel.setText(course.Acronym);
		instLabel.setText(course.Instructor);
		creditLabel.setText(String.valueOf(course.Credits));
		postLabel.setWrapText(true);
		postLabel.setTextAlignment(TextAlignment.JUSTIFY);
		postLabel.setText(course.post_conditions);
	}

}
