package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "Show All Transaction":
                viewAllTransaction(request, response);
                break;
            case "transactionmade":
            	transactionmade(request,response);
            	break;
            case"transactionreceived":
            	transactionreceived(request,response,action);
            	break;
            case "withdrawal":  
            case "deposit":  
                transactiontype(request, response,action);
                break;
            
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void viewAllTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String accountNo = null;

        try {
            // Database connection parameters
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Query to fetch account number
            String sql = "SELECT account_no FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                accountNo = rs.getString("account_no");  // Corrected column name
            } else {
                out.println("<p>No account found for email: " + email + "</p>");
                return;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
            return;
        }

        // Fetch and display transactions
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT * FROM transactions WHERE from_account_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            // Generate HTML for transaction table
            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background-color: #fff; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("h1 { color: #333; }");
            out.println("</style></head><body>");
            
            out.println("<html><body>");
            out.println("<h1>Transaction History for Account " + accountNo + "</h1>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>ID</th><th>From Account</th><th>To Account</th><th>Type</th><th>Amount</th><th>Date</th><th>Description</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("transaction_id") + "</td>");
                out.println("<td>" + rs.getString("from_account_no") + "</td>");
                out.println("<td>" + (rs.getString("to_account_no") == null ? "-" : rs.getString("to_account_no")) + "</td>");
                out.println("<td>" + rs.getString("transaction_type") + "</td>");
                out.println("<td>" + rs.getBigDecimal("amount") + "</td>");
                out.println("<td>" + rs.getTimestamp("transaction_date") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }

    private void transactionmade(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String accountNo = null;

        try {
            // Database connection parameters
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Query to fetch account number
            String sql = "SELECT account_no FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                accountNo = rs.getString("account_no");  // Corrected column name
            } else {
                out.println("<p>No account found for email: " + email + "</p>");
                return;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
            return;
        }
        
        
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT * FROM transactions WHERE from_account_no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();

            // Generate HTML for transaction table
            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background-color: #fff; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("h1 { color: #333; }");
            out.println("</style></head><body>");
            out.println("<html><body>");
            out.println("<h1>Transaction History for Account " + accountNo + "</h1>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>ID</th><th>From Account</th><th>To Account</th><th>Type</th><th>Amount</th><th>Date</th><th>Description</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("transaction_id") + "</td>");
                out.println("<td>" + rs.getString("from_account_no") + "</td>");
                out.println("<td>" + (rs.getString("to_account_no") == null ? "-" : rs.getString("to_account_no")) + "</td>");
                out.println("<td>" + rs.getString("transaction_type") + "</td>");
                out.println("<td>" + rs.getBigDecimal("amount") + "</td>");
                out.println("<td>" + rs.getTimestamp("transaction_date") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
    
	private void transactionreceived(HttpServletRequest request, HttpServletResponse response,String transaction_type) throws IOException{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String accountNo = null;

        try {
            // Database connection parameters
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Query to fetch account number
            String sql = "SELECT account_no FROM users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                accountNo = rs.getString("account_no");  // Corrected column name
            } else {
                out.println("<p>No account found for email: " + email + "</p>");
                return;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
            return;
        }
        
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT * FROM transactions WHERE to_account_no = ? and transaction_type=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountNo);
            ps.setString(2, transaction_type);
            ResultSet rs = ps.executeQuery();

            // Generate HTML for transaction table
            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background-color: #fff; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("h1 { color: #333; }");
            out.println("</style></head><body>");
            out.println("<html><body>");
            out.println("<h1>Transaction History for Account " + accountNo + "</h1>");
            out.println("<table border='1' cellpadding='10'>");
            out.println("<tr><th>ID</th><th>From Account</th><th>To Account</th><th>Type</th><th>Amount</th><th>Date</th><th>Description</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("transaction_id") + "</td>");
                out.println("<td>" + rs.getString("from_account_no") + "</td>");
                out.println("<td>" + (rs.getString("to_account_no") == null ? "-" : rs.getString("to_account_no")) + "</td>");
                out.println("<td>" + rs.getString("transaction_type") + "</td>");
                out.println("<td>" + rs.getBigDecimal("amount") + "</td>");
                out.println("<td>" + rs.getTimestamp("transaction_date") + "</td>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
        
        
	}

	

	private void transactiontype(HttpServletRequest request, HttpServletResponse response, String transactionType) throws IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        String email = request.getParameter("email");
	        String accountNo = null;

	        try {
	            // Database connection parameters
	            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
	            String dbUser = "root";
	            String dbPassword = "1234";

	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

	            // Fetch account number based on the user's email
	            String sql = "SELECT account_no FROM users WHERE email = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, email);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                accountNo = rs.getString("account_no");
	            } else {
	                out.println("<p>No account found for email: " + email + "</p>");
	                return;
	            }

	            rs.close();
	            ps.close();

	            // Fetch transactions based on account number and transaction type
	            String transactionSql = "SELECT * FROM transactions WHERE from_account_no = ? AND transaction_type = ?";
	            ps = conn.prepareStatement(transactionSql);
	            ps.setString(1, accountNo);
	            ps.setString(2, transactionType);
	            rs = ps.executeQuery();

	            // Generate HTML to display the transactions
	            out.println("<html><head><style>");
	            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }");
	            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background-color: #fff; }");
	            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }");
	            out.println("th { background-color: #4CAF50; color: white; }");
	            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
	            out.println("h1 { color: #333; }");
	            out.println("</style></head><body>");
	            out.println("<html><body>");
	            out.println("<h1>Transaction History for Account " + accountNo + " (" + transactionType + ")</h1>");
	            out.println("<table border='1' cellpadding='10'>");
	            out.println("<tr><th>ID</th><th>From Account</th><th>To Account</th><th>Type</th><th>Amount</th><th>Date</th><th>Description</th></tr>");

	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt("transaction_id") + "</td>");
	                out.println("<td>" + rs.getString("from_account_no") + "</td>");
	                out.println("<td>" + (rs.getString("to_account_no") == null ? "-" : rs.getString("to_account_no")) + "</td>");
	                out.println("<td>" + rs.getString("transaction_type") + "</td>");
	                out.println("<td>" + rs.getBigDecimal("amount") + "</td>");
	                out.println("<td>" + rs.getTimestamp("transaction_date") + "</td>");
	                out.println("<td>" + rs.getString("description") + "</td>");
	                out.println("</tr>");
	            }

	            out.println("</table>");
	            out.println("</body></html>");

	            rs.close();
	            ps.close();
	            conn.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	            out.println("<p>Error: " + e.getMessage() + "</p>");
	        }
	    }

    
}
