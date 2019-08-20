package com.aakash.servlet;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Get instance of Log4j
    static final Logger LOGGER = Logger.getLogger(loginServlet.class);
       
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
			//Step1 Set content type
			response.setContentType("text/html");
			
			//Step2 Get PrintWriter
			PrintWriter out = response.getWriter();
			
			HttpSession session = request.getSession();
			out.println(session.getAttribute("username"));
			
			if(session.getAttribute("username")!=null && session.getAttribute("username").equals(true))
			{
			   response.sendRedirect("welcome");
			}
			else {
				response.sendRedirect("form");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			// Form data
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String storedEmail = "" ;
			String storedPassword = "" ;
			String fname = "";
			int userId = 0;
			
			// Variables
			int loginAttempt = 0;
			int loginChances = 5;
			int lockTime = 1000*60;
			
			 LOGGER.info("This is a logging statement from loginServlet");
			
			 if(PoolDemo.validate(email)) {
				// LOGGER.info("Database connection pool is working");
			}
			//Step1 Set content type
			response.setContentType("text/html");
			
			//Step2 Get PrintWriter
			PrintWriter out = response.getWriter();
			
			//Step3 Dynamic content  
			// Get instance of Singleton class
			JDBCSingleton jdbc = JDBCSingleton.getInstance();  
	        
			//1. Get login attempt value from J1_LOGIN_AUDIT_TRAIL
			try {
				ResultSet rs = jdbc.viewLoginAudit(email);
				while (rs.next()) {  
					loginAttempt = rs.getInt(1);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(loginAttempt >= loginChances) {
				//Block user
				Timer timer = new Timer(true);
				TimerTask task = new TimerTask() {
			    	@Override
			        public void run() {
			    
					    LOGGER.info("User is BLOCKED.");
			    	}
				};
			
			    timer.scheduleAtFixedRate(task, 1000,1000);
			    timer.cancel();
			    
			    try {
			        Thread.sleep(lockTime);
			        timer.purge();
			        
			    } 
			    catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			    
			    
			    // Reset LOGIN_ATTEMPT to 0
			    try {  
			            int i = jdbc.updateLoginAudit(0, email);
			            if ( i > 0 ) {  
			            	 LOGGER.info("LOGIN_ATTEMPT has been set to 0 in J1_LOGIN_AUDIT_TRAIL");  
			            }
			            else{  
			            	 LOGGER.info("LOGIN_ATTEMPT has not been updated");      
			            }  
		        } 
				catch (Exception e) {  
						LOGGER.info(e);  
		        }
			    
			    finally {
			    	//set the error message request variable
				    request.setAttribute("blockMessage","Your account has been locked for"+ lockTime + "hours");
				    
				    RequestDispatcher rd=request.getRequestDispatcher("form");
				    rd.forward(request,response);
			    }
			}
			 
			// When Login is successful
			else {
				//Get email,password and fname from j1_account_member
		        try  {  
		           ResultSet  rs = jdbc.view(email); 
			       while (rs.next()) {  
	                        storedEmail = rs.getString(1);
	                        storedPassword = rs.getString(2);
	                        fname = rs.getString(3);
	                        userId = rs.getInt(4);
	               } 
			       
			       // Instance of PasswordHash class
			       boolean isPasswordMatched = PasswordHash.checkPassword(password, storedPassword) ;
			       
			       if( isPasswordMatched &&  email.equals(storedEmail)) {
			    	   LOGGER.info( fname + "'s  password matched  !!");
						
						// Add Cookie
				        Cookie cookie = new Cookie("username", fname ); 
				        response.addCookie(cookie);
				        LOGGER.info( fname + " : Cookies has been set");
				        // Add Session
				        HttpSession session = request.getSession();
				        session.setAttribute("username", fname);
				        LOGGER.info( fname + " : Session has been set");
				        
						// Redirect user to welcome page
						response.sendRedirect("welcome");
						LOGGER.info( fname + " : redirected to welcome page");
						
						// Reset LOGIN_ATTEMPT to 0
					    try {  
				            int i = jdbc.updateLoginAudit(0, email);
				            if ( i > 0 ) {  
				            	 LOGGER.info("LOGIN_ATTEMPT has been set to 0 in J1_LOGIN_AUDIT_TRAIL ");  
				            }
				            else{  
				            	 LOGGER.info("LOGIN_ATTEMPT has not been updated ");      
				            }  
				        } catch (Exception e) {  
				        	LOGGER.info(e);  
				        }
						

					}
					else {
						 LOGGER.info( fname + "'s login failed !! ");  
						 
						//set the error message request variable
					    request.setAttribute("errorMessage","Incorrect Email address or Password &#9785;");
						
						// Redirect user to login page after failed login
					    RequestDispatcher rd=request.getRequestDispatcher("form");
					    rd.forward(request,response);
					    LOGGER.info( fname + " : redirected to index page");
				
						//2. If password is incorrect then increase counter and store in table
						loginAttempt = loginAttempt+1;
						try {  
				            int i = jdbc.updateLoginAudit(loginAttempt, email);
				            if ( i > 0 ) {  
				            	 LOGGER.info("LOGIN_ATTEMPT count increased  in J1_LOGIN_AUDIT_TRAIL ");  
				            }
				            else{  
				            	 LOGGER.info("LOGIN_ATTEMPT  has not been updated ");      
				            }  
				        } 
						catch (Exception e) {  
				        	out.println(e);  
				        }
						
						
					}
		         } 
		        catch (Exception e) {                                                                        
		          e.printStackTrace();
		        }    

			}
			
	}
}