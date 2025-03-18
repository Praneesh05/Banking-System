package com.example.dao;
import com.example.model.Account;
import com.example.model.User;
import java.sql.*;
public class TransactionDAO {
	public static String retrieveaccount(String email) {
		try(Connection connection=DataBaseConnection.initializeDatabase()){
			String query="select account_no from users where email=?";
		}
	}
}
