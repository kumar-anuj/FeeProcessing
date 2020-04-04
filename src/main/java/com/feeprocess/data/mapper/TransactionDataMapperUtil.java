package main.java.com.feeprocess.data.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.model.Transaction;

public class TransactionDataMapperUtil {
	
	private TransactionDataMapperUtil() {}
	
	public static Transaction createTransactionObject(String[] txnData) throws ParseException {
		Transaction txn = new Transaction();
		
		txn.setTxnId(txnData[0]);
		txn.setClientId(txnData[1]);
		txn.setSecurityId(txnData[2]);
		txn.setTxnType(txnData[3]);
		txn.setTxnDate(new SimpleDateFormat(StringConstants.DATE_FORMAT).parse(txnData[4]));
		txn.setMarketVal(Double.parseDouble(txnData[5]));
		txn.setPriority(txnData[6]);
		
		return txn;
	}
	
}
