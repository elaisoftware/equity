package com.cienet.equity;
/**
 * Position
 * @author JiangLai
 * @since 2020.5.20
 */
public class Position {
	private String securityCode;
	private int quantity;
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
