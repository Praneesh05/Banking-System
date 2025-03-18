package com.example.servlet;

import com.example.dao.AccountDAO;
import com.example.model.*;

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
import jakarta.servlet.RequestDispatcher; // Missing for forwarding the request

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "viewAccount":
                viewAccount(request, response);
                break;
            case "viewBalance":
                viewBalance(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "withdraw":
                withdraw(request, response);
                break;
            case "deposit":
                deposit(request, response);
                break;
            case "transfer":
                transfer(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void viewAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String accountNo = request.getParameter("account_no");
        String email = request.getParameter("email");
        
        // Check if the account number belongs to the provided email
        
            User user = AccountDAO.getAccountDetails(email);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("account-details.jsp");
            dispatcher.forward(request, response);
        
    }

    private void viewBalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String accountNo = request.getParameter("account_no").trim();
    	String email = request.getParameter("email").trim();
    	String password=request.getParameter("password");

        // Check if the account number belongs to the provided email
        if (AccountDAO.isAccountValid(accountNo, email,password)) {
            Account user = AccountDAO.getAccountBalance(accountNo);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-balance.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp?error=InvalidAccountOrEmail");
        }
    }
    

    private void withdraw(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	String accountNo = request.getParameter("account_no").trim();
        	String email = request.getParameter("email").trim();
        	String password=request.getParameter("password");
            double amount = Double.parseDouble(request.getParameter("amount"));

            // Check if the account number belongs to the provided email
            if (AccountDAO.isAccountValid(accountNo, email,password)) {
                boolean success = AccountDAO.withdraw(accountNo, amount);
                if (success) {
                    response.sendRedirect("success.jsp");
                } else {
                    response.sendRedirect("error.jsp?error=InsufficientBalance");
                }
            } else {
                response.sendRedirect("error.jsp?error=InvalidAccountOrEmail");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?error=InvalidAmount");
        }
    }

    private void deposit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	String accountNo = request.getParameter("account_no").trim();
        	String email = request.getParameter("email").trim();
        	String password=request.getParameter("password");
            double amount = Double.parseDouble(request.getParameter("amount"));

            // Check if the account number belongs to the provided email
            if (AccountDAO.isAccountValid(accountNo, email,password)) {
                boolean success = AccountDAO.deposit(accountNo, amount);
                if (success) {
                    response.sendRedirect("success.jsp");
                } 
                else {
                    response.sendRedirect("error.jsp");
                }
            } 
            else {
                response.sendRedirect("error.jsp?error=InvalidAccountOrEmail");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?error=InvalidAmount");
        }
    }

    private void transfer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fromAccount = request.getParameter("from_account");
            String toAccount = request.getParameter("to_account");
            String email = request.getParameter("email");
            String password=request.getParameter("password");
            double amount = Double.parseDouble(request.getParameter("amount"));

            // Check if the "from account" belongs to the provided email
            if (AccountDAO.isAccountValid(fromAccount, email,password)) {
                boolean success = AccountDAO.transfer(fromAccount, toAccount, amount);
                if (success) {
                    response.sendRedirect("success.jsp");
                } else {
                    response.sendRedirect("error.jsp?error=TransferFailed");
                }
            } else {
                response.sendRedirect("error.jsp?error=InvalidAccountOrEmail");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?error=InvalidAmount");
        }
    }
}
