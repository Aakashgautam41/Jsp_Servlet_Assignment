package com.aakash.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoolDemo {
	public static boolean validate(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		boolean status = false;
		
		 try {
			if(connection == null || connection.isClosed()) {
				 connection = DbUtil.getConnection();
			 }
			
			ps = connection.prepareStatement("SELECT EMAIL FROM J1_LOGIN_AUDIT_TRAIL WHERE USER_ID = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			status = rs.next();
			//System.out.println("Database pooling ran");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	
}
