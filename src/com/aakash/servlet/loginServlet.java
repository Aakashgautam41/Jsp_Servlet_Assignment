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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				// Form data
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String  storedEmail = "" ;
				String storedPassword = "" ;
				String fname = "";
				String hashedPassword = "" ;
				
				
				//Step1 Set content type
				response.setContentType("text/html");
				
				//Step2 Get PrintWriter
				PrintWriter out = response.getWriter();
				
				//Step3 Dynamic content
				
				// Password Hashing
		        try {
		        	// Create MessageDigest instance for MD5
					MessageDigest  md = MessageDigest.getInstance("MD5");
					
					 //Add password bytes to digest
		            md.update(password.getBytes());
		            
		          //Get the hash's bytes
		            byte[] bytes = md.digest();
		            
		          //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            
		          //Get complete hashed password in hex format
		             hashedPassword = sb.toString();
		             out.println(hashedPassword);
		            
		            
				} 
		        catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        
				try {
					
					//1. Get a connection to the database
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USER_DETAILS","root","root");
					//out.println("connection created:"+connection);
					
					//2. Create a prepared statement
					PreparedStatement pStatement = connection.prepareStatement("SELECT  EMAIL, PASSWORD, FIRST_NAME  FROM J1_ACCOUNT_MEMBER WHERE EMAIL=  ?");
					
					//3. Set the parameters
					pStatement.setString(1, email);
	
					
					//4. Execute SQL query
					ResultSet  result = pStatement.executeQuery();
		
					while (result.next()) {              										 // Position the cursor              
						 storedEmail = result.getString(1);       				 // Retrieve the first column value
						 storedPassword = result.getString(2);      		// Retrieve the first column value
						 fname = result.getString(3);
						 out.println("EmailRetrieved =  " + storedEmail + "Password retrieved =  " + storedPassword + " Name retrieved =  " + fname);
						}
					out.println(storedEmail.getClass().getName());
					
					if(  hashedPassword.equals( storedPassword) ) {
						out.println("User found !!!");
					}
					else {
						out.println(" Incorrect credentials  !!!");

					}
					result.close();   
					connection.commit();
					connection.close();  
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

	}

}
