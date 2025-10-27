package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MyException extends Exception{
	public MyException(String massage) {
		super(massage);
		Alert alert1=new Alert(AlertType.INFORMATION,massage,ButtonType.CLOSE);
	      alert1.setTitle("Exception");
	      alert1.showAndWait();
	}
}
