package main.java.com.feeprocess.data.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.data.mapper.TransactionDataMapperUtil;
import main.java.com.feeprocess.exception.CustomException;
import main.java.com.feeprocess.model.Transaction;

public class CSVFileDataReader implements DataReader<Transaction>{

	
	@Override
	public List<Transaction> readData(String inputResource) throws CustomException {
		List<Transaction> txnlist = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File(inputResource)))) {
			String line = br.readLine();
			while((line = br.readLine()) != null) {
				if(line.length() == 0 || line.startsWith(StringConstants.EXTERNAL_TXN_ID))
					continue;
				
				txnlist.add(TransactionDataMapperUtil.createTransactionObject(line.split(StringConstants.COMMA)));
			}
			
		} catch (FileNotFoundException e) {
			throw new CustomException("File is not found :" + inputResource);
		} catch (IOException | ParseException e) {
			throw new CustomException(e.getMessage());
		} 
		
		return txnlist;
	}

}
