package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.dao.user_dao;
import com.example.model.*;

@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String s = request.getParameter("intdeposite");
        String gender = request.getParameter("gender");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        int deposit=Integer.parseInt(s);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setDeposit(deposit);
        user.setGender(gender);
        user.setNumber(number);
        user.setPassword(password);
        try {
            // Generate account number
            user_dao userDAO = new user_dao();
            String accountNo = userDAO.generateAccountNo();
            user.setAccountNo(accountNo);
            // Insert user into the database
            boolean success = userDAO.insertUser(user);

            if (success) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registration SUCCESSFUL!');");
                out.println("location='login.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registration failed! Please try again.');");
                out.println("location='signin.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('An error occurred! Please try again later.');");
            out.println("location='signin.jsp';");
            out.println("</script>");
        } finally {
            out.close();
        }
    }
}
