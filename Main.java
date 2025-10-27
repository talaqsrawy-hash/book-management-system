package application;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	FileHandling obj = new FileHandling();
	BookView obj2 = new BookView();
	FileChooser fc;
	TextField id = new TextField();
	TextField titel = new TextField();
	TextField auther = new TextField();
	TextField cate = new TextField();
	TextField pYear = new TextField();
	TextField iBn = new TextField();
	TextField textSearch = new TextField();
	String searchText = new String();
	ComboBox<String> displayBook = new ComboBox<String>();
	TextField bookSearch = new TextField();
	String searchBok = new String();
	Label comboselaect = new Label();
	Label result = new Label();
	TextArea ta = new TextArea();
	ComboBox<String> chois = new ComboBox<String>();
	TextField searchFunction = new TextField();
	Button functionSearch = new Button("search");

	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox(40);
			VBox vb = new VBox(20);
			// file choser button
			Image fileCh = new Image("add.png");
			ImageView iv2 = new ImageView(fileCh);
			iv2.setFitWidth(80);
			iv2.setFitHeight(80);
			Button chooser = new Button("Read file", iv2);//button to select a file to read
			chooser.setContentDisplay(ContentDisplay.TOP);
			chooser.setPrefWidth(80);
			chooser.setPrefHeight(80);
			chooser.setOnAction(e -> {
				fc = new FileChooser();
				fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
				File select = fc.showOpenDialog(primaryStage);
				BookView.listO.addAll(obj.readFromFile(select));
				AddNew.list2.addAll(obj.readFromFile(select));
			});
			// insert a new book record
			Image insert = new Image("addBook.png");
			ImageView iv3 = new ImageView(insert);
			iv3.setFitWidth(80);
			iv3.setFitHeight(80);
			Button insertBook = new Button("insert", iv3);
			insertBook.setOnAction(e -> {
				AddNew obb = new AddNew();
				try {
					primaryStage.setScene(obb.getScene2(primaryStage));
					primaryStage.setFullScreen(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			insertBook.setContentDisplay(ContentDisplay.TOP);
			insertBook.setPrefWidth(80);
			insertBook.setPrefHeight(80);

			// delete a book record
			Image delet = new Image("delete.png");
			ImageView iv4 = new ImageView(delet);
			iv4.setFitWidth(80);
			iv4.setFitHeight(80);
			Button deletBook = new Button("delete", iv4);
			deletBook.setOnAction(e -> {
				searchText = textSearch.getText();
				for (int i = 0; i < AddNew.list2.size(); i++) {
					if (AddNew.list2.get(i).getBookId().equals(searchText)
							|| AddNew.list2.get(i).getTitle().equals(searchText)) {
						AddNew.list2.remove(AddNew.list2.get(i));
					}
				}
				BookView.listO.clear();
				BookView.listO.addAll(AddNew.list2);
			});
			deletBook.setContentDisplay(ContentDisplay.TOP);
			deletBook.setPrefWidth(80);
			deletBook.setPrefHeight(80);

			// search for a specific book
			Image search = new Image("search.png");
			ImageView iv5 = new ImageView(search);
			iv5.setFitWidth(80);
			iv5.setFitHeight(80);
			Button searchBook = new Button("search", iv5);
			searchBook.setContentDisplay(ContentDisplay.TOP);
			searchBook.setPrefWidth(80);
			searchBook.setPrefHeight(80);
			searchBook.setOnAction(event -> {
				BookView.listO.clear();
				BookView.listO.addAll(AddNew.list2);
				searchText = textSearch.getText();
				for (int i = 0; i < AddNew.list2.size(); i++) {
					if (AddNew.list2.get(i).getBookId().equals(searchText)
							|| AddNew.list2.get(i).getTitle().equals(searchText)) {
						BookView.listO.clear();
						BookView.listO.add(AddNew.list2.get(i));
					}
				}
			});
			// display statistics 
			//create combo box to display statistics for the books
			displayBook.getItems().addAll("Number of books by category", "Number of books by author",
					"Number of books published in specific year",
					"The year with the maximum number of books published, along with the count of books published in that year",
					"The year with the minimum number of books published, along with the count of books published in that year",
					"The author with the maximum number of books published along with the titles of those books",
					"The author with the minimum number of books published along with the titles of those books");
			displayBook.setValue("display statistics by:");
			displayBook.setOnAction(e -> {
				if (displayBook.getValue().trim().equals("Number of books by category")) {
					int co = 0;
					comboselaect.setText("enter category:");
					for (int i = 0; i < BookView.listO.size(); i++) {
						if (BookView.listO.get(i).getCategory().equalsIgnoreCase(bookSearch.getText().trim())) {
							co++;
						}
					}
					if (co > 0) {
						result.setText(co + "");
					} else if (co <= 0) {
						Alert alert1 = new Alert(AlertType.INFORMATION, "there is no book with this category ",
								ButtonType.CLOSE);
						alert1.setTitle("number of category");
						alert1.showAndWait();
					}
				} else if (displayBook.getValue().trim().equals("Number of books by author")) {
					int co = 0;
					comboselaect.setText("enter author:");
					for (int i = 0; i < BookView.listO.size(); i++) {
						if (BookView.listO.get(i).getAuther().equalsIgnoreCase(bookSearch.getText().trim())) {
							co++;
						}
					}
					if (co > 0) {
						result.setText(co + "");
					} else if (co <= 0) {
						Alert alert1 = new Alert(AlertType.INFORMATION, "there is no book with this auther ",
								ButtonType.CLOSE);
						alert1.setTitle("number of auther");
						alert1.showAndWait();
					}
				} else if (displayBook.getValue().trim().equals("Number of books published in specific year")) {
					int co = 0;
					comboselaect.setText("enter year:");
					for (int i = 0; i < BookView.listO.size(); i++) {
						if (BookView.listO.get(i).getPublishedYear() == Integer.parseInt(bookSearch.getText().trim())) {
							co++;
						}
					}
					if (co > 0) {
						result.setText(co + "");
					} else if (co <= 0) {
						Alert alert1 = new Alert(AlertType.INFORMATION, "there is no book with this year ",
								ButtonType.CLOSE);
						alert1.setTitle("number of year");
						alert1.showAndWait();
					}
				} else if (displayBook.getValue().trim().equals(
						"The year with the maximum number of books published, along with the count of books published in that year")) {
					int maxYear = 0;
					int counter = 0;
					for (int i = 0; i < BookView.listO.size(); i++) {
						int yearN = BookView.listO.get(i).getPublishedYear();
						int c = 0;
						for (int j = 0; j < BookView.listO.size(); j++) {
							if (BookView.listO.get(j).getPublishedYear() == yearN) {
								c++;
							}
						}
						if (c > counter) {
							counter = c;
							maxYear = yearN;
						}
					}
					result.setText(maxYear + "");

				} else if (displayBook.getValue().trim().equals(
						"The year with the minimum number of books published, along with the count of books published in that year")) {
					int minYear = 0;
					int counter = Integer.MAX_VALUE;
					//loop to find the writer who wrote the least number of books
					for (int i = 0; i < BookView.listO.size(); i++) {
						int yearN = BookView.listO.get(i).getPublishedYear();
						int c = 0;
						for (int j = 0; j < BookView.listO.size(); j++) {
							if (BookView.listO.get(j).getPublishedYear() == yearN) {
								c++;
							}
						}
						if (c < counter) {
							counter = c;
							minYear = yearN;
						}
					}
					result.setText(minYear + "");
				} else if (displayBook.getValue().trim().equals(
						"The author with the maximum number of books published along with the titles of those books")) {
					ta.setText(null);
					String authorNam = null;
					int counter = 0;
					//loop to find the writer who wrote the largest number of books
					for (int i = 0; i < BookView.listO.size(); i++) {
						String autherSave = BookView.listO.get(i).getAuther();
						int c = 0;
						for (int j = 0; j < BookView.listO.size(); j++) {
							if (BookView.listO.get(j).getAuther().trim().equals(autherSave)) {
								c++;
							}
						}
						if (c > counter) {
							counter = c;
							authorNam = autherSave;
						}
					}
					//loop to add the author and book titles to text area
					for (int j = 0; j < BookView.listO.size(); j++) {
						if (BookView.listO.get(j).getAuther().trim().equals(authorNam)) {
							ta.appendText(
									BookView.listO.get(j).getAuther() + ", " + BookView.listO.get(j).getTitle() + "\n");
						}
					}
					//create stage, scene and VBox to show the Text Area
					VBox vbT = new VBox();
					vbT.getChildren().add(ta);
					vbT.setAlignment(Pos.CENTER);
					Scene textScene = new Scene(vbT, 450, 200);
					Stage textStage = new Stage();
					textStage.setScene(textScene);
					textStage.show();
				} else if (displayBook.getValue().trim().equals(
						"The author with the minimum number of books published along with the titles of those books")) {
					ta.setText(null);
					String authorNam = null;
					int counter = Integer.MAX_VALUE;
					boolean isMen=false;// to check if its min or not
					for (int i = 0; i < BookView.listO.size(); i++) {
						String autherSave = BookView.listO.get(i).getAuther();
						int c = 0;
						for (int j = 0; j < BookView.listO.size(); j++) {
							if (BookView.listO.get(j).getAuther().trim().equals(autherSave)) {
								c++;
							}
						}
						if (c < counter) {
							counter = c;
							authorNam = autherSave;
						}
					}
					for (int j = 0; j < BookView.listO.size(); j++) {
						if (BookView.listO.get(j).getAuther().trim().equals(authorNam)) {
							ta.appendText(
									BookView.listO.get(j).getAuther() + ", " + BookView.listO.get(j).getTitle() + "\n");
						}
					}
					//create stage, scene and VBox to show the Text Area
					VBox vbT = new VBox();
					vbT.getChildren().add(ta);
					vbT.setAlignment(Pos.CENTER);
					Scene textScene = new Scene(vbT, 450, 200);
					Stage textStage = new Stage();
					textStage.setScene(textScene);
					textStage.show();
				}

			});
			// author is still active
			Image active = new Image("auther.png");
			ImageView iv7 = new ImageView(active);
			iv7.setFitWidth(80);
			iv7.setFitHeight(80);
			Button activeAuthor = new Button("active or not", iv7);
			activeAuthor.setContentDisplay(ContentDisplay.TOP);
			activeAuthor.setPrefWidth(80);
			activeAuthor.setPrefHeight(80);
			activeAuthor.setOnAction(e -> {
				ta.setText(null);
				for (int i = 0; i < BookView.listO.size(); i++) {
					if (activeORNot(BookView.listO.get(i).getAuther())) {
						ta.appendText(BookView.listO.get(i) + "\n");
					}
				}
				VBox vbT = new VBox();
				vbT.getChildren().add(ta);
				vbT.setAlignment(Pos.CENTER);
				Scene textScene = new Scene(vbT, 450, 200);
				Stage textStage = new Stage();
				textStage.setScene(textScene);
				textStage.show();
			});
			// save the updated data
			HBox buttom = new HBox();
			Image save = new Image("save.png");
			ImageView iv8 = new ImageView(save);
			iv8.setFitWidth(80);
			iv8.setFitHeight(80);
			Button saveOnFile = new Button("save On File", iv8);
			saveOnFile.setContentDisplay(ContentDisplay.TOP);
			saveOnFile.setPrefWidth(80);
			saveOnFile.setPrefHeight(80);
			buttom.getChildren().addAll(saveOnFile);
			buttom.setAlignment(Pos.CENTER);
			buttom.setTranslateY(-50);
			// update set on action
			saveOnFile.setOnAction(e -> {
				obj.writeToFile();
			});
			// Additional Features
			// SORT Menu
			MenuBar sorted = new MenuBar();
			Image sort = new Image("sort.png");
			ImageView iv0 = new ImageView(sort);
			iv0.setFitWidth(90);
			iv0.setFitHeight(90);
			sorted.setTranslateY(10);
			Menu sortAll = new Menu("", iv0);
			MenuItem sTitel = new MenuItem("sorting by Title");
			sTitel.setOnAction(e -> {
				BookView.listO.sort(new Comparator<Book>() {

					@Override
					public int compare(Book o1, Book o2) {
						// TODO Auto-generated method stub
						return o1.getTitle().compareTo(o2.getTitle());
					}

				});
			});
			MenuItem sAuther = new MenuItem("sorting by Author");
			sAuther.setOnAction(e -> {
				BookView.listO.sort(new Comparator<Book>() {

					@Override
					public int compare(Book o1, Book o2) {
						// TODO Auto-generated method stub
						return o1.getAuther().compareTo(o2.getAuther());
					}

				});
			});
			MenuItem sYear = new MenuItem("sorting by Published Year");
			sYear.setOnAction(e -> {
				BookView.listO.sort(new Comparator<Book>() {

					@Override
					public int compare(Book o1, Book o2) {
						// TODO Auto-generated method stub
						return Integer.compare(o1.getPublishedYear(), o2.getPublishedYear());
					}

				});
			});
			sortAll.getItems().addAll(sTitel, sAuther, sYear);
			sorted.getMenus().add(sortAll);
			sorted.setPrefWidth(200);
			// Button to edit book
			Image editBook = new Image("edit.png");
			ImageView iv9 = new ImageView(editBook);
			iv9.setFitWidth(80);
			iv9.setFitHeight(80);
			Button edit = new Button("edit ", iv9);
			edit.setContentDisplay(ContentDisplay.TOP);
			edit.setPrefWidth(80);
			edit.setPrefHeight(80);
			edit.setOnAction(e -> {
				searchText = textSearch.getText();
				for (int i = 0; i < AddNew.list2.size(); i++) {
					if (AddNew.list2.get(i).getBookId().equals(searchText)) {
						EditBook obb3 = new EditBook();
						try {
							primaryStage.setScene(obb3.getScene3(primaryStage, AddNew.list2.get(i)));
							primaryStage.setFullScreen(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				BookView.listO.clear();
				BookView.listO.addAll(AddNew.list2);
			});
			// functionSearch Action Create a search function that allows searching for
			// books by keywords in the Title or
			// Author fields.
			functionSearch.setOnAction(e -> {
				BookView.listO.clear();
				for (int i = 0; i < AddNew.list2.size(); i++) {
					if (AddNew.list2.get(i).getTitle().contains(searchFunction.getText())) {
						BookView.listO.add(AddNew.list2.get(i));
					} else if (AddNew.list2.get(i).getAuther().contains(searchFunction.getText())) {
						BookView.listO.add(AddNew.list2.get(i));
					}
				}
				if (searchFunction.getText().equals(null)) {
					BookView.listO.addAll(AddNew.list2);
				}
			});
			// the interface
			HBox hb2 = new HBox(10);
			hb2.getChildren().addAll(deletBook, searchBook, edit);
			vb.getChildren().addAll(textSearch, hb2);
			HBox hb3 = new HBox(20);
			HBox hbC = new HBox();
			hbC.getChildren().addAll(comboselaect, bookSearch);
			VBox combo = new VBox(20);
			bookSearch.setPrefWidth(100);
			hbC.setAlignment(Pos.CENTER);
			HBox resultBox = new HBox();
			resultBox.getChildren().add(result);
			result.setFont(new Font(25));
			result.setStyle("-fx-fill-color: #2C3930");
			resultBox.setAlignment(Pos.CENTER);
			result.setPrefWidth(100);
			combo.getChildren().addAll(hbC, displayBook, resultBox);
			hb3.getChildren().addAll(sorted, chooser, insertBook, activeAuthor, saveOnFile, combo);
			HBox hb = new HBox(150);
			hb.getChildren().addAll(vb, hb3);
			HBox searchFun = new HBox();
			searchFun.getChildren().addAll(new Label("search:"), searchFunction, functionSearch);
			searchFun.setAlignment(Pos.CENTER_LEFT);
			searchFun.setTranslateX(-100);
			HBox view = new HBox();
			view.getChildren().addAll(searchFun, obj2.getTable());
			view.setAlignment(Pos.CENTER);
			root.getChildren().addAll(hb, view);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public boolean activeORNot(String name) {
		Calendar c = new GregorianCalendar();
		int carrentYear = c.getWeekYear();
		int activeYear = carrentYear - 5;
		for (int i = 0; i < BookView.listO.size(); i++) {
			if (BookView.listO.get(i).getAuther().trim().equals(name)
					&& BookView.listO.get(i).getPublishedYear() <= carrentYear
					&& BookView.listO.get(i).getPublishedYear() >= activeYear) {
				return true;
			}
		}
		return false;

	}
}
