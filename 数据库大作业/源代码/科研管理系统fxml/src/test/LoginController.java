package test;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	   @FXML
	   private Button myButton;

	   @FXML
	   private TextField myTextField;

	   @Override
	   public void initialize(URL location, ResourceBundle resources) {

	   }
	   
	   // When user click on myButton
	   // this method will be called.
	   public void showDateTime(ActionEvent event) {
	       System.out.println("Button Clicked!");

	       Date now= new Date();

	       DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
	       String dateTimeString = df.format(now);
		   myTextField.setText(dateTimeString);
	   }

}
