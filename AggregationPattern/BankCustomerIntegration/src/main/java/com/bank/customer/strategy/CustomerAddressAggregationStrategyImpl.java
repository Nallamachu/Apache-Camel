package com.bank.customer.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.common.bean.Address;
import com.common.bean.Customer;

public class CustomerAddressAggregationStrategyImpl implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange original, Exchange resource) {
		Object originalBody = original.getIn().getBody();
		Object resourceResponse = resource.getIn().getBody();

		Customer customer = (Customer) originalBody;
		Address address = (Address) resourceResponse;
		customer.setAddress(address);
		return original;
	}

}
