package application;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {

        	String dat = date.toString().substring(8,10) + "/"+date.toString().substring(5,7) + "/"+ date.getYear();
        	Main.date=dat;
        	try {
				Main.scene.openDialog("youSure");
			} catch (IOException e1) {
				e1.printStackTrace();
			}


			 Stage stage = (Stage) this.getScene().getWindow();
			 stage.close();

        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
