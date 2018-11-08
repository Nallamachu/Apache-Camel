package com.bank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.bean.Bank;

@RestController
@RequestMapping(path = "/bank")
public class BankController {
	List<Bank> bankList = new ArrayList<>();

	public BankController() {
		testBankInfo();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Bank getBankInfo(@PathVariable int id) {
		return bankList.get(id - 1);
	}

	@GetMapping(path = "/all", produces = "application/json")
	public List<Bank> getAllBanksInfo() {
		return bankList;
	}

	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void addBank(@RequestBody Bank bank) {
		if(bank.getId() == 0) {
			throw new NullPointerException("Bank details are empty");
		}else {
			bankList.add(bank);
			System.out.println("Bank details has been added successfully: " + bank.toString());
		}
	}

	@DeleteMapping(path = "/deletebank/{id}")
	public void deleteBankInfoById(@PathVariable int id) {
		bankList.remove(id - 1);
	}
	
	@DeleteMapping(path = "/deletebank")
	public void deleteBankInfoById() {
		bankList.remove(bankList.size()-1);
	}

	public void testBankInfo() {
		bankList.add(new Bank(1, "TA", "USA"));
	}
}
