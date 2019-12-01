package model;

import java.util.ArrayList;

import main.DateTime;

public class Book extends LibraryItem {

	private static int TOTAL_NUMBER_OF_BOOKS = 100;
	private ArrayList<String> authors = new ArrayList<>();
	private String publisher;
	private int totalNumberOfPages;
	private static int currentNumberOfBooksAvailable = 0;

	public Book(String type, String isbn, String title, String sector, DateTime publicationDate,
			ArrayList<String> authors, String publisher, int totalNumberOfPages) {
		this.setType(type);
		this.setIsbn(isbn);
		this.setTitle(title);
		this.setSector(sector);
		this.setPublicationDate(publicationDate);
		this.authors = authors;
		this.publisher = publisher;
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public Book(String isbn, DateTime dateBorrowed, DateTime returnDate, Reader reader) {
		this.setIsbn(isbn);
		this.setDateBorrowed(dateBorrowed);
		this.setReturnDate(returnDate);
		this.setReader(reader);
	}

	public static int getTOTAL_NUMBER_OF_BOOKS() {
		return TOTAL_NUMBER_OF_BOOKS;
	}

	public static void setTOTAL_NUMBER_OF_BOOKS(int tOTAL_NUMBER_OF_BOOKS) {
		TOTAL_NUMBER_OF_BOOKS = tOTAL_NUMBER_OF_BOOKS;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public static int getCurrentNumberOfBooksAvailable() {
		return currentNumberOfBooksAvailable;
	}

	public static void setCurrentNumberOfBooksAvailable(int currentNumberOfBooksAvailable) {
		Book.currentNumberOfBooksAvailable = currentNumberOfBooksAvailable;
	}

}
