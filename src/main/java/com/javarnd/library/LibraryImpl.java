package com.javarnd.library;

import java.util.Map;
import java.util.Map.Entry;

import com.javarnd.commons.LibraryConstants;

public class LibraryImpl implements Library{

	public void addLibrary(LibraryDetails LibraryDetails, Map<Long, LibraryDetails> map) {
		
map.put(LibraryDetails.getLibraryID(), LibraryDetails);
		
		//return Boolean.TRUE.booleanValue();
	
	}

	public void displayLibraryInfo(Map<Long, LibraryDetails> LibraryMap) {
	
		
java.util.Iterator<Entry<Long, LibraryDetails>> itr = LibraryMap.entrySet().iterator();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("******* Library Information *******").append(LibraryConstants.LINECHANGE);
		while(itr.hasNext()){
			Entry<Long, LibraryDetails> entry = itr.next();
			LibraryDetails LibraryDetails = entry.getValue();			
			stringBuilder.append(entry.getKey()).append(LibraryConstants.TABSPACE).append(LibraryDetails.getName()).append(LibraryConstants.TABSPACE).append(LibraryDetails.getAddress()).append(LibraryConstants.LINECHANGE);
		}
		System.out.println(stringBuilder);
		
	}

	

}
