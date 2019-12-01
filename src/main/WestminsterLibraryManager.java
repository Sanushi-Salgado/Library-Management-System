package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javafx.scene.control.Tab;
import model.Book;
import model.DVD;
import model.LibraryItem;
import model.Reader;
import model.Reservations;

public class WestminsterLibraryManager implements LibraryManager {

	// List of all items available in the library
	static ArrayList<LibraryItem> libraryItems = new ArrayList<>();

	// List of borrowed items in the library
	static ArrayList<LibraryItem> borrowedItems = new ArrayList<>();
	
	// Contains all the unique isbn numbers
	static Set<String> uniqueIsbnNumbers = new LinkedHashSet<>();

	static Map<Double, String> treeMap = new TreeMap<>(Collections.reverseOrder());

	static int optionNumber;
	static String isbn;
	static String itemType;
	static LibraryItem itemBorrowed;
	static boolean found;
	static boolean isExists;
	static boolean isBorrowed;
	static double fee;
	static int hours;
	static int minutes;
	static int seconds;
	static int day;
	static int month;
	static int year;
	static String content;
    static String currentDateNTime;
    static Scanner input = new Scanner(System.in);

	// Displays the main menu from which the user can select options
	public static void displayMainMenu() {
		System.out.println("---------Enter a number as mentioned in the  below list to proceed----------\n");
		System.out.println("Enter 1 - to add a new item");
		System.out.println("Enter 2 - to delete an item");
		System.out.println("Enter 3 - to print a list of items");
		System.out.println("Enter 4 - to borrow an item");
		System.out.println("Enter 5 - to return an item");
		System.out.println("Enter 6 - to generate a report");
		System.out.println("Enter 7 - to open the GUI");
		System.out.println("Enter 8 - to make a reservation\n");
		System.out.println("---------------------------------------------------------------------------");

		// Validate input entered by the user
		validateInput(1, 8);

		WestminsterLibraryManager object = new WestminsterLibraryManager();
		// Call relevant methods based on the entered option number by the user
		switch (optionNumber) {
			case 1:
				object.addNewItem();
				break;
			case 2:
				object.deleteItem();
				break;
			case 3:
				object.printListOfItems();
				break;
			case 4:
				object.borrowItem();
				break;
			case 5:
				object.returnItem();
				break;
			case 6:
				object.generateReport();
				break;
			case 7:
				object.openUserInterface();
				break;
			case 8:
				object.makeReservation();
				break;
		}
	}

