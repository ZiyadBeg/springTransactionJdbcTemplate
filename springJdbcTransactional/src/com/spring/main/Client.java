package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.model.Account;
import com.spring.service.BankService;
import com.spring.serviceImpl.ServiceImpl;
import com.spring.serviceImpl.TransacferException;

public class Client {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
		BankService bankService=applicationContext.getBean("serviceimpl",ServiceImpl.class);
		Account fromAccount=new Account();

		Account toAccount=new Account();
		
		//fromAccount.setAccountNumber(12345);
		fromAccount.setAccountNumber(67890);
		
		toAccount.setAccountNumber(12345);
		//toAccount.setAccountNumber(67890);
		double money=1000;
		try {
			bankService.moneyTransacfer(fromAccount, toAccount, money);
		} catch (TransacferException e) {
			System.out.println(e.getMessage());
		}
	}
}
