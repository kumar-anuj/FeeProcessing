package main.java.com.feeprocess.data.reader;

import java.util.List;

import main.java.com.feeprocess.exception.CustomException;

public interface DataReader<T> {
	
	public List<T> readData(String inputResource) throws CustomException;

}
