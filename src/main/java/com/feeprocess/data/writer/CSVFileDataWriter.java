package main.java.com.feeprocess.data.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.exception.CustomException;
import main.java.com.feeprocess.model.Transaction;

public class CSVFileDataWriter implements DataWriter<Transaction>{
	
	@Override
	public void writeData(List<Transaction> txns, String outputResource) throws CustomException {

		String header = StringConstants.HEADER;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(outputResource)))) {
			bw.write(header);
			bw.write(System.lineSeparator());
			
			for(Transaction tx : txns) {
				System.out.println(tx.toString());
				bw.write(tx.toString());
				bw.write(System.lineSeparator());
			}
			
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		}
	}

	
	

}
