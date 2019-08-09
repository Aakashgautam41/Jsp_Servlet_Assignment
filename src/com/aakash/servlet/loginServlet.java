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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		        
				// Database Connection
				// Get instance of Singleton class
				JDBCSingleton  jdbc = JDBCSingleton.getInstance();  
		        
		        try  {  
		           ResultSet  rs = jdbc.view(email); 
		           out.println(rs);
			       while (rs.next()) {  
	                        out.println("Name= " + rs.getString(3) + "\t" + "Email= " + rs.getString(1));
	                        storedEmail = rs.getString(1);
	                        storedPassword = rs.getString(2);
	                        fname = rs.getString(3);
	               } 
			       
			       
			       // Instance of  password hash class
			       PasswordHash hash = new PasswordHash();
			       boolean isPasswordMatched = hash.checkPassword(password, storedPassword) ;
			       
			       if( isPasswordMatched) {
						out.println("User found !!!");
						
						// Add Cookie
				        Cookie cookie = new Cookie("username", fname );		//creating cookie object  
				        response.addCookie(cookie);
				        
				        // Add Session
				        HttpSession session = request.getSession();
				        session.setAttribute("username", fname);
				        
						 // Redirect user to login page
						response.sendRedirect("welcome.jsp");

					}
					else {
						out.println(" Incorrect credentials  !!!");

					}
		         } 
		        catch (Exception e) {                                                                        
		          e.printStackTrace();
		        } 
		      
	}

}
