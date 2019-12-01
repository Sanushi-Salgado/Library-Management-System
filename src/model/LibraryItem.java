package model;

import main.DateTime;

public abstract class LibraryItem {

	private static int MAXIMUM_NUMBER_OF_ITEMS = 150;
	private static int currentNumberOfItemsAvailable = 0;
	private String isbn;
	private String title;
	private String sector;
	private DateTime publicationDate;
	private DateTime dateBorrowed;
	private DateTime returnDate;
	private Reader reader;
	private String type;

	public LibraryItem() {

	}

	public LibraryItem(String isbn, DateTime dateBorrowed, DateTime returnDate, Reader reader) {
		this.isbn = isbn;
		this.dateBorrowed = dateBorrowed;
		this.returnDate = returnDate;
		this.reader = reader;
	}

	public static int getMAXIMUM_NUMBER_OF_ITEMS() {
		return MAXIMUM_NUMBER_OF_ITEMS;
	}

	public static void setMAXIMUM_NUMBER_OF_ITEMS(int tOTAL_NUMBER_OF_ITEMS) {
		MAXIMUM_NUMBER_OF_ITEMS = tOTAL_NUMBER_OF_ITEMS;
	}

	public static int getCurrentNumberOfItemsAvailable() {
		return currentNumberOfItemsAvailable;
	}

	public static void setCurrentNumberOfItemsAvailable(int currentNumberOfItemsAvailable) {
		LibraryItem.currentNumberOfItemsAvailable = currentNumberOfItemsAvailable;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public DateTime getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(DateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public DateTime getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(DateTime dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public DateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(DateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public String getType() {
		return type;
	}

	public void setType(String itemType) {
		this.type = itemType;
	}

}