	@Override
	public void addNewItem() {

		// Calculate the number of free spaces remaining in order to check if we can add a new item
		int numberOfFreeSpacesRemaining = LibraryItem.getMAXIMUM_NUMBER_OF_ITEMS()
				- LibraryItem.getCurrentNumberOfItemsAvailable();

		// Display an error message if there is no free space available
		if (numberOfFreeSpacesRemaining == 0) {
			System.out.println("*********UNABLE TO ADD A NEW ITEM DUE TO INSUFFICIENT SPACE**********\n\n");
			displayFreeSpaces();
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
			displayMainMenu();
		}

		else {
			System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  ADD NEW ITEM  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
			System.out.println("----------Enter a number as mentioned below in order to proceed--------------\n");
			System.out.println("Enter 1 - to add a new BOOK");
			System.out.println("Enter 2 - to add a new DVD\n");

			// Validate input entered by the user
			validateInput(1, 2);


			if (optionNumber == 1) {
				// Check if there is sufficient space to add a new book
				if (Book.getCurrentNumberOfBooksAvailable() == 100) {
					System.out.println("*********UNABLE TO ADD A NEW BOOK DUE TO INSUFFICIENT SPACE**********\n");
					displayFreeSpaces();
				} else {
					// Get information about the new library item
					System.out.println("Enter ISBN: ");
					String isbn = input.next();
					input.nextLine();
					
					// Checking if the ISBN is unique
					while(validateIsbnNumbers(isbn)) {
						System.out.println("Enter a valid ISBN: ");
						isbn = input.nextLine();
						input.nextLine();
					}
					
					uniqueIsbnNumbers.add(isbn);

					System.out.println("Enter title: ");
					String title = input.nextLine();
					input.nextLine();

					System.out.println("Enter sector: ");
					String sector = input.nextLine();
					input.nextLine();

					System.out.println("Enter publication date (dd/MM/yyyy): ");
					String strPublicationDate = input.nextLine();
					input.nextLine();
					
					while(validateDate(strPublicationDate)) {
						System.out.println("Enter a valid publication date (dd/MM/yyyy): ");
						strPublicationDate = input.nextLine();
						input.nextLine();
					}

					// Extract the day, month, year from the entered string publication date
					int day = Integer.parseInt(strPublicationDate.substring(0, 2));
					int month = Integer.parseInt(strPublicationDate.substring(3, 5));
					int year = Integer.parseInt(strPublicationDate.substring(6));
					DateTime publicationDate = new DateTime(day, month, year);

					System.out.println("Enter author/s (seperated by commas): ");
					String author = input.nextLine();
					input.nextLine();
					ArrayList<String> authors = new ArrayList<>(Arrays.asList(author.split(",")));

					System.out.println("Enter publisher: ");
					String publisher = input.nextLine();
					input.nextLine();

					System.out.println("Enter total number of pages: ");
					int totalNumberOfPages = input.nextInt();
					input.nextLine();

					// Increase the number of books currently available in the library
					Book.setCurrentNumberOfBooksAvailable(Book.getCurrentNumberOfBooksAvailable() + 1);
					libraryItems.add(new Book("Book", isbn, title, sector, publicationDate, authors, publisher,
							totalNumberOfPages));

					// Increase the total number of items currently available in the library
					LibraryItem.setCurrentNumberOfItemsAvailable(LibraryItem.getCurrentNumberOfItemsAvailable() + 1);
					displayFreeSpaces();
				}

			} else if (optionNumber == 2) {

				// Check if there is sufficient space to add a new DVD
				if (DVD.getCurrentNumberOfDvdsAvailable() == 50) {
					System.out.println("!!!!!!!!!!!   UNABLE TO ADD A NEW DVD DUE TO INSUFFICIENT SPACE   !!!!!!!!\n");
					displayFreeSpaces();
				} else {
					// Get information about the new library item
					System.out.println("Enter ISBN: ");
					String isbn = input.next();
					input.nextLine();
					
					// Checking if the ISBN is unique
					while(validateIsbnNumbers(isbn)) {
						System.out.println("Enter a valid ISBN: ");
						isbn = input.nextLine();
						input.nextLine();
					}
					
					uniqueIsbnNumbers.add(isbn);

					System.out.println("Enter title: ");
					String title = input.nextLine();
					input.nextLine();

					System.out.println("Enter sector: ");
					String sector = input.nextLine();
					input.nextLine();

					System.out.println("Enter publication date (dd/MM/yyyy): ");
					String strPublicationDate = input.nextLine();
					input.nextLine();

					while(validateDate(strPublicationDate)) {
						System.out.println("Enter a valid publication date (dd/MM/yyyy): ");
						strPublicationDate = input.nextLine();
						input.nextLine();
					}


					// Extract the day, month, year from the entered string publication date
					int day = Integer.parseInt(strPublicationDate.substring(0, 2));
					int month = Integer.parseInt(strPublicationDate.substring(3, 5));
					int year = Integer.parseInt(strPublicationDate.substring(6));
					DateTime publicationDate = new DateTime(day, month, year);

					System.out.println("Enter available language/s (seperated by commas): ");
					String language = input.nextLine();
					input.nextLine();
					ArrayList<String> languages = new ArrayList<>(Arrays.asList(language.split(",")));

					System.out.println("Enter subtitles (seperated by commas): ");
					String subtitle = input.nextLine();
					input.nextLine();
					ArrayList<String> subtitles = new ArrayList<>(Arrays.asList(subtitle.split(",")));

					System.out.println("Enter producer: ");
					String producer = input.nextLine();
					input.nextLine();

					System.out.println("Enter actor/s (seperated by commas): ");
					String actor = input.nextLine();
					input.nextLine();
					ArrayList<String> actors = new ArrayList<>(Arrays.asList(actor.split(",")));

					// Increase the number of DVDs currently available in the library
					DVD.setCurrentNumberOfDvdsAvailable(DVD.getCurrentNumberOfDvdsAvailable() + 1);
					libraryItems.add(new DVD("DVD", isbn, title, sector, publicationDate, languages, subtitles,
							producer, actors));

					// Increase the total number of items currently available in the library
					LibraryItem.setCurrentNumberOfItemsAvailable(LibraryItem.getCurrentNumberOfItemsAvailable() + 1);
					displayFreeSpaces();

				}
			}

			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
			displayMainMenu();
		}
	}

