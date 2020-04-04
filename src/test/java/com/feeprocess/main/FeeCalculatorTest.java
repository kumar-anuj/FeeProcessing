package test.java.com.feeprocess.main;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.com.feeprocess.common.StringConstants;
import main.java.com.feeprocess.data.process.context.ProcessContext;
import main.java.com.feeprocess.data.process.strategy.TransactionDataProcessStrategy;
import main.java.com.feeprocess.model.Transaction;

public class FeeCalculatorTest {
	List<Transaction> trxList;
	
	@Before
	public void initData() throws ParseException {
		trxList = createDummyData();
	}

	@Test
	public void test001() {
		ProcessContext<Transaction> context = new ProcessContext<>(new TransactionDataProcessStrategy());
		List<Transaction> processedTxn = context.executeStrategy(trxList);
		
		for(Transaction tx : processedTxn) {
			if(tx.getTxnId().equals("t1") || tx.getTxnId().equals("t2")) {
				assertEquals(Double.valueOf(10.0), tx.getTxnFee());
			}
			
			if(tx.getTxnId().equals("t3") ) {
				assertEquals(Double.valueOf(500.0), tx.getTxnFee());
				assertEquals(StringConstants.YES, tx.getPriority());
				assertEquals(StringConstants.SELL, tx.getTxnType());
			}
			
			if(tx.getTxnId().equals("t4") ) {
				assertEquals(Double.valueOf(100.0), tx.getTxnFee());
				assertEquals(StringConstants.NO, tx.getPriority());
				assertEquals(StringConstants.SELL, tx.getTxnType());
			}
			
			if(tx.getTxnId().equals("t5") ) {
				assertEquals(Double.valueOf(50.0), tx.getTxnFee());
				assertEquals(StringConstants.NO, tx.getPriority());
				assertEquals(StringConstants.BUY, tx.getTxnType());
			}
		}
		
	}
	
	private List<Transaction> createDummyData() throws ParseException {
		Transaction t1 = new Transaction();
		t1.setTxnId("t1");
		t1.setClientId("c1");
		t1.setSecurityId("s1");
		t1.setTxnDate(new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("23-11-2013"));
		t1.setTxnType(StringConstants.BUY);
		t1.setMarketVal(100.0);
		t1.setPriority("Y");
		
		Transaction t2 = new Transaction();
		t2.setTxnId("t2");
		t2.setClientId("c1");
		t2.setSecurityId("s1");
		t2.setTxnDate(new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("23-11-2013"));
		t2.setTxnType(StringConstants.SELL);
		t2.setMarketVal(101.0);
		t2.setPriority("Y");
		
		Transaction t3 = new Transaction();
		t3.setTxnId("t3");
		t3.setClientId("c3");
		t3.setSecurityId("s3");
		t3.setTxnDate(new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("21-01-2014"));
		t3.setTxnType(StringConstants.SELL);
		t3.setMarketVal(101.0);
		t3.setPriority("Y");
		
		Transaction t4 = new Transaction();
		t4.setTxnId("t4");
		t4.setClientId("c4");
		t4.setSecurityId("s4");
		t4.setTxnDate(new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("11-01-2014"));
		t4.setTxnType(StringConstants.SELL);
		t4.setMarketVal(101.0);
		t4.setPriority("N");
		
		Transaction t5 = new Transaction();
		t5.setTxnId("t5");
		t5.setClientId("c5");
		t5.setSecurityId("s5");
		t5.setTxnDate(new SimpleDateFormat(StringConstants.DATE_FORMAT).parse("10-02-2014"));
		t5.setTxnType(StringConstants.BUY);
		t5.setMarketVal(102.0);
		t5.setPriority("N");
		
		List<Transaction> txnList = new ArrayList<>();
		txnList.add(t1);
		txnList.add(t2);
		txnList.add(t3);
		txnList.add(t4);
		txnList.add(t5);
		
		return txnList;
		
	}

}
