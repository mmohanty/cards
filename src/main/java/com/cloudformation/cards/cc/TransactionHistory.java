package com.cloudformation.cards.cc;

import java.io.Serializable;

public class TransactionHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String startDate;
	private String endDate;
	private String cardNumber;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
