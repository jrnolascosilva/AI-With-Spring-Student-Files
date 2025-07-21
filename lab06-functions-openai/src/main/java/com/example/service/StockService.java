package com.example.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.AIClient;

import java.util.List;

// By naming the service bean, we can reliably reference it from the ChatClient.
@Service("stockService")
public class StockService {

	public static Double price = Math.round(Math.random() * 800) / 100.0;
	public static Integer volume = (int) (Math.random() * 999 + 1);

	// FIX: Update the Request record to accept the 'fields' parameter from the AI.
	public record Request(String symbol, List<String> fields) {}
	public record Response(String symbol, Double price, Integer volume, String currency) {}

	//private final AIClient client;
	/**
	 * The @Tool annotation clearly marks this as a callable function for the AI.
	 * We can now use a descriptive name like 'getStockQuote' instead of the generic 'apply'.
	 */
	@Tool(description = "Get the current stock price, volume, and currency for a given stock symbol")
	public Response getStockQuote(Request request) {
		//	For now, just return a hard-coded response:
		return new Response(request.symbol(), price, volume, "USD");
	}



//	public String getCompanySummary(String symbol) {
//		return client.callApi(
//				"Provide a description of the company with stock ticker symbol '%s'".formatted(symbol)
//		);
//	}
}