package com.example.dao;

import com.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Get user details by account number
    public User getUserByAccountNo(String accountNo) {
        User user = null;
        try (Connection conn = DataBaseConnection.initializeDatabase()) { // Use correct method name
            String sql = "SELECT * FROM users WHERE account_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setAccountNo(rs.getString("account_no"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setDeposit(rs.getInt("deposit"));
                user.setGender(rs.getString("gender"));
                user.setNumber(rs.getString("number"));
            }
        } 
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Get balance by account number
    public int getBalanceByAccountNo(String accountNo) {
        int balance = 0;
        try (Connection conn = DataBaseConnection.initializeDatabase()) { // Use correct method name
            String sql = "SELECT deposit FROM users WHERE account_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, accountNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                balance = rs.getInt("deposit");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return balance;
    }

    // Transfer funds between accounts
    public boolean transferFunds(String fromAccount, String toAccount, int amount) {
        try (Connection conn = DataBaseConnection.initializeDatabase()) { // Use correct method name
            conn.setAutoCommit(false);

            // Deduct amount from source account
            String deductSQL = "UPDATE users SET deposit = deposit - ? WHERE account_no = ?";
            PreparedStatement deductStmt = conn.prepareStatement(deductSQL);
            deductStmt.setInt(1, amount);
            deductStmt.setString(2, fromAccount);
            int rowsUpdated1 = deductStmt.executeUpdate();

            // Add amount to destination account
            String addSQL = "UPDATE users SET deposit = deposit + ? WHERE account_no = ?";
            PreparedStatement addStmt = conn.prepareStatement(addSQL);
            addStmt.setInt(1, amount);
            addStmt.setString(2, toAccount);
            int rowsUpdated2 = addStmt.executeUpdate();

            if (rowsUpdated1 == 1 && rowsUpdated2 == 1) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Withdraw funds from account
    public boolean withdrawFunds(String accountNo, int amount) {
        try (Connection conn = DataBaseConnection.initializeDatabase()) { // Use correct method name
            String sql = "UPDATE users SET deposit = deposit - ? WHERE account_no = ? AND deposit >= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, amount);
            stmt.setString(2, accountNo);
            stmt.setInt(3, amount);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated == 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Deposit funds to account
    public boolean depositFunds(String accountNo, int amount) {
        try (Connection conn = DataBaseConnection.initializeDatabase()) { // Use correct method name
            String sql = "UPDATE users SET deposit = deposit + ? WHERE account_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, amount);
            stmt.setString(2, accountNo);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated == 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
