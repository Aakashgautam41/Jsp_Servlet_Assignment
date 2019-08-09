package com.aakash.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at : ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Form data
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String zipCode = request.getParameter("zip");
		String  hashedPassword= "";

			
		//Step1 Set content type
		response.setContentType("text/html");
		
		//Step2 Get PrintWriter
		PrintWriter out = response.getWriter();
		
		//Step3 Dynamic content
		PasswordHash hash = new PasswordHash();
		hashedPassword = hash.hashPassword(password);
		
	     // Database Connection
		// Get instance of Singleton class
		JDBCSingleton  jdbc = JDBCSingleton.getInstance();
		try {  
           int i = jdbc.insert( firstName, lastName , email, hashedPassword );   //Insert Method in Singleton class
            if ( i >0 ) {  
            	out.println(" Data has been inserted successfully");  
            }
            else{  
                out.println("Data has not been inserted ");      
            }  
        } catch (Exception e) {  
        	out.println(e);  
        } 
	
		// Redirect user to login page
		response.sendRedirect("form.jsp");
	}

}