	@Override
	public void deleteItem() {
		System.out.printf("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  DELETE ITEM  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
		String isbn;

		// Check if an item with the given isbn number exists
		do{
			System.out.println("Enter isbn: ");
			isbn = input.next();
			input.nextLine();
		} while (!hasItem(isbn));


		for (int i = 0; i < libraryItems.size(); i++) {
			if (libraryItems.get(i).getIsbn().equals(isbn)) {

				String itemType = libraryItems.get(i).getType();
				if (itemType.equals("Book"))
					Book.setCurrentNumberOfBooksAvailable(Book.getCurrentNumberOfBooksAvailable() - 1);
				else if (itemType.equals("DVD"))
					DVD.setCurrentNumberOfDvdsAvailable(DVD.getCurrentNumberOfDvdsAvailable() - 1);

				// Get the type of the item that has been deleted
				System.out.println("Type of item that has been deleted: " + itemType);

				// Remove the deleted item from the library items list
				libraryItems.remove(libraryItems.get(i));
				// Remove the isbn number of the deleted item from the uniqueIsbnNumbers Set as it doesn't exist anymore
				uniqueIsbnNumbers.remove(isbn);

				// Decrease the total number of items in the library when an item has been
				// deleted
				LibraryItem.setCurrentNumberOfItemsAvailable(LibraryItem.getCurrentNumberOfItemsAvailable() - 1);

				// Get the number of free spaces left
				System.out.println("\n\nTotal number of free spaces remaining = "
						+ (LibraryItem.getMAXIMUM_NUMBER_OF_ITEMS() - LibraryItem.getCurrentNumberOfItemsAvailable())
						+ "\n");
				System.out.println("Number of free spaces remaining for BOOKS = "
						+ (Book.getTOTAL_NUMBER_OF_BOOKS() - Book.getCurrentNumberOfBooksAvailable()) + "\n");
				System.out.println("Number of free spaces remaining for DVDS = "
						+ (DVD.getTOTAL_NUMBER_OF_DVDS() - DVD.getCurrentNumberOfDvdsAvailable()) + "\n");
				System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
				displayMainMenu();
				break;

			}
		}
	}

	@Override
	public void printListOfItems() {
		System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  ALL LIBRARY ITEMS  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

		// Handle if there are no items in the list
		if (libraryItems.size() == 0)
			System.out.println("---------------  CURRENTLY NO ITEMS AVAILABLE IN THE LIBRARY ------------------\n");

		else {
			// Display all the items in the library along with it's isbn, title & item type


			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
			displayMainMenu();
		}
	}

	@Override
	public void borrowItem() {
		System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  BORROW ITEM  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

		// Check if an item with the given isbn number exists
		do{
			System.out.println("Enter isbn: ");
			isbn = input.next();
			input.nextLine();
		}while (!hasItem(isbn));


		// Get the item type using the isbn number
		libraryItems.forEach(item -> {
			if (isbn.equals(item.getIsbn())) {
				itemType = item.getType();
				itemBorrowed = item;
			}
		});

		// Check if the item is available or not
		borrowedItems.forEach(item -> {
			if (isbn.equals(item.getIsbn())) {
				found = true;
				System.out.println(
						"!!!!!!!!!!!!!!!!!!  ITEM CURRENTLY NOT AVAILABLE TO BORROW  !!!!!!!!!!!!!!!!!!!!!!\n");

				// Display a message informing when the item will be available
				DateTime returnDateOfItem = item.getReturnDate();
				System.out.println("It will be available on: " + returnDateOfItem.getDate() + "\n\n");
				System.out.println(
						"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
				displayMainMenu();
			}

			else
				found = false;

		});

		if (!found) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!   ITEM CURRENTLY AVAILABLE TO BORROW   !!!!!!!!!!!!!!!!!\n");

			System.out.println("\nEnter reader name: ");
			String readerName = input.nextLine();
			input.nextLine();

			System.out.println("Enter borrow date & time (dd/MM/yyyy hh:mm:ss): ");
			String borrowedDate = input.nextLine();
			input.nextLine();
			
			while(validateDateNTime(borrowedDate)) {
				System.out.println("Enter a valid borrow date & time (dd/MM/yyyy hh:mm:ss): ");
				borrowedDate = input.nextLine();
				input.nextLine();
			}

			// Extract the day, month, year from the entered string publication date
			int day = Integer.parseInt(borrowedDate.substring(0, 2));
			int month = Integer.parseInt(borrowedDate.substring(3, 5));
			int year = Integer.parseInt(borrowedDate.substring(6, 10));

			int hours = Integer.parseInt(borrowedDate.substring(11, 13));
			int minutes = Integer.parseInt(borrowedDate.substring(14, 16));
			int seconds = Integer.parseInt(borrowedDate.substring(17, 19));

			//14:10:23
			//00:45:59
			//12:20:53
			//23:59:59 -> 24:00:00 -> +1 day

			// BOOK
			//hours += 24 * 7;
			
			//DVD
			//hours += 24 * 3;
			
			// BOOK -> 01/07/2018 14:10:23 -> 08/07/2018 14:10:23
			// DVD  -> 01/07/2018 14:10:23 -> 04/07/2018 14:10:23
			
			// Calculating the return date of the item
			DateTime dateBorrowed = new DateTime(day, month, year, hours, minutes, seconds);

			if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)
					&& itemType.equals("Book")) {
				if (day == 31) {
					month += 1;
					day = 07;
				} else
					day += 07;

				borrowedItems.add(new Book(isbn, dateBorrowed, new DateTime(day, month, year, hours, minutes, seconds), new Reader(readerName)));

			} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)
					&& itemType.equals("DVD")) {
				if (day == 31) {
					month += 1;
					day = 03;
				} else
					day += 03;

				borrowedItems.add(new DVD(isbn, dateBorrowed, new DateTime(day, month, year, hours, minutes, seconds), new Reader(readerName)));				
			} 
			
			else if ((month == 4 || month == 6 || month == 9 || month == 11) && itemType.equals("Book")) {
				if (day == 30) {
					month += 1;
					day = 07;
				} else
					day += 07;

				borrowedItems.add(new Book(isbn, dateBorrowed, new DateTime(day, month, year, hours, minutes, seconds), new Reader(readerName)));
			}

			else if ((month == 4 || month == 6 || month == 9 || month == 11) && itemType.equals("DVD")) {
				if (day == 30) {
					month += 1;
					day = 03;
				} else
					day += 03;

				borrowedItems.add(new DVD(isbn, dateBorrowed, new DateTime(day, month, year, hours, minutes, seconds), new Reader(readerName)));
			}
			
			// If the borrowed date of a BOOK is something like this: 31/12/2018
			// Then the return date for the BOOK should be:   07/01/2019
			else if(month == 12 && day == 31 && itemType.equals("Book")) {
				year += 1;
				day = 07;
				month = 01;
				borrowedItems.add(new DVD(isbn, dateBorrowed, new DateTime(day, month, year, hours, minutes, seconds), new Reader(readerName)));
			}
			
			// If the borrowed date of a DVD is something like this: 31/12/2018
			// Then the return date for the DVD should be:   03/01/2019
			else if(month == 12 && day == 31 && itemType.equals("DVD")) {
				year += 1;
				day = 03;
				month = 01;
				borrowedItems.add(new DVD(isbn, dateBorrowed, new DateTime(day, month, year, hours, minutes, seconds), new Reader(readerName)));
			}
			
		}

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		displayMainMenu();

	}

	@Override
	public void returnItem() {
		System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  RETURN ITEM  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");

		// Check if an item exists in the library with the given isbn and if there's an item
		// Check whether it has been borrowed before returning
//		do{
			System.out.println("Enter isbn: ");
			isbn = input.next();
			input.nextLine();
//		}while (!hasItem(isbn) && !isBorrowed(isbn));

		// Check if the item has been returned after the period allowed
		// So first we have to get the current date & time
		System.out.println("Enter current date & time (dd/MM/yyyy hh:mm:ss): ");
		String currentDate = input.nextLine();
		input.nextLine();
		
		while(validateDateNTime(currentDate)) {
			System.out.println("Enter a valid borrow date & time (dd/MM/yyyy hh:mm:ss): ");
			currentDate = input.nextLine();
			input.nextLine();
		}
		
		// Extract the day, month, year, hours, minutes, seconds from the current date
		day = Integer.parseInt(currentDate.substring(0, 2));
		month = Integer.parseInt(currentDate.substring(3, 5));
		year = Integer.parseInt(currentDate.substring(6, 10));

		hours = Integer.parseInt(currentDate.substring(11, 13));
		minutes = Integer.parseInt(currentDate.substring(14, 16));
		seconds = Integer.parseInt(currentDate.substring(17, 19));

		 
		// Then get the return date & time of that item
		borrowedItems.forEach(item -> {
			if (isbn.equals(item.getIsbn())) {
				DateTime returnDate = item.getReturnDate();
				String rDate = returnDate.getDate(); // Date
				String rTime = returnDate.getTime(); // Time
				String rDateTime = returnDate.getDate() +  " " + returnDate.getTime();
				System.out.println("Return date: " + returnDate.getDate());
				System.out.println("Return time: " + returnDate.getTime());
				
				
				// Extract the day, month, year, hours, minutes, seconds from the current date
				int day2 = Integer.parseInt(rDateTime.substring(0, 2));
				int month2 = Integer.parseInt(rDateTime.substring(3, 5));
				int year2 = Integer.parseInt(rDateTime.substring(6, 10));

				int hours2 = Integer.parseInt(rDateTime.substring(11, 13));
				int minutes2 = Integer.parseInt(rDateTime.substring(14, 16));
				int seconds2 = Integer.parseInt(rDateTime.substring(17, 19));
				
				// We have to check if the current date & time is before the due return date & time or not
				// Get the time difference
				int dayDifference =  0;
				int differenceInNoOfHours = 0;
				int differenceInNoOfMinutes = 0;
				int differenceInNoOfSeconds = 0;
				
				// 08/07/2019 07:45:10 -> 04/07/2019 14:53:23
				if(day > day2 && month == month2 && year == year2) {
					dayDifference = day - Integer.parseInt(rDate.substring(0, 2));
					if(seconds < seconds2) {
						seconds += 60;
						minutes -= 1;
						
						differenceInNoOfSeconds = seconds - seconds2;
						System.out.println("differenceInNoOfSeconds: " + differenceInNoOfSeconds);
					}
					else {
						differenceInNoOfSeconds = seconds - seconds2;
						System.out.println("differenceInNoOfSeconds: " + differenceInNoOfSeconds);
					}
					
					
					if(minutes < minutes2) {
						minutes += 60;
						hours -= 1;
						differenceInNoOfMinutes = minutes - minutes2;
						System.out.println("differenceInNoOfMinutes: " + differenceInNoOfMinutes);
					}
					else {
						differenceInNoOfMinutes = minutes - minutes2;
						System.out.println("differenceInNoOfMinutes: " + differenceInNoOfMinutes);
				
					}
					
					
					if(hours < hours2) {
						hours += 24;
						day -= 1;
						differenceInNoOfHours = hours - hours2;
						System.out.println("differenceInNoOfHours: " + differenceInNoOfHours);
					}
					else {
						differenceInNoOfHours = hours - hours2;
						System.out.println("differenceInNoOfHours: " + differenceInNoOfHours);
				
					}
					
					dayDifference = day - day2;
					System.out.println("dayDifference: " + dayDifference);
					System.out.println("Total period related to the fine: " + dayDifference + " days " + differenceInNoOfHours + ":" + differenceInNoOfMinutes + ":" + differenceInNoOfSeconds);
					
					
					
					// The reader will have to pay £0.2 any extra hour for the first 3 days
					// So first we have to check if it has exceeded 3 days from the due date
					if (dayDifference <= 3) {
						fee = (dayDifference * 24) * 0.2 + differenceInNoOfHours * 0.2;
						System.out.println("Total fee £" + fee + "\n");
						System.out.println("You have to pay £" + Math.round(fee) + " as the fine!\n");
		            } 
					
					// The reader will have to pay �0.2 any extra hour for the first 3 days, then �0.5 per hour
					else if (dayDifference > 3) { // 5 days 2 hours
						fee = (24 * 3) * 0.2 + ((dayDifference - 3) * 24) * 0.5 + (differenceInNoOfHours * 0.5);
						System.out.println("Total fee £" + fee + "\n");
		                System.out.println("You have to pay £" + Math.round(fee) + " as the fine!\n");
		            }			
				}
				
				
				// 02/02/2019 -> 31/01/2019
			/*	else if(day != Integer.parseInt(rDate.substring(0, 2)) && month != Integer.parseInt(rDate.substring(3, 5)) && year == Integer.parseInt(rDate.substring(6, 10))) {
					
				}*/
				
				// 02/02/2019 -> 31/01/2018
				/*else if(day != Integer.parseInt(rDate.substring(0, 2)) && month != Integer.parseInt(rDate.substring(3, 5)) && year == Integer.parseInt(rDate.substring(6, 10))) {
					
				}*/
				
			 
			}


			// Make the item available again
			borrowedItems.remove(item);
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!   ITEM RETURNED SUCCESSFULLY   !!!!!!!!!!!!!!!!!!!!!!!!!!\n");
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
			displayMainMenu();

		});
	}

	@Override
	public void generateReport() {
		treeMap.clear();

		System.out.println("Enter current date & time(dd/MM/yyyy hh:mm:ss): ");
		currentDateNTime = input.nextLine();
		input.nextLine();

		// Validate date & time format entered by the user
		while(validateDateNTime(currentDateNTime)) {
			System.out.println("Enter a valid borrow date & time (dd/MM/yyyy hh:mm:ss): ");
			currentDateNTime = input.nextLine();
			input.nextLine();
		}

		// Get all overdue/unpaid/unsettled items
		borrowedItems.forEach(item -> {
			String itemName = item.getIsbn();
			DateTime returnDate = item.getReturnDate();
			String rDate = returnDate.getDate(); // Date
			String rTime = returnDate.getTime(); // Time
			String rDateTime = returnDate.getDate() +  " " + returnDate.getTime();
			System.out.printf("%10s  %20s %20s\n", itemName, rDate, rTime);

			day = Integer.parseInt(currentDateNTime.substring(0, 2));
			month = Integer.parseInt(currentDateNTime.substring(3, 5));
			year = Integer.parseInt(currentDateNTime.substring(6, 10));

			hours = Integer.parseInt(currentDateNTime.substring(11, 13));
			minutes = Integer.parseInt(currentDateNTime.substring(14, 16));
			seconds = Integer.parseInt(currentDateNTime.substring(17, 19));

			//due but not returned items
			// Check if the due date has exceeded
			// Extract the day, month, year, hours, minutes, seconds from the current date
			int day2 = Integer.parseInt(rDateTime.substring(0, 2));
			int month2 = Integer.parseInt(rDateTime.substring(3, 5));
			int year2 = Integer.parseInt(rDateTime.substring(6, 10));

			int hours2 = Integer.parseInt(rDateTime.substring(11, 13));
			int minutes2 = Integer.parseInt(rDateTime.substring(14, 16));
			int seconds2 = Integer.parseInt(rDateTime.substring(17, 19));

			// We have to check if the current date & time is before the due return date & time or not
			// Get the time difference
			int dayDifference =  0;
			int differenceInNoOfHours = 0;
			int differenceInNoOfMinutes = 0;
			int differenceInNoOfSeconds = 0;

			// 08/07/2019 07:45:10 -> 04/07/2019 14:53:23
			if(day > day2 && month == month2 && year == year2) {
				dayDifference = day - Integer.parseInt(rDate.substring(0, 2));
				if (seconds < seconds2) {
					seconds += 60;
					minutes -= 1;

					differenceInNoOfSeconds = seconds - seconds2;
					System.out.println("differenceInNoOfSeconds: " + differenceInNoOfSeconds);
				} else {
					differenceInNoOfSeconds = seconds - seconds2;
					System.out.println("differenceInNoOfSeconds: " + differenceInNoOfSeconds);
				}


				if (minutes < minutes2) {
					minutes += 60;
					hours -= 1;
					differenceInNoOfMinutes = minutes - minutes2;
					System.out.println("differenceInNoOfMinutes: " + differenceInNoOfMinutes);
				} else {
					differenceInNoOfMinutes = minutes - minutes2;
					System.out.println("differenceInNoOfMinutes: " + differenceInNoOfMinutes);

				}


				if (hours < hours2) {
					hours += 24;
					day -= 1;
					differenceInNoOfHours = hours - hours2;
					System.out.println("differenceInNoOfHours: " + differenceInNoOfHours);
				} else {
					differenceInNoOfHours = hours - hours2;
					System.out.println("differenceInNoOfHours: " + differenceInNoOfHours);

				}

				dayDifference = day - day2;
				System.out.println("dayDifference: " + dayDifference);
				System.out.println("Total Time Difference: " + dayDifference + " days " + differenceInNoOfHours + ":" + differenceInNoOfMinutes + ":" + differenceInNoOfSeconds);


				if (dayDifference > 0 || differenceInNoOfHours > 0 ) {

					// The reader will have to pay £0.2 any extra hour for the first 3 days
					// So first we have to check if it has exceeded 3 days from the due date
					if (dayDifference <= 3) {
						fee = (dayDifference * 24) * 0.2 + differenceInNoOfHours * 0.2;
						fee = Math.round(fee);
						System.out.println("You have to pay £" + fee + " as the fine!\n");
					}

					// The reader will have to pay �0.2 any extra hour for the first 3 days, then �0.5 per hour
					else if (dayDifference > 3) { // 5 days 2 hours
						fee = (24 * 3) * 0.2 + ((dayDifference - 3) * 24) * 0.5 + (differenceInNoOfHours * 0.5);
						fee = Math.round(fee);
						System.out.println("You have to pay £" + fee + " as the fine!\n");
					}


					treeMap.put(fee, itemName);

				}
			}
		});

		// Order the list from the item that has been borrowed for the longest period
		// Since the item that has been borrowed for the longest period has the largest fee
		// We iterate through the map based on the reverse order of the keys
		// which is same as the descending order of the fee

		// Display a message if there are no overdue items in the library
		if(treeMap.size() == 0)
			content = "\n\nCURRENTLY THERE ARE NO OVERDUE ITEMS IN THE LIBRARY\n\n" ;

		else {
			// Content which is to be written to the text file
			content = "";
			content = "OVERDUE ITEM NAME                                      FEE\n\n";
			for(Map.Entry<Double, String> entry : treeMap.entrySet()){
				Double fee = entry.getKey();
				String itemName = entry.getValue();
				content += itemName + "                                                " + fee + "\n" ;
			}
		}

		content += "\n\n------------------------------------------------------------";



		try {
			// Create a new text file if it does not exist
			File file = new File("D:\\Workspaces\\CourseWork\\LibraryManagementSystem\\src\\resources\\OverdueItems.txt");
			if(!file.exists())
				file.createNewFile();


			// Write the content to the text file
			Files.write(Paths.get("D:\\Workspaces\\CourseWork\\LibraryManagementSystem\\src\\resources\\OverdueItems.txt"), content.getBytes());

			System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  GENERATE REPORT  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!   REPORT GENERATED SUCCESSFULLY   !!!!!!!!!!!!!!!!!!!!\n\n");

		} catch (IOException e) {
			System.out.println("Error occured while writing to the text file: " + e.getMessage());
		}

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		displayMainMenu();
	}
	
	
	@Override
	public void openUserInterface() {
		new GraphicalUserInterface();
		displayMainMenu();
	}

	
	@Override
	public void makeReservation() {
		System.out.println("\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  MAKE RESERVATION  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n");
		System.out.println("Enter the isbn of the item to which you want to make a reservation: ");
		String isbn = input.next();
		input.nextLine();

		System.out.println("Enter reader name: ");
		String readerName = input.nextLine();
		input.nextLine();

		int reservationId = Reservations.getReservationId() + 1;

		// Check if a reservation to the same item has done before
		Reservations.allReservations.forEach(reservation -> {
			if(isbn.equals(reservation.getIsbn())){

			}
		});

		// If a previous reservation has not done to the item
		// Then calculate when the item will be available
		// For that, we have get the due date of the borrowed item
		borrowedItems.forEach(itemBorrowed ->{
			// Due date of the borrowed item
			String dueDate = itemBorrowed.getReturnDate().getDate();
			// Due time of the borrowed item
			String dueTime = itemBorrowed.getReturnDate().getTime();


		});

		Reservations.allReservations.add(new Reservations(reservationId, isbn, new Reader(readerName)));

		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		displayMainMenu();
	}
	

	// Calculates & displays the number of remaining spaces
	public void displayFreeSpaces() {
		System.out.println("\n\nTotal number of free spaces remaining = "
				+ (LibraryItem.getMAXIMUM_NUMBER_OF_ITEMS() - LibraryItem.getCurrentNumberOfItemsAvailable()) + "\n");
		System.out.println("Number of free spaces remaining for BOOKS = "
				+ (Book.getTOTAL_NUMBER_OF_BOOKS() - Book.getCurrentNumberOfBooksAvailable()) + "\n");
		System.out.println("Number of free spaces remaining for DVDS = "
				+ (DVD.getTOTAL_NUMBER_OF_DVDS() - DVD.getCurrentNumberOfDvdsAvailable()) + "\n");
	}

	
	// Checks whether the isbn numbers are unique
	public boolean validateIsbnNumbers(String isbn) {
		if(uniqueIsbnNumbers.contains(isbn)) {
			System.out.println("!!!!!!!!!!!!!   ITEM WITH THE SAME ISBN NUMBER EXISTS   !!!!!!!!!!!!\n\n");
			return true;
		}
		
		return false;
	}
	
	// Validates the date format
	public boolean validateDate(String date) {
		if(date.length() != 10) {
			System.out.println("!!!!!!!!!   ENTER A VALID DATE IN THE FORM OF dd/MM/yyyy   !!!!!!!!!\n\n");
			return true;
		}
		
		return false;
	}
	
	// Validates the date format
	public boolean validateDateNTime(String date) {
		if(date.length() != 19) {
			System.out.println("!!!!  ENTER A VALID DATE & TIME IN THE FORM OF dd/MM/yyyy hh:mm:ss  !!!!\n\n");
			return true;
		}
			
		return false;
	}

	// Validates option numbers entered by the user
	public static void validateInput(int low, int high){
		boolean validEntry = false;
		do {
			try {
				optionNumber = input.nextInt();
				if ((optionNumber >= low && optionNumber <= high))
					validEntry = true;
				else {
					validEntry = false;
					System.out.println("!!!!!!!!!!!!  PLEASE ENTER A VALID NUMBER  !!!!!!!!!!!!!!");
				}
			} catch (InputMismatchException e) {
				System.out.println("!!!!!!!!!!!!  PLEASE ENTER A VALID NUMBER  !!!!!!!!!!!!!!");
			}
			input.nextLine();
		} while (!validEntry);

	}

	// Check if an item exists in the library that corresponds to the given isbn number
	public static boolean hasItem(String isbn){
		isExists = false;
		libraryItems.forEach(item -> {
			if(isbn.equals(item.getIsbn()))
				isExists = true;
		});
		if(!isExists)
			System.out.println("!!!!!!!!!!!!!!!!!!!  ITEM DOESN'T EXIST  !!!!!!!!!!!!!!!!!!!!");
		return isExists;
	}

	// Check if the item returning is borrowed
	public static boolean isBorrowed(String isbn){
		isBorrowed = false;
		borrowedItems.forEach(item -> {
			if(isbn.equals(item.getIsbn()))
				isBorrowed = true;
		});
		if(!isBorrowed)
			System.out.println("!!!!!!!!!!!!!!!  ITEM HAS NOT BORROWED BEFORE TO RETURN  !!!!!!!!!!!!");
		return isBorrowed;
	}

	public static void main(String[] args) {
		System.out.println("xxxxxxxxxxxxxxx  WELCOME TO THE WESTMISTER LIBRARY MANAGEMENT SYSTEM xxxxxxxxxxxx\n");
		// Displays the main menu
		WestminsterLibraryManager.displayMainMenu();
	}

}