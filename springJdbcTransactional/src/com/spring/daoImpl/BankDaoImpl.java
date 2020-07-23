package com.spring.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.spring.dao.BankDao;
import com.spring.model.Account;

@Repository("bankdaoimpl")
public class BankDaoImpl implements BankDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int withdrawMoney(Account fromAccount, Account toAccount, double money) {
		int status = 0;
		Account account = getAccountDetails(fromAccount);

		double amt = account.getBalance() - money;
		String SQL = "update account set balance=? where accountnumber=? ";
		status = jdbcTemplate.update(SQL, amt, fromAccount.getAccountNumber());
		
		return status;
	}

	@Override
	//Support
	//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.MANDATORY,readOnly = false,noRollbackFor = Exception.class)
	//NOT_SUPPORTED
	//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.NOT_SUPPORTED,readOnly = false,noRollbackFor = Exception.class)
	//NEVER
	//@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.NEVER,readOnly = false,noRollbackFor = Exception.class)
	public int depositeMoney(Account toAccount, double money) {
		
		// TODO Auto-generated method stub
		int status = 0;
		Account account = getAccountDetails(toAccount);

		String SQL = "update account set balance=? where accountnumber=?";
		double amt = account.getBalance() + money;

		status = jdbcTemplate.update(SQL, amt, toAccount.getAccountNumber());
		
		return status;
	}
	private Account getAccountDetails(Account fromAccount) {
		String SQL = "select * from account where accountnumber=?";
		Account account = jdbcTemplate.queryForObject(SQL, new AccountRowMapper(), fromAccount.getAccountNumber());
		return account;
	}
}
