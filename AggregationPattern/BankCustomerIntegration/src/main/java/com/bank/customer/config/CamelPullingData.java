package com.bank.customer.config;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import com.bank.customer.strategy.CustomerAddressAggregationStrategyImpl;
import com.bank.customer.strategy.CustomerAggregationStrategyImpl;
import com.common.bean.Address;
import com.common.bean.Bank;
import com.common.bean.Customer;

@Component
public class CamelPullingData extends RouteBuilder {

	@Autowired
	CamelContext context;

	/*@Autowired
	DiscoveryClient discoveryClient;*/

	@Override
	public void configure() throws Exception {

		context.setTracing(true);

		restConfiguration().component("netty4-http").host("localhost").port(8000);

		JacksonDataFormat formatBank = new JacksonDataFormat(Bank.class);
		JacksonDataFormat formatCustomer = new JacksonDataFormat(Customer.class);
		JacksonDataFormat formatAddress = new JacksonDataFormat(Address.class);
		JacksonDataFormat formatCustomers = new JacksonDataFormat(Customer.class);
		formatCustomers.useList();
		JacksonDataFormat formatBanks = new JacksonDataFormat(Bank.class);
		formatBanks.useList();
		JacksonDataFormat formatAddresses = new JacksonDataFormat(Address.class);
		formatAddresses.useList();

		/* Common Exception Handling with OnException Block */
		onException(RuntimeException.class).process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				System.out.println("Hurray! Exception Occurred. Please retry...");
			}
		}).log("Received body ").handled(true);

		/* Integration Logic starts from below line */
		rest("/customerinfo").get("/").to("direct:customer").skipBindingOnErrorCode(true);

		from("direct:customer").setBody().constant(null).to("netty4-http:localhost:8282/customer/1")
				.unmarshal(formatCustomer).log("Customer info ${body}")
				.enrich("direct:bank", new CustomerAggregationStrategyImpl())
				.log("Bank Customer Migration info ${body}")
				.enrich("direct:address", new CustomerAddressAggregationStrategyImpl())
				.log("Customer Address Migration infor ${body}");

		from("direct:bank").setBody().constant(null).to("netty4-http:http://localhost:8181/bank/1")
				.unmarshal(formatBank)
				.log("Bank info ${body}");

		from("direct:address").setBody().constant(null).to("netty4-http:http://localhost:8383/address/1")
				.unmarshal(formatAddress)
				.log("Address info ${body}");

	}

}
