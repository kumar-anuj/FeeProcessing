package main.java.com.feeprocess.model;

import java.util.Date;

import main.java.com.feeprocess.common.StringConstants;

public class Transaction {
	
	private String txnId;
	private String clientId;
	private String securityId;
	private String txnType;
	private Date txnDate;
	private Double marketVal;
	private String priority; //High = Y, Normal = N
	private Double txnFee;
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	public Double getMarketVal() {
		return marketVal;
	}
	public void setMarketVal(Double marketVal) {
		this.marketVal = marketVal;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Double getTxnFee() {
		return txnFee;
	}
	public void setTxnFee(Double txnFee) {
		this.txnFee = txnFee;
	}
	
	@Override
	public String toString() {
		return clientId + StringConstants.COMMA + txnType +  StringConstants.COMMA + txnDate + 
				StringConstants.COMMA + priority  + StringConstants.COMMA + txnFee;
	}
	
	
}
