package main.java.com.feeprocess.data.writer;

import java.util.List;

import main.java.com.feeprocess.exception.CustomException;

public interface DataWriter<T> {

	public void writeData(List<T> items, String outputResource) throws CustomException;
}
