package com.cienet.equity;
/**
 * Transaction
 * @author JiangLai
 * @since 2020.5.20
 */
public class Transaction {
	private int transcationID;
	private int tradeID;
	private int version;
	private String securityCode;
	private int quatity;
	private Actions action;
	private BuySell buySell;
	public int getTranscationID() {
		return transcationID;
	}
	public void setTranscationID(int transcationID) {
		this.transcationID = transcationID;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	public Actions getAction() {
		return action;
	}
	public void setAction(Actions action) {
		this.action = action;
	}
	public BuySell getBuySell() {
		return buySell;
	}
	public void setBuySell(BuySell buySell) {
		this.buySell = buySell;
	}
	
}

