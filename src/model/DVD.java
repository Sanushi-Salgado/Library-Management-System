package model;

import java.util.ArrayList;

import main.DateTime;

public class DVD extends LibraryItem {

	private static int TOTAL_NUMBER_OF_DVDS = 50;
	private ArrayList<String> availableLanguages = new ArrayList<>();
	private ArrayList<String> availableSubtitles = new ArrayList<>();
	private String producer;
	private ArrayList<String> actors = new ArrayList<>();
	private static int currentNumberOfDvdsAvailable = 0;

	public DVD(String type, String isbn, String title, String sector, DateTime publicationDate,
			ArrayList<String> availableLanguages, ArrayList<String> availableSubtitles, String producer,
			ArrayList<String> actors) {
		this.setType(type);
		this.setIsbn(isbn);
		this.setTitle(title);
		this.setSector(sector);
		this.setPublicationDate(publicationDate);
		this.availableLanguages = availableLanguages;
		this.availableSubtitles = availableLanguages;
		this.producer = producer;
		this.actors = actors;
	}

	public DVD(String isbn, DateTime dateBorrowed, DateTime returnDate, Reader reader) {
		this.setIsbn(isbn);
		this.setDateBorrowed(dateBorrowed);
		this.setReturnDate(returnDate);
		this.setReader(reader);
	}

	public static int getTOTAL_NUMBER_OF_DVDS() {
		return TOTAL_NUMBER_OF_DVDS;
	}

	public static void setTOTAL_NUMBER_OF_DVDS(int tOTAL_NUMBER_OF_DVDS) {
		TOTAL_NUMBER_OF_DVDS = tOTAL_NUMBER_OF_DVDS;
	}

	public ArrayList<String> getAvailableLanguages() {
		return availableLanguages;
	}

	public void setAvailableLanguages(ArrayList<String> availableLanguages) {
		this.availableLanguages = availableLanguages;
	}

	public ArrayList<String> getAvailableSubtitles() {
		return availableSubtitles;
	}

	public void setAvailableSubtitles(ArrayList<String> availableSubtitles) {
		this.availableSubtitles = availableSubtitles;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public ArrayList<String> getActors() {
		return actors;
	}

	public void setActors(ArrayList<String> actors) {
		this.actors = actors;
	}

	public static int getCurrentNumberOfDvdsAvailable() {
		return currentNumberOfDvdsAvailable;
	}

	public static void setCurrentNumberOfDvdsAvailable(int currentNumberOfDvdsAvailable) {
		DVD.currentNumberOfDvdsAvailable = currentNumberOfDvdsAvailable;
	}

}
