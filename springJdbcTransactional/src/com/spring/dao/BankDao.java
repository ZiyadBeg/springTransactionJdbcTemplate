package com.spring.dao;

import com.spring.model.Account;

public interface BankDao {

	public abstract int withdrawMoney(Account fromAccount,Account toAccount,double money);
	public abstract int depositeMoney(Account toAccount,double money);
}
