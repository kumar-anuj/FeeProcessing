package main.java.com.feeprocess.factory;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.data.reader.CSVFileDataReader;
import main.java.com.feeprocess.data.reader.DataReader;
import main.java.com.feeprocess.model.Transaction;

public class ReaderFactory {
	
	private ReaderFactory() {}
	
	public static DataReader<Transaction> newReader(String sourceType) {
		switch(sourceType) {
		
		case StringConstants.CSV : 
			return new CSVFileDataReader();
			
		default :
			return null;
			
		
		}
	}

}
