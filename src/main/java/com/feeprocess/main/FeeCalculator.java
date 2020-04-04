package main.java.com.feeprocess.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.data.process.context.ProcessContext;
import main.java.com.feeprocess.data.process.strategy.TransactionDataProcessStrategy;
import main.java.com.feeprocess.data.reader.DataReader;
import main.java.com.feeprocess.data.writer.DataWriter;
import main.java.com.feeprocess.exception.CustomException;
import main.java.com.feeprocess.factory.ReaderFactory;
import main.java.com.feeprocess.factory.WriterFactory;
import main.java.com.feeprocess.model.Transaction;

public class FeeCalculator {

	public static void main(String[] args) throws CustomException {
	
		try(FileInputStream fis = new FileInputStream(StringConstants.CONFIG_FILE)) {
			Properties properties = new Properties();
			properties.load(fis);
			String inputFile = properties.getProperty(StringConstants.INPUT_FILE);
			String outputFile = properties.getProperty(StringConstants.OUTPUT_FILE);
			
			DataReader<Transaction> reader = ReaderFactory.newReader(StringConstants.CSV);
			ProcessContext<Transaction> context = new ProcessContext<>(new TransactionDataProcessStrategy());
			
			List<Transaction> txnList = reader.readData(inputFile);
			List<Transaction> processedTxn = context.executeStrategy(txnList);
			
			DataWriter<Transaction> writer = WriterFactory.newWriter(StringConstants.CSV);
			writer.writeData(processedTxn, outputFile);
			
			
		} catch (IOException e) {
			throw new CustomException(e.getMessage());
		}
		
	}

}
