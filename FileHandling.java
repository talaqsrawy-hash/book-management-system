package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {
	//methode to read from file ,add the information to the array list and return the array list
	public ArrayList<Book> readFromFile(File file) {
		ArrayList<Book> listBook = new ArrayList<Book>();
		try {
			Scanner read = new Scanner(file);
			while (read.hasNext()) {
				String rFile = read.nextLine();
				String[] readArray = rFile.split(", ");
				listBook.add(new Book(readArray[0].trim(), readArray[1].trim(), readArray[2].trim(),
						readArray[3].trim(), Integer.parseInt(readArray[4].trim()), readArray[5].trim()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listBook;
	}
	//methode to write the information in the array list to the file
	public void writeToFile() {
		File file = new File("updatedBooks.txt");
		try {
			PrintWriter write = new PrintWriter(file);
			for (int i = 0; i < BookView.listO.size(); i++) {
				write.println(BookView.listO.get(i).toString());
			}
			write.flush();
			write.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
