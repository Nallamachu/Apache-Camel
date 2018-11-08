package com.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.bean.Customer;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {

	List<Customer> customerList = new ArrayList<Customer>();

	public CustomerController() {
		testData();
	}
	
	@GetMapping(path="/{id}",produces="application/json")
	public Customer getCustomerInfo(@PathVariable int id){
		return customerList.get(id-1);
	}
	
	@GetMapping(path="/all",produces="application/json")
	public List<Customer> getAllCustomerInfo(){
		return customerList;
	}
	
	@PostMapping(path="/add",consumes="application/json")
	public Customer addCustomer(@RequestBody Customer customer) {
		if(customer.getId() == 0) {
			throw new NullPointerException("Customer is empty");
		} else {
			customerList.add(customer);
			System.out.println("Customer Added Successfully: "+customer.toString());
			return customer;
		}
	}
	
	@PostMapping(path="/deletecust/{id}")
	public void deleteCustomerInfoById(@PathVariable String id){
		for(Customer cust : customerList) {
			if(id.equalsIgnoreCase(cust.getId()+"")) {
				int index =customerList.lastIndexOf(cust);
				customerList.remove(index);
				System.out.println("Cusomer with id "+id+" has been removed.");
				return;
			}
		}
		System.out.println("No Customer found with Id: "+id);
	}
	
	@PostMapping(path="/deletecust")
	public void deleteCustomer(){
		customerList.remove(customerList.size()-1);
		System.out.println("Recentrly added Customer has been removed Successfully");
	}
	
	public void testData() {
		customerList.add(new Customer(1,"Subbareddy","Bangalore",null,null));
		customerList.add(new Customer(2,"Nallamachu","YSR Kadapa",null,null));
	}
	
}
