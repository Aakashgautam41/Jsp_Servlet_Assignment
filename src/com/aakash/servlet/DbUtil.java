package com.aakash.servlet;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
	
		static Connection connection = null;
		static {
			try {
				InitialContext context =  new InitialContext();
				DataSource ds =  (DataSource) context.lookup("java:comp/env/jdbc/USER_DETAILS");
				try {
					connection =  ds.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch(NamingException e) {
				e.printStackTrace();
			}
	
		}
		public static Connection getConnection() {
			return connection;
		}

}
