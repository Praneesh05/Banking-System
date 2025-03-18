package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import com.example.model.User;


public class user_dao {

    // Method to insert user into the database
    public boolean insertUser(User user) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO users (account_no, name, email, deposit, gender, number, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String accountInsertQuery = "INSERT INTO account (account_no, account_type, balance) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement accountStmt = null;
        
        try {
            connection = DataBaseConnection.initializeDatabase();
            connection.setAutoCommit(false); // Begin transaction

            // Hash the password before storing it
            //String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            // Insert into users table
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getAccountNo());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getDeposit());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getNumber());
            ps.setString(7, user.getPassword()); // Store hashed password

            int result = ps.executeUpdate();

            // Insert into account table
            accountStmt = connection.prepareStatement(accountInsertQuery);
            accountStmt.setString(1, user.getAccountNo());
            accountStmt.setString(2, "savings"); // Default to savings account
            accountStmt.setInt(3, user.getDeposit()); // Initial balance

            accountStmt.executeUpdate();

            connection.commit(); // Commit transaction
            return result > 0; // Return true if user was inserted successfully

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Rollback in case of error
            }
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (accountStmt != null) accountStmt.close();
            if (connection != null) connection.setAutoCommit(true); // Reset auto-commit
        }
    }

    // Generate a unique alphanumeric account number
    public String generateAccountNo() {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder accountNo = new StringBuilder();
        
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(alphanumeric.length());
            accountNo.append(alphanumeric.charAt(index));
        }
        
        return accountNo.toString();
    }
}

