package com.javarnd.coreJavalibrarymanagementsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.javarnd.book.Book;
import com.javarnd.book.BookDetails;
import com.javarnd.book.BookImpl;
import com.javarnd.commons.LibraryConstants;
import com.javarnd.commons.LibraryFormattedColumns;
import com.javarnd.library.Library;
import com.javarnd.library.LibraryDetails;
import com.javarnd.library.LibraryImpl;

public class App {
	static Map<Long, LibraryDetails> LibraryMap = new TreeMap<Long, LibraryDetails>();

	static Map<Long, BookDetails> BookMap = new TreeMap<Long, BookDetails>();

	static Map<Long, Set> LibraryBookAssociation = new TreeMap<Long, Set>();

	// static Scanner scanner = new Scanner(System.in);
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader bufferedReader = new BufferedReader(isr);
	static Library LibraryObj = new LibraryImpl();

	static Book BookObj = new BookImpl();

	static Long index = 0l;

	public static void main(String[] args) throws IOException {

		System.out.println("********************************************");
		System.out.println("  Welcome to JAVA RND Library  ");
		System.out.println("********************************************");
		System.out.println("--- Please select your option to take action --- ");

		mainMenu();

		// Scanner scanner = new Scanner(System.in);

		Boolean isNotExit = true;

		while (isNotExit) {
			System.out.println("********* Please Enter your Option *********");
			try {
				Integer selectedOption = Integer.valueOf(bufferedReader.readLine());

				action(selectedOption);
			} catch (Exception e) {
				System.out.println("Error Input : " + e.getMessage());
				continue;
			}

		}
	}

	private static void stopApplication() {

		System.exit(1);
	}

	private static void action(Integer selectedOption) throws IOException {

		switch (selectedOption) {
		case 0:
			System.out.println(" Please login");

			login();

			mainMenu();
			break;
		case 1:
			System.out.println("****Library Details****" + LibraryConstants.LINECHANGE);

			iterateAndDispLibraryMap(LibraryMap);

			mainMenu();
			break;

		case 2:
			System.out.println("****Library and Book Association Details****" + LibraryConstants.LINECHANGE);
			displayLibraryBookAss(LibraryBookAssociation);

			mainMenu();
			break;
		case 3:
			System.out.println("****Display Existing Available Books****" + LibraryConstants.LINECHANGE);

			displayAvailableBookInfo(BookMap);
			break;
		case 4:
			System.out.println("****Display Existing Issued Books****" + LibraryConstants.LINECHANGE);

			displayIssuedBookInfo(BookMap);

			mainMenu();
			break;
		case 5:
			System.out.println("****Add New Libray****" + LibraryConstants.LINECHANGE);

			addLibrary();
			mainMenu();
			break;
		case 6:
			System.out.println("****Add New Book****" + LibraryConstants.LINECHANGE);
			addBook();
			mainMenu();
			break;
		case 7:
			System.out.println("****Issue a Book****" + LibraryConstants.LINECHANGE);
			issueBook();

			mainMenu();
			break;
		case 8:
			System.out.println("****return a Book****" + LibraryConstants.LINECHANGE);

			returnBook();

			mainMenu();
			break;
		case 9:
			System.out.println("****Delete a Book****" + LibraryConstants.LINECHANGE);

			deleteBook(BookMap);

			mainMenu();
			break;
		case 10:
			System.out.println("Application Stoped......Have a Nice Day! :)");

			stopApplication();

			mainMenu();
			break;
		default:
			System.out.println("Option Not Recognized!");
			mainMenu();
			break;

		}
	}

	private static void mainMenu() {

		LibraryFormattedColumns formattedColumnsObj = new LibraryFormattedColumns();

		formattedColumnsObj.addLine("********** MAIN MENU **********")
				.addLine("Press '0' To Login")
				.addLine("Press '1' To Display Existing Libraries")
				.addLine("Press '2' To Display Existing Libraries and Books Details")
				.addLine("Press '3' To Display Existing reference Books")
				.addLine("Press '4' To Display Existing Issues Books")
				.addLine("Press '5' To Add New Libray")
				.addLine("Press '6' To Add New Book")
				.addLine("Press '7' Issue a Book")
				.addLine("Press '8' return a  Book")
				.addLine("Press '9' Delete a Book")
				.addLine("Press '10' To Stop The Application");

		formattedColumnsObj.print();

	}

