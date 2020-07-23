package com.spring.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.spring.daoImpl.BankDaoImpl;
import com.spring.model.Account;
import com.spring.service.BankService;

@Service("serviceimpl")
public class ServiceImpl implements BankService {

	@Autowired
	BankDaoImpl bankdaoImpl;
	
	@Override
	//@Transactional with no parameter
	//Support
	//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.SUPPORTS,readOnly = false,noRollbackFor = Exception.class)
	//NOT_SUPPORTED
	//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,readOnly = false,noRollbackFor = Exception.class)
	//NEVER
	//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,noRollbackFor = Exception.class)

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,readOnly = false,noRollbackFor = Exception.class)
	public int moneyTransacfer(Account fromAccount,Account toAccount,double money) throws TransacferException {
		
		System.out.println("38...."+TransactionSynchronizationManager.isSynchronizationActive());

		int withdrawstatus=bankdaoImpl.withdrawMoney(fromAccount, toAccount, money);

		if (withdrawstatus > 0) {
			System.out.println("Withdraw Successfully");
			int depositestatus=bankdaoImpl.depositeMoney(toAccount, money);
			if (depositestatus > 0) {
				System.out.println("Deposite Successfully");
			} else {
				System.out.println("Unable to deposite Successfully");
			}
		} else {
			 throw new TransacferException("Unable to Withdraw Successfully");
		}
		return 0;
	}

}
