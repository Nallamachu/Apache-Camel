package com.address.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.bean.Address;

@RestController
@RequestMapping(path = "/address")
public class AddressControler {

	List<Address> addressList = new ArrayList<Address>();

	public AddressControler() {
		Address address = new Address();
		address.setId(1);
		address.setLine1("4th Cross, Ashwath Nagar");
		address.setLine2("Marathahalli Bridge");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setZipcode(560037L);
		addressList.add(address);
	}

	@RequestMapping(path = "/",produces="application/json")
	public List<Address> getDefaultAddressList() {
		return addressList;
	}

	@RequestMapping(path = "/{id}",produces="application/json")
	public Address getDefaultAddressList(@PathVariable int id) {
		return addressList.get(id - 1);
	}

}
