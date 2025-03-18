package com.example.dao;

import com.example.model.Account;
import com.example.model.User;
import java.sql.*;

public class AccountDAO {
	
	 public static boolean isAccountValid(String accountNo, String email,String password) {
	        boolean isValid = false;
	        try (Connection connection = DataBaseConnection.initializeDatabase()) {
	            String query = "SELECT * FROM users WHERE account_no = ? AND email = ? and password=?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, accountNo);
	            ps.setString(2, email);
	            ps.setString(3, password);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                
	                isValid = true;
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return isValid;
	    }
	public static User getAccountDetails(String email) {
		User user=null;
		try (Connection connection = DataBaseConnection.initializeDatabase()) {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("account_no"),rs.getString("name"),rs.getString("email"),rs.getInt("deposit"),rs.getString("gender"),rs.getString("number"),rs.getString("password"));
                System.out.print(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
	}
    public static Account getAccountBalance(String accountNo) {
        Account account = null;
        try (Connection connection = DataBaseConnection.initializeDatabase()) {
            String query = "SELECT * FROM account WHERE account_no = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account(rs.getString("account_no"), rs.getString("account_type"), rs.getDouble("balance"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }
    public static boolean withdraw_transaction(String accountNo,Double amount) {
    	Connection connection = null;
        try {
            connection = DataBaseConnection.initializeDatabase();
            connection.setAutoCommit(false); // Start transaction
            String insertTransactionQuery = "INSERT INTO transactions (from_account_no, to_account_no, transaction_type, amount, description) " +
                    "VALUES (?, NULL, 'withdrawal', ?, 'WithDrawal from account " + accountNo + "')";
			PreparedStatement psWithdrawal = connection.prepareStatement(insertTransactionQuery);
			psWithdrawal.setString(1, accountNo);
			psWithdrawal.setDouble(2, amount);
			psWithdrawal.executeUpdate();
			connection.commit();
            return true;
        }
        catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                try 
                {
                    connection.rollback();
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } 
        finally 
        {
            try 
            {
                if (connection != null) connection.setAutoCommit(true); // Reset auto-commit mode
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
    public static boolean withdraw(String accountNo, double amount) {
        Connection connection = null;
        try {
            connection = DataBaseConnection.initializeDatabase();
            connection.setAutoCommit(false); // Start transaction

            // Lock the row for the account
            String selectQuery = "SELECT balance FROM account WHERE account_no = ? FOR UPDATE";
            PreparedStatement psSelect = connection.prepareStatement(selectQuery);
            psSelect.setString(1, accountNo);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    String updateQuery = "UPDATE account SET balance = balance - ? WHERE account_no = ?";
                    PreparedStatement psUpdate = connection.prepareStatement(updateQuery);
                    psUpdate.setDouble(1, amount);
                    psUpdate.setString(2, accountNo);
                    psUpdate.executeUpdate();
                    connection.commit(); // Commit transaction
                    withdraw_transaction(accountNo,amount);
                    return true;
                }
            }
            connection.rollback(); // Insufficient balance, rollback transaction
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true); // Reset auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean transfer(String fromAccount, String toAccount, double amount) {
        Connection connection = null;
        try {
            connection = DataBaseConnection.initializeDatabase();
            connection.setAutoCommit(false); // Start transaction

            // Withdraw from source account
            if (!withdraw(fromAccount, amount)) {
                connection.rollback();
                return false;
            }

            // Deposit to destination account
            String depositQuery = "UPDATE account SET balance = balance + ? WHERE account_no = ?";
            PreparedStatement ps = connection.prepareStatement(depositQuery);
            ps.setDouble(1, amount);
            ps.setString(2, toAccount);
            ps.executeUpdate();

            // Insert withdrawal transaction into the transactions table
            String insertTransactionQuery = "INSERT INTO transactions (from_account_no, to_account_no, transaction_type, amount, description) " +
                                            "VALUES (?, NULL, 'withdrawal', ?, 'WithDrawal from account " + fromAccount + "')";
            PreparedStatement psWithdrawal = connection.prepareStatement(insertTransactionQuery);
            psWithdrawal.setString(1, fromAccount);
            psWithdrawal.setDouble(2, amount);
            psWithdrawal.executeUpdate();

            // Insert deposit transaction into the transactions table
            insertTransactionQuery = "INSERT INTO transactions (from_account_no, to_account_no, transaction_type, amount, description) " +
                                     "VALUES (?, ?, 'deposit', ?, 'Transfer to account " + toAccount + "')";
            PreparedStatement psDeposit = connection.prepareStatement(insertTransactionQuery);
            psDeposit.setString(1,fromAccount);
            psDeposit.setString(2, toAccount);
            psDeposit.setDouble(3, amount);
            psDeposit.executeUpdate();

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true); // Reset auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deposit_transaction(String accountNo,Double amount) {
    	Connection connection = null;
        try {
            connection = DataBaseConnection.initializeDatabase();
            connection.setAutoCommit(false); // Start transaction
            String insertTransactionQuery = "INSERT INTO transactions (from_account_no, to_account_no, transaction_type, amount, description) " +
                    "VALUES (?, ?, 'deposit', ?, 'Deposit to account " + accountNo + "')";
			PreparedStatement psDeposit = connection.prepareStatement(insertTransactionQuery);
			psDeposit.setString(1,accountNo);
			psDeposit.setString(2, accountNo);
			psDeposit.setDouble(3, amount);
			psDeposit.executeUpdate();

			connection.commit();
            return true;
        }
        catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                try 
                {
                    connection.rollback();
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } 
        finally 
        {
            try 
            {
                if (connection != null) connection.setAutoCommit(true); // Reset auto-commit mode
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean deposit(String accountNo, double amount) {
        try (Connection connection = DataBaseConnection.initializeDatabase()) {
            String query = "UPDATE account SET balance = balance + ? WHERE account_no = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setString(2, accountNo);
            
            int rowsAffected = ps.executeUpdate();
            deposit_transaction(accountNo,amount);
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
