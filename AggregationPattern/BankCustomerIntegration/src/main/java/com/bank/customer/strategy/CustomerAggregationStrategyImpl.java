package com.bank.customer.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.common.bean.Bank;
import com.common.bean.Customer;

public class CustomerAggregationStrategyImpl implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange original, Exchange resource) {
		Object originalBody = original.getIn().getBody();
		Object resourceResponse = resource.getIn().getBody();

		Customer customer = (Customer) originalBody;
		Bank bank = (Bank) resourceResponse;
		customer.setBank(bank);
		return original;
	}

}
