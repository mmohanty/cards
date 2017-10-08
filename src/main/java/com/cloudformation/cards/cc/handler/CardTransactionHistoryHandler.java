package com.cloudformation.cards.cc.handler;

import java.util.HashMap;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cloudformation.cards.cc.TransactionHistory;

public class CardTransactionHistoryHandler implements RequestHandler<TransactionHistory, String>{

	public String handleRequest(TransactionHistory input, Context context) {
		
		final ClientConfiguration clientConfig = new ClientConfiguration();

		final String tableName = "CardTransaction";
		final AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().withClientConfiguration(clientConfig).withRegion(Regions.AP_SOUTHEAST_1).build();
		
		 // Scan items for transactions with a card-number ranging between start date and end date.
        HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
        Condition cardNumberCondition = new Condition()
            .withComparisonOperator(ComparisonOperator.EQ.toString())
            .withAttributeValueList(new AttributeValue().withS(input.getCardNumber()));
        Condition startDateCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.BETWEEN.toString())
                .withAttributeValueList(new AttributeValue().withS(input.getStartDate()), 
                		new AttributeValue().withS(input.getEndDate()));
        
        scanFilter.put("cardNumber", cardNumberCondition);
        scanFilter.put("transactionDate", startDateCondition);
        
        ScanRequest scanRequest = new ScanRequest(tableName).withScanFilter(scanFilter);
        ScanResult scanResult = dynamoDB.scan(scanRequest);
        System.out.println("Result: " + scanResult);
        
		return scanResult.toString();
	}

}
