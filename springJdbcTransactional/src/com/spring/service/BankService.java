package com.spring.service;

import com.spring.model.Account;
import com.spring.serviceImpl.TransacferException;

public interface BankService {

	public abstract int moneyTransacfer(Account fromAccount,Account toAccount,double money)throws TransacferException;
}
