package com.common.bean;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String location;
	private Bank bank;
	private Address address;

	public Customer() {

	}

	public Customer(int id, String name, String location, Bank bank, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.bank = bank;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", location=" + location + ", bank=" + bank + ", address="
				+ address + "]";
	}

}
