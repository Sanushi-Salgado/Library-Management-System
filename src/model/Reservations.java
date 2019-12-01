package model;

import java.util.ArrayList;

public class Reservations {
	
	private static int reservationId = 0;
	private String isbn;
	private Reader reader;
	public static ArrayList<Reservations> allReservations = new ArrayList<>();
	

	public Reservations(int reservationId, String isbn, Reader reader) {
		super();
		Reservations.reservationId = reservationId;
		this.isbn = isbn;
		this.reader = reader;
	}

	public static int getReservationId() {
		return reservationId;
	}

	public static void setReservationId(int reservationId) {
		Reservations.reservationId = reservationId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	/*public static ArrayList<Reservations> getAllReservations() {
		return allReservations;
	}

	public static void setAllReservations(ArrayList<Reservations> allReservations) {
		Reservations.allReservations = allReservations;
	}*/
	
}
