package com.aakash.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	/*@Override
	public void init(ServletConfig config) throws ServletException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("class found");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USER_DETAILS","root","root");
			System.out.println("connection created..!"+connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("connection created..end!"+connection);
		super.init(config);
	}*/
	
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		try {
			
			//1. Get a connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USER_DETAILS","root","root");
			out.println("connection created:"+connection);
			//2. Create a prepared statement
			PreparedStatement pStatement = connection.prepareStatement("INSERT INTO J1_ACCOUNT_MEMBER(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD ) VALUES(?,?,?,?)");
			
			//3. Set the parameters
			pStatement.setString(1,"John");
			pStatement.setString(2,"Doe");
			pStatement.setString(3,"john@gmail.com");
			pStatement.setString(4,"123456");
			
			//4. Execute SQL query
			//ResultSet result = pStatement.executeQuery();
			int i= pStatement.executeUpdate();  
			out.println(i+" records inserted");  
			  
			connection.close();  
           
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("HAHAHA");
		
	}

}