	private static void login() throws FileNotFoundException {


	//	String Username;
		//String Password;

		//Username = "wisdom";
		//Password = "123";

		Scanner input1 = new Scanner(System.in);
		System.out.println("before login please register");
		System.out.println("=======================");
		System.out.println("enter the user id");
		int useID=input1.nextInt();
		System.out.println("enter the user name");
		String 	userName=input1.next();
		System.out.println("Enter age ");
		int age=input1.nextInt();
		System.out.println("enter mail Id");
		String emailId=input1.next();
		System.out.println("enter the login Username");
		String Username = input1.next();
		System.out.println("Enterlogin Password : ");
		String password = input1.next();
		
		if (Username.equals(Username) && password.equals(password)) {

			System.out.println("Access Granted! Welcome!");
		} else {
			if (Username.equals(Username)) {
				System.out.println("valid Password!");
			} else if (password.equals(password)) {
				System.out.println("valid Username!");
			} else {
				System.out.println("Invalid Username & Password!");
			}
			
		}
		
		
	}

	private static Boolean addBook() throws IOException {
		try {
			// Scanner tempScanner = new Scanner(System.in);

			BookDetails BookDetails = new BookDetails();
			BookDetails.setTotalPage((int) (Math.random() * 100));
			BookDetails.setBookID(++index);
			BookDetails.setIssued(false);

			System.out.println("Enter Book Title -");
			BookDetails.setTitle(bufferedReader.readLine());
			System.out.println("Enter Book Author -");
			BookDetails.setAuthor(bufferedReader.readLine());
			System.out.println("Enter Associated LibraryID -");
			BookDetails.setLibraryID(Long.valueOf(bufferedReader.readLine()));
			System.out.println("Enter Publisher -");
			BookDetails.setPublisher(bufferedReader.readLine());
			validateLibraryID(BookDetails);

			return BookObj.addBook(BookDetails, BookMap);
		} catch (NumberFormatException nfe) {
			nfe.getMessage();

		}
		return Boolean.FALSE.booleanValue();

	}

