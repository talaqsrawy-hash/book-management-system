package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class BookView {
	static ObservableList<Book> listO;
	TableView<Book> tv = new TableView<Book>();

	public BookView() {

	}
//metode to return table view 
	public TableView<Book> getTable() {
		listO = FXCollections.observableArrayList();
		//create columns 
		TableColumn<Book, String> bookId = new TableColumn<>("Book ID");
		bookId.setMinWidth(120);
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		TableColumn<Book, String> title = new TableColumn<>("Title");
		title.setMinWidth(190);
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		TableColumn<Book, String> auther = new TableColumn<>("Auther");
		auther.setMinWidth(120);
		auther.setCellValueFactory(new PropertyValueFactory<>("auther"));
		TableColumn<Book, String> category = new TableColumn<>("Category");
		category.setMinWidth(120);
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		TableColumn<Book, Integer> publishedYear = new TableColumn<>("Published Year");
		publishedYear.setMinWidth(120);
		publishedYear.setCellValueFactory(new PropertyValueFactory<>("publishedYear"));
		TableColumn<Book, String> isBn = new TableColumn<>("IsBn");
		isBn.setMinWidth(120);
		isBn.setCellValueFactory(new PropertyValueFactory<>("isBn"));
		tv.getColumns().addAll(bookId, title, auther, category, publishedYear, isBn);
		tv.setItems(listO);
		tv.setPrefWidth(800);
		return tv;
	}
}
