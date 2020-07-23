package com.spring.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Account;

public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int arg1) throws SQLException {
		Account account=new Account();
		account.setAccountNumber(rs.getLong("accountnumber"));
		account.setAccountHolder(rs.getString("accountholder"));
		account.setBalance(rs.getDouble("balance"));
		return account;
	}

}
