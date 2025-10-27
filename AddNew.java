package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddNew {
	TextField id = new TextField();
	TextField titel = new TextField();
	TextField auther = new TextField();
	TextField cate = new TextField();
	TextField pYear = new TextField();
	TextField iBn = new TextField();
	TextField searchText = new TextField();
	Button save;
	Button back;
	static ArrayList<Book> list2 = new ArrayList<Book>();
    //create method to return the scene on which you add a new book
	public Scene getScene2(Stage s) {
		BorderPane b = new BorderPane();
		VBox bp = new VBox(100);
		Image sa = new Image("save.png");
		ImageView iv7 = new ImageView(sa);
		iv7.setFitWidth(80);
		iv7.setFitHeight(80);
		Image ba = new Image("back.png");
		ImageView iv8 = new ImageView(ba);
		iv8.setFitWidth(80);
		iv8.setFitHeight(80);
		save = new Button("", iv7);
		//button to save the new book
		save.setOnAction(e -> {
			Book o=new Book(id.getText(), titel.getText(), auther.getText(), cate.getText(),
					Integer.parseInt(pYear.getText()), iBn.getText());
			if(o.getBookId()!=null&&o.getTitle()!=null&&o.getAuther()!=null&&o.getCategory()!=null&&o.getPublishedYear()!=0&&o.getIsBn()!=null) {
			list2.add(o);
			Alert alert1=new Alert(AlertType.INFORMATION,"Information added successfully",ButtonType.CLOSE);
		      alert1.setTitle("Exception");
		      alert1.showAndWait();
		      id.clear();
		      titel.clear();
		      auther.clear(); 
		      cate.clear();
			  pYear.clear();
			  iBn.clear();
			}else {
			  Alert alert1=new Alert(AlertType.INFORMATION,"The addition was not successful",ButtonType.CLOSE);
		      alert1.setTitle("Exception");
		      alert1.showAndWait();
			}
		});
		back = new Button("", iv8);
		back.setOnAction(e -> {
			Main o = new Main();
			o.start(s);
			s.setFullScreen(true);
			BookView.listO.addAll(list2);
		});
		//create grid pane to add text field and label
		GridPane txt = new GridPane(20, 20);
		txt.add(new Label("book Id:"), 0, 0);
		txt.add(id, 1, 0);
		txt.add(new Label("titel:"), 0, 1);
		txt.add(titel, 1, 1);
		txt.add(new Label("auther:"), 0, 2);
		txt.add(auther, 1, 2);
		txt.add(new Label("category:"), 0, 3);
		txt.add(cate, 1, 3);
		txt.add(new Label("publishedYear:"), 0, 4);
		txt.add(pYear, 1, 4);
		txt.add(new Label("isBn:"), 0, 5);
		txt.add(iBn, 1, 5);
		txt.setTranslateY(20);
		b.setStyle("-fx-background-color: #A27B5C;");
		//create hbox to add tow button save to save the information and back To return to the first scene
		HBox vb = new HBox(10);
		vb.getChildren().addAll(save, back);
		vb.setTranslateY(20);
		bp.getChildren().addAll(txt, vb);
		bp.setTranslateX(120);
		bp.setAlignment(Pos.CENTER);
		Image im = new Image("library.jpg");
		ImageView iv = new ImageView(im);
		iv.setFitWidth(400);
		iv.setFitHeight(500);
		iv.setTranslateY(70);
		iv.setTranslateX(20);
		Image im2 = new Image("library.jpg");
		ImageView iv2 = new ImageView(im2);
		iv2.setFitWidth(400);
		iv2.setFitHeight(500);
		iv2.setTranslateY(70);
		iv2.setTranslateX(-20);
		b.setLeft(iv);
		b.setCenter(bp);
		b.setRight(iv2);
		Scene second = new Scene(b);
		second.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return second;

	}

}
