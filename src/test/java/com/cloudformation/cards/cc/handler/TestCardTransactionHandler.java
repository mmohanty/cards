package com.cloudformation.cards.cc.handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cloudformation.cards.cc.TransactionHistory;
import com.cloudformation.cards.cc.handler.CardTransactionHistoryHandler;


public class TestCardTransactionHandler {
	
	private CardTransactionHistoryHandler cardTransactionHandler;
	
	@Before
	public void init(){
		cardTransactionHandler = new CardTransactionHistoryHandler();
	}

	@Test
	public void test_Handler(){
		TransactionHistory historyInput = new TransactionHistory();
		historyInput.setCardNumber("123");
		historyInput.setStartDate("01-01-2000");
		historyInput.setEndDate("31-12-2020");
		
		String data = cardTransactionHandler.handleRequest(historyInput, null);
		
		Assert.assertNotNull("Data can not be null.", data);
	}
}
