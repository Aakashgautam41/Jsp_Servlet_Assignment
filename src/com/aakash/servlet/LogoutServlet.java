package com.aakash.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Get instance of Log4j
    static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
		
		//Step3 Dynamic content
        
		HttpSession session = request.getSession();
		
		if(session.getAttribute("username")!=null)
		{
			   response.sendRedirect("welcome");
		}
		else {
			   response.sendRedirect("index");
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step1 Set content type
				response.setContentType("text/html");
				
				//Step2 Get PrintWriter
				PrintWriter out = response.getWriter();
				
				//Step3 Dynamic content
		        
		        HttpSession  session = request.getSession(); 
		       Object fname = session.getAttribute("username");
		        session.invalidate();  
		        
		        response.sendRedirect("index");
		        LOGGER.info( fname+": Successfully logged out!");  
	}

}