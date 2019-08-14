package com.aakash.servlet;
import java.io.*;
import java.sql.*;
import java.util.Properties;

 
// Create a JDBCSingleton class.
public class JDBCSingleton {
	
	
	// Database details
		static final String JDBC_DRIVER  = "com.mysql.cj.jdbc.Driver";
		 static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/USER_DETAILS";
	 
	// JDBC Database Credentials
	    static final String JDBC_USER = "root";
	    static final String JDBC_PASS = "root";
		String Account_Table = "J1_ACCOUNT_MEMBER";                                               
		String Login_Trail_Table = " J1_LOGIN_AUDIT_TRAIL";
	
	 // Static member holds only one instance of the JDBCSingleton class.  
	 private static JDBCSingleton jdbc;  
	
	 // JDBCSingleton prevents the instantiation from any other class.  
     private JDBCSingleton() {  }  

     // Now we are providing gloabal point of access.  
     public static JDBCSingleton getInstance() {    
    	 if (jdbc == null){  
    		 jdbc = new JDBCSingleton();  
    	 }  
	     return jdbc;  
     }  
     
     //  To get the connection from methods like insert, view etc.   
     public static Connection getConnection()throws ClassNotFoundException, SQLException  
     {    
         Connection connection = null;  
     	 Class.forName( JDBC_DRIVER );
         connection = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
         return connection;  
    	
     }  
     
     // To insert the record into table J1_ACCOUNT_MEMBER   
     public int insert(String fname,String lname, String email, String password) throws SQLException  
     {  
         Connection c = null;  
         PreparedStatement ps = null;     
         int recordCounter = 0;  
           
         try {  
        	 System.out.println("connection creation started for J1_ACCOUNT_MEMBER- insert()");
             c= this.getConnection();  
             ps= c.prepareStatement("INSERT INTO  J1_ACCOUNT_MEMBER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD ) VALUES(?,?,?,?)");  
             ps.setString(1, fname);  
             ps.setString(2, lname);  
             ps.setString(3, email);  
             ps.setString(4, password);  
             recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 System.out.println("connection:"+ c);
        	 e.printStackTrace();
    	 }
         finally{  
           if ( ps!=null ){  
        	   ps.close();  
           }
           if(c!=null){  
        	   c.close();  
           }   
         }  
         return recordCounter;  
     }  
     
     // To view the data from table J1_ACCOUNT_MEMBER         
     public ResultSet view(String email) throws SQLException  
     {  
	   Connection connection = null;  
	   PreparedStatement ps = null;  
	   ResultSet rs = null;  
                 
       try {
    	   System.out.println("connection creation started for J1_ACCOUNT_MEMBER- view()");
		   connection = this.getConnection();  
		   ps = connection.prepareStatement("SELECT  EMAIL, PASSWORD, FIRST_NAME, USER_ID  FROM  J1_ACCOUNT_MEMBER WHERE EMAIL=  ?");  
		   ps.setString(1, email);  
		   rs=ps.executeQuery();  
		   System.out.println(rs.getClass().getName());    
       
       } 
       catch (Exception e) { 
    	   System.out.println(e);
	   }  
		return rs;  
     }
     
     // To insert a record into the J1_LOGIN_AUDIT_TRAIL  
     public int insertLoginAudit(int userId, String email) throws SQLException  
     {  
         Connection c = null;  
         PreparedStatement ps = null;       
         int recordCounter = 0;  
         
         try {  
        	 System.out.println("connection creation started for J1_LOGIN_AUDIT_TRAIL");
             c= this.getConnection();  
             ps= c.prepareStatement("INSERT INTO"+ Login_Trail_Table +" ( USER_ID, EMAIL) VALUES (?,?)");  
             ps.setLong(1, userId);  
             ps.setString(2, email);  
             recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 System.out.println("connection:"+c);
        	 e.printStackTrace();
    	 }
         finally{  
           if ( ps!=null ){  
        	   ps.close();  
           }
           if(c!=null){  
        	   c.close();  
           }   
         }  
         return recordCounter;  
     }

     // To view the data from table J1_LOGIN_AUDIT_TRAIL         
     public ResultSet viewLoginAudit(String email) throws SQLException  
     {  
	   Connection connection = null;  
	   PreparedStatement ps = null;  
	   ResultSet rs = null;  
                 
       try {
    	   System.out.println("connection creation started for J1_LOGIN_AUDIT_TRAIL- viewLoginAudit()");
		   connection = this.getConnection();  
		   ps = connection.prepareStatement("SELECT LOGIN_ATTEMPT FROM "+  Login_Trail_Table +" WHERE EMAIL= ?");  
		   ps.setString(1, email);  
		   rs=ps.executeQuery();  
		   System.out.println(rs.getClass().getName());    
       
       } 
       catch (Exception e) { 
    	   System.out.println(e);
	   }  
       
       return rs;  
     }

     // To update the data of table J1_LOGIN_AUDIT_TRAIL
     public int updateLoginAudit(int attemptCount, String email) throws SQLException  
     {  
         Connection c = null;  
         PreparedStatement ps = null;       
         int recordCounter = 0;  
         
         try {  
        	 System.out.println("connection creation started for J1_LOGIN_AUDIT_TRAIL -UPDATE");
             c= this.getConnection();  
             ps= c.prepareStatement(" UPDATE " + Login_Trail_Table + " SET LOGIN_ATTEMPT = ? WHERE EMAIL = ?");  
             ps.setLong(1, attemptCount);  
             ps.setString(2, email);  
             recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 System.out.println("connection:"+c);
        	 e.printStackTrace();
    	 }
         finally{  
           if ( ps!=null ){  
        	   ps.close();  
           }
           if(c!=null){  
        	   c.close();  
           }   
         }  
         return recordCounter;  
     }
}

//End of JDBCSingleton class  