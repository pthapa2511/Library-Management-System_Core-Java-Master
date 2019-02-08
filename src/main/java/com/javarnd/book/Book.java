package com.javarnd.book;

import java.util.Map;

public interface Book {

	public Boolean addBook(BookDetails BookDetails, Map<Long, BookDetails> BookMap);

	public Boolean deleteBook(Long bookID, Map<Long, BookDetails> BookMap);

	public void displayAvailableBookInfo(Map<Long, BookDetails> BookMap);

	public void displayIssuedBookInfo(Map<Long, BookDetails> BookMap);

	public BookDetails returnBook(BookDetails BookDetails, Map<Long, BookDetails> BookMap);

}
