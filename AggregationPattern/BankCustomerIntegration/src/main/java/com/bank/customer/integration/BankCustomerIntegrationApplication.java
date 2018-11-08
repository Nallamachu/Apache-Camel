package com.bank.customer.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan(basePackages="com.bank.customer.config")
public class BankCustomerIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankCustomerIntegrationApplication.class, args);
	}
}
