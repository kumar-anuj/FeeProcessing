package main.java.com.feeprocess.data.process.context;

import java.util.List;

import main.java.com.feeprocess.data.process.strategy.DataProcessStrategy;

public class ProcessContext<T> {

	DataProcessStrategy<T> strategy;
	
	public ProcessContext(DataProcessStrategy<T> strategy) {
		this.strategy = strategy;
		
	}
	
	public List<T> executeStrategy(List<T> items) {
		return strategy.processData(items);
	}
}