	private static Boolean issueBook() throws IOException {

		if (BookMap.isEmpty()) {
			System.out.println("No Book Added Yet!");

			return Boolean.FALSE.booleanValue();
		} else {
			try {
				displayAvailableBookInfo(BookMap);
				System.out.println("Enter BookID from above to issue");
				Long bookID = Long.valueOf(bufferedReader.readLine());
				if (BookMap.containsKey(bookID)) {
					Scanner s = new Scanner(System.in);
					int choice = 0;
					switch (choice) {
					case 1:
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DAY_OF_MONTH, 18);
						System.out.println("Issue to : ");
						String issueTo = s.next();
						BookObj.displayIssuedBookInfo(BookMap);
						System.out.println("Book " + bookID + " Issued,  please return it till "
								+ new Date(cal.getTimeInMillis()));
						break;
					case 2:
						cal = Calendar.getInstance();
						cal.add(Calendar.HOUR_OF_DAY, 4);
						System.out.println("issued to : ");
						issueTo = s.next();
						BookObj.displayIssuedBookInfo(BookMap);
						issueTo = s.next();
						BookObj.displayIssuedBookInfo(BookMap);
						System.out.println("Book " + bookID + " Issued for refernce,  please return it till "
								+ new Date(cal.getTimeInMillis()));
						break;
					}
					BookMap.get(bookID).setIssued(Boolean.TRUE.booleanValue());
				}

				else {
					System.out.println("BookID Not available!");
				}
				System.out.println("book got issued");
				return Boolean.TRUE.booleanValue();
			} catch (NumberFormatException nfe) {
				nfe.getMessage();

			}
		}
		return Boolean.FALSE.booleanValue();

	}

	private static boolean returnBook() throws IOException {

		if (BookMap.isEmpty()) {
			System.out.println("No Book Added Yet!");

			return Boolean.FALSE.booleanValue();
		} else {
			try {
				displayIssuedBookInfo(BookMap);
				System.out.println("Enter BookID from above to return");
				Long bookID = Long.valueOf(bufferedReader.readLine());
				if (BookMap.containsKey(bookID)) {
					BookMap.get(bookID).setReturned(Boolean.TRUE.booleanValue());
				} else {
					System.out.println("BookID Not available!");

					return Boolean.TRUE.booleanValue();
				}
				System.out.println("Book got Returned");
				return Boolean.TRUE.booleanValue();
			} catch (NumberFormatException nfe) {
				nfe.getMessage();

			}
		}
		return Boolean.FALSE.booleanValue();
	}

	private static Boolean deleteBook(Map<Long, BookDetails> BookMap) throws IOException {

		if (BookMap.isEmpty()) {
			System.out.println("No Book Added Yet!");

			return Boolean.FALSE.booleanValue();
		} else {
			try {
				displayAvailableBookInfo(BookMap);
				System.out.println("Enter BookID from above to delete");
				Long bookID = Long.valueOf(bufferedReader.readLine());
				if (BookMap.containsKey(bookID)) {

					BookObj.deleteBook(bookID, BookMap);
				} else {
					System.out.println("BookID Not available!");
					return Boolean.TRUE.booleanValue();
				}

				return Boolean.TRUE.booleanValue();
			} catch (NumberFormatException nfe) {
				nfe.getMessage();

			}
		}

		return Boolean.FALSE.booleanValue();

	}

	private static Boolean addLibrary() throws IOException {

		// Scanner tempScanner = new Scanner(System.in);

		LibraryDetails LibraryDetails = new LibraryDetails();
		LibraryDetails.setLibraryID(++index);
		LibraryDetails.setIsActive(Boolean.TRUE.booleanValue());
		LibraryDetails.setIsPublicLibrary(Boolean.TRUE.booleanValue());
		LibraryObj.addLibrary(LibraryDetails, LibraryMap);

		System.out.println("Enter Library Name -");
		String name = bufferedReader.readLine();
		LibraryDetails.setName(name);
		System.out.println("Enter Library Address -");
		String address = bufferedReader.readLine();
		LibraryDetails.setAddress(address);
		System.out.println("Enter Library Owner Name -");
		String ownerName = bufferedReader.readLine();
		LibraryDetails.setOwnerName(ownerName);

		return Boolean.TRUE.booleanValue();

	}

	private static void displayLibraryBookAss(Map<Long, Set> LibraryBookAssociation) {
		if (LibraryBookAssociation.isEmpty()) {
			System.out.println("No Book assigned to Library Yet!" + LibraryConstants.LINECHANGE);

			return;
		}

		Iterator<Entry<Long, Set>> itr = LibraryBookAssociation.entrySet().iterator();

		LibraryFormattedColumns formattedColumnsObj = new LibraryFormattedColumns();
		formattedColumnsObj.addLine(LibraryConstants.LibraryName, LibraryConstants.TITLE, LibraryConstants.AUTHOR,
				LibraryConstants.PUBLISHER);
		while (itr.hasNext()) {
			Entry<Long, Set> entry = itr.next();
			Long key = entry.getKey();
			Iterator itrSet = entry.getValue().iterator();
			while (itrSet.hasNext()) {
				Long bookID = (Long) itrSet.next();
				if (BookMap.containsKey(bookID)) {
					BookDetails BookDetails = BookMap.get(bookID);
					formattedColumnsObj.addLine(LibraryMap.get(key).getName(), BookDetails.getTitle(),
							BookDetails.getAuthor(), BookDetails.getPublisher());
				}
			}
		}
		formattedColumnsObj.print();
	}

	private static Boolean iterateAndDispLibraryMap(Map<Long, LibraryDetails> LibraryMap) {

		if (LibraryMap.isEmpty()) {
			System.out.println("No Library Added Yet!");

			return Boolean.FALSE.booleanValue();
		}

		Iterator<Entry<Long, LibraryDetails>> itr = LibraryMap.entrySet().iterator();
		LibraryFormattedColumns formattedColumnsObj = new LibraryFormattedColumns();
		formattedColumnsObj.addLine(LibraryConstants.LIBRARYID, LibraryConstants.LibraryName);
		while (itr.hasNext()) {
			Entry<Long, LibraryDetails> entry = itr.next();
			formattedColumnsObj.addLine(entry.getKey().toString(), entry.getValue().getName());
		}
		formattedColumnsObj.print();

		return Boolean.TRUE.booleanValue();
	}

	@SuppressWarnings("unchecked")
	private static void validateLibraryID(BookDetails BookDetails) throws NumberFormatException, IOException {

		if (!LibraryMap.containsKey(BookDetails.getLibraryID())) {
			System.out.println("Entered Library ID not available, Added Default Value as 0!");
			if (iterateAndDispLibraryMap(LibraryMap)) {
				System.out.println("Enter Associated LibraryID -");
				BookDetails.setLibraryID(Long.valueOf(bufferedReader.readLine()));
				validateLibraryID(BookDetails);
			} else {
				BookDetails.setLibraryID(0l);
			}
		} else {
			Set bookIDSet = LibraryBookAssociation.get(BookDetails.getLibraryID());
			if (bookIDSet == null) {
				bookIDSet = new HashSet();
			}

			bookIDSet.add(BookDetails.getBookID());

			LibraryBookAssociation.put(BookDetails.getLibraryID(), bookIDSet);
		}

	}

	public static void displayAvailableBookInfo(Map<Long, BookDetails> BookMap) {
		if (BookMap.isEmpty()) {
			System.out.println("");

			return;
		} else {
			BookObj.displayAvailableBookInfo(BookMap);

		}
	}

	public static void displayIssuedBookInfo(Map<Long, BookDetails> BookMap) {
		if (BookMap.isEmpty()) {
			System.out.println("No Book Added Yet!");

			return;
		} else {
			BookObj.displayIssuedBookInfo(BookMap);

		}
	}
}