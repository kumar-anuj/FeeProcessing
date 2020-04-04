package main.java.com.feeprocess.factory;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.data.writer.CSVFileDataWriter;
import main.java.com.feeprocess.data.writer.DataWriter;
import main.java.com.feeprocess.model.Transaction;

public class WriterFactory {

	public static DataWriter<Transaction> newWriter(String sourceType) {
		switch(sourceType) {
			case StringConstants.CSV : 
				return new CSVFileDataWriter();
		
			default :
				return null;
		}
	}
}
