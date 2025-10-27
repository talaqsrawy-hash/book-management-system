package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book {
	private String bookId;
	private String title;
	private String auther;
	private String category;
	private int publishedYear;
	private String isBn;
	//create constructor with no argument   
	public Book() {
		super();
	}
	//create constructor with argument  
	public Book(String bookId, String title, String auther, String category, int publishedYear, String isBn) {
		super();
		try {
			setBookId(bookId);
			setTitle(title);
			setAuther(auther);
			setCategory(category);
			setPublishedYear(publishedYear);
			setIsBn(isBn);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//setter and getter with validation 
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) throws MyException {
		if (bookId.length() != 3) {
			throw new MyException("The Book ID must consist of three digits");
		} else if (isInt(bookId) == false) {
			throw new MyException("The Book ID must be an Integer");
		} else {
			this.bookId = bookId;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws MyException {
		if (title == null || title.isEmpty()) {
			throw new MyException("The Book titel is empty");
		}
		for (int i = 0; i < title.length(); i++) {
			if (Character.isLetter(title.charAt(i)) || title.charAt(i) == ' ' || Character.isDigit(title.charAt(i))) {
				this.title = title;
			} else {
				throw new MyException("The Book titel muse be letter");
			}
		}
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) throws MyException {
		if (auther == null || auther.isEmpty()) {
			throw new MyException("The Book auther is empty");
		}
		for (int i = 0; i < auther.length(); i++) {
			if (Character.isLetter(auther.charAt(i)) || auther.charAt(i) == ' ') {
				this.auther = auther;
			} else {
				throw new MyException("The Book auther muse be letter");
			}
		}
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws MyException {
		if (category == null || category.isEmpty()) {
			throw new MyException("The Book category is empty");
		}
		for (int i = 0; i < category.length(); i++) {
			if (Character.isLetter(category.charAt(i)) || category.charAt(i) == ' ') {
				this.category = category;
			} else {
				throw new MyException("The Book category muse be letter");
			}
		}
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) throws MyException {
		Calendar c = new GregorianCalendar();
		int carrentYear = c.getWeekYear();
		if (publishedYear > carrentYear || publishedYear < 1000) {
			throw new MyException("The Book Book year muse Greater than 1000 and less than " + carrentYear + "");
		}
		this.publishedYear = publishedYear;
	}

	public String getIsBn() {
		return isBn;
	}

	public void setIsBn(String isBn) throws MyException {
		if (isBn == null || !isBn.matches("^\\d{3}-\\d{10}$")) {
			throw new MyException("ISBN must be 13 digits");
		}
		this.isBn = isBn;
	}

	@Override
	public String toString() {
		return bookId + ", " + title + ", " + auther + ", " + category + ", " + publishedYear + ", " + isBn;
	}
	
	public boolean isInt(String sNum) {
		try {
			Integer.parseInt(sNum);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
