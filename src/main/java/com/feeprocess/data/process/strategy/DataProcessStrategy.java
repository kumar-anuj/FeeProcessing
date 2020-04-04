package main.java.com.feeprocess.data.process.strategy;

import java.util.List;

public interface DataProcessStrategy<T> {
	
	public List<T> processData(List<T> items);

}
