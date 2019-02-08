package com.javarnd.book;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.javarnd.commons.LibraryConstants;
import com.javarnd.commons.LibraryFormattedColumns;

import java.util.Scanner;


public class BookImpl implements Book{

	public Boolean addBook(BookDetails BookDetails, Map<Long, BookDetails> BookMap) {

		BookMap.put(BookDetails.getBookID(), BookDetails);
		
		return Boolean.TRUE.booleanValue();
	
	}

	public Boolean deleteBook(Long bookID, Map<Long, BookDetails> BookMap) {
		Iterator<Entry<Long, BookDetails>> itr = BookMap
				.entrySet().iterator();
			
				while (itr.hasNext()) {			
					Entry<Long, BookDetails> entry = itr.next();
					
					if(bookID == entry.getKey()){
						
						if(entry.getValue().getIssued()){
							System.out.println("You can not delete issued book!");
							break;
						}
					
						itr.remove();
						
						return Boolean.TRUE.booleanValue();
					}			
				}
			
				return Boolean.FALSE.booleanValue();
	}

	public void displayAvailableBookInfo(Map<Long, BookDetails> BookMap) {

		Iterator<Entry<Long, BookDetails>> itr = BookMap
				.entrySet().iterator();
		
		LibraryFormattedColumns formattedColumnsObj = new LibraryFormattedColumns();		
		formattedColumnsObj.addLine(LibraryConstants.BOOKID,LibraryConstants.AUTHOR,LibraryConstants.TITLE);
	
		while (itr.hasNext()) {			
			Entry<Long, BookDetails> entry = itr.next();
			BookDetails BookDetails = entry.getValue();
			
			if(BookDetails!=null && !BookDetails.getIssued()){
				formattedColumnsObj.addLine(entry.getKey().toString(),
						BookDetails.getAuthor(), BookDetails.getTitle());
			}			
		}
		
		formattedColumnsObj.print();		

		
	}

	public void displayIssuedBookInfo(Map<Long, BookDetails> BookMap) {
		
		Iterator<Entry<Long, BookDetails>> itr = BookMap
		.entrySet().iterator();
		
		LibraryFormattedColumns formattedColumnsObj = new LibraryFormattedColumns();		
		formattedColumnsObj.addLine(LibraryConstants.BOOKID,LibraryConstants.AUTHOR,LibraryConstants.TITLE);
	
		while (itr.hasNext()) {			
			Entry<Long, BookDetails> entry = itr.next();
			BookDetails BookDetails = entry.getValue();
			
			if(BookDetails.getIssued()){				
				formattedColumnsObj.addLine(entry.getKey().toString(),
						BookDetails.getAuthor(), BookDetails.getTitle());
			}			
		}
		
		formattedColumnsObj.print();
		
	}

	public BookDetails returnBook(BookDetails BookDetails, Map<Long, BookDetails> BookMap) {
		System.out.println(" bookId");
		@SuppressWarnings("resource")
		Scanner c = new Scanner(System.in);
		c.nextInt();
		int bookId = c.nextInt();
		@SuppressWarnings("unchecked")
		List<BookDetails> bd = (List<BookDetails>) BookMap.put(BookDetails.getBookID(), BookDetails);
		for (BookDetails b : bd) {
			if (b.getBookID() == bookId) {
				Date issueDate = b.getIssuedDate();
				Date todayDate = new Date();

				long diff = todayDate.getTime() - issueDate.getTime();

				long diffDays = diff / (24 * 60 * 60 * 1000);

				if ((diffDays > 15) &&  (diffDays <= 18)){
					
					 System.out.println("we can reissue the book");
				}else
				{
					int fine = (int) (diffDays - 15);
					fine = fine * 15;
					System.out.println("Penalty " + fine + " Rs.");
				}
			
			}
		}
		return BookDetails;


		
	}
	
	
	}
