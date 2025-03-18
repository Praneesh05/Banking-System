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
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
            String dbUser = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // If login is successful, store email in session
                HttpSession session = request.getSession();
                session.setAttribute("email", email);  // Store email in session
                response.sendRedirect("options.jsp");  // Redirect to options.jsp
            } else {
                // If login fails
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid Email or Password!');");
                out.println("location='login.jsp';");
                out.println("</script>");
            }

            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Database connection error!');");
            out.println("location='login.jsp';");
            out.println("</script>");
        } finally {
            out.close();
        }
    }
}
