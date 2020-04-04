package main.java.com.feeprocess.data.process.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.model.Transaction;

public class TransactionDataProcessStrategy implements DataProcessStrategy<Transaction> {

	
	@Override
	public List<Transaction> processData(List<Transaction> txns) {
		Map<String, List<Transaction>> txnMap = new LinkedHashMap<>();
		List<Transaction> txnsLst = new ArrayList<>();
		
		calculateProcessingFee(txns, txnMap);
		
		sortTransactions(txnMap, txnsLst);
		
		return txnsLst;
	}

	private void calculateProcessingFee(List<Transaction> txns, Map<String, List<Transaction>> txnMap) {
		for(Transaction tx : txns) {
			String key = tx.getClientId() + StringConstants.COLON + tx.getSecurityId() + 
					StringConstants.COLON + tx.getTxnDate() + StringConstants.COLON + tx.getPriority();
			
			if(txnMap.get(key) == null) {
				
				setProcessingFeeForNormalTxn(txnMap, tx, key);
			}
			else {
				setProcessingFeeForIntradayTxn(txnMap, tx, key);
			}
		}
	}

	private void setProcessingFeeForIntradayTxn(Map<String, List<Transaction>> txnMap, Transaction tx, String key) {
		List<Transaction> txnLst;
		txnLst = txnMap.get(key);
		txnLst.add(tx);
		
		if(tx.getTxnType().equals(StringConstants.SELL) || tx.getTxnType().equals(StringConstants.BUY)) {
			UnaryOperator<Transaction> func1 = t -> {
				t.setTxnFee(Double.valueOf(StringConstants.DOLLAR_10));
				return t;
			};

			txnLst = txnLst.stream().map(func1).collect(Collectors.toList());
		}
		txnMap.put(key, txnLst);
	}

	private void setProcessingFeeForNormalTxn(Map<String, List<Transaction>> txnMap, Transaction tx, String key) {
		List<Transaction> txnLst;
		if(tx.getPriority().equals(StringConstants.YES)) { // High Priority
			tx.setTxnFee(Double.valueOf(StringConstants.DOLLAR_500));
		}
		else { // Normal Priority
			if(tx.getTxnType().equals(StringConstants.SELL) || tx.getTxnType().equals(StringConstants.WITHDRAW))
				tx.setTxnFee(Double.valueOf(StringConstants.DOLLAR_100));
			
			if(tx.getTxnType().equals(StringConstants.BUY) || tx.getTxnType().equals(StringConstants.DEPOSIT))
				tx.setTxnFee(Double.valueOf(StringConstants.DOLLAR_50));
		}
		
		txnLst = new ArrayList<>();
		txnLst.add(tx);
		txnMap.put(key, txnLst);
	}

	private void sortTransactions(Map<String, List<Transaction>> txnMap, List<Transaction> txnsLst) {
		Collection<List<Transaction>> valCollection = txnMap.values();
		
		for(List<Transaction> tx : valCollection) {
			txnsLst.addAll(tx);
		}
		
		txnsLst.sort(Comparator.comparing(Transaction::getClientId).thenComparing(Transaction::getTxnType).
				thenComparing(Transaction::getTxnDate).thenComparing(Transaction::getPriority));
	}

}
