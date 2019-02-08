package com.javarnd.library;

import java.util.Map;

public interface Library {
	
	public void addLibrary(LibraryDetails LibraryDetails,Map<Long, LibraryDetails> map);
	
	public void displayLibraryInfo(Map<Long, LibraryDetails> LibraryMap);

}
