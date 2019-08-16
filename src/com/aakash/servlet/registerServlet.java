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

import org.apache.log4j.Logger;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Get instance of Log4j
    static final Logger LOGGER = Logger.getLogger(loginServlet.class);

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
		String email = request.getParameter("user-email_confirmation");
		String password = request.getParameter("password");
		String zipCode = request.getParameter("zip");
		String  hashedPassword= "";
		int userId = 0;

		 LOGGER.info("This is a logging statement from registerServlet");
			
		//Step1 Set content type
		response.setContentType("text/html");
		
		//Step2 Get PrintWriter
		PrintWriter out = response.getWriter();
		
		//Step3 Dynamic content
		hashedPassword = PasswordHash.hashPassword(password);
		
	    // Database Connection
		// Get instance of Singleton class
		JDBCSingleton  jdbc = JDBCSingleton.getInstance();
		
		// Insert data in J1_ACCOUNT_MEMBER
		try {  
           int i = jdbc.insert( firstName, lastName , email, hashedPassword ); 
            if ( i > 0 ) {  
            	 LOGGER.info(" Data has been inserted successfully in J1_ACCOUNT_MEMBER");  
            }
            else{  
           	 	LOGGER.info("Data has not been inserted in J1_ACCOUNT_MEMBER ");      
            }  
        } catch (Exception e) {  
    	 	 	LOGGER.info(e);  
        } 
		
		//Get email,password and fname and userId  from J1_ACCOUNT_MEMBER
        try  {  
	           ResultSet  rs = jdbc.view(email); 
		       while (rs.next()) {  
	                    userId = rs.getInt(4); 
	           }
         }
	       catch (Exception e) {  
	   	 		LOGGER.info(e);  
	        }
		
		
		// Insert entry in J1_LOGIN_AUDIT_TRAIL
		try {  
           int i = jdbc.insertLoginAudit(userId,email);
            if ( i > 0 ) {  
           		LOGGER.info(" Data has been inserted successfully in J1_LOGIN_AUDIT_TRAIL ");  
            }
            else{  
           	 	LOGGER.info("Data has not been inserted in J1_LOGIN_AUDIT_TRAIL");      
            }  
        } 
		catch (Exception e) {  
			 	LOGGER.info(e);  
        }
	
		// Redirect user to login page
		response.sendRedirect("form");
	}

}