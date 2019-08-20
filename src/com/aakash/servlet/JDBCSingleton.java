package com.aakash.servlet;
import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.apache.log4j.Logger;

 
// Create a JDBCSingleton class.
public class JDBCSingleton {
	
	// Get instance of Log4j
    static final Logger LOGGER = Logger.getLogger(JDBCSingleton.class);
    
    //Get instance of Properties
    static Properties properties = Props.getProps();
	
	// Database details
		static final String JDBC_DRIVER  =  properties.getProperty("JDBC_DRIVER");
		static final String JDBC_DB_URL = properties.getProperty("JDBC_DB_URL");
	 
	// JDBC Database Credentials
	    static final String JDBC_USER = properties.getProperty("JDBC_USER");
	    static final String JDBC_PASS = properties.getProperty("JDBC_PASS");
		String ACCOUNT_TABLE = "J1_ACCOUNT_MEMBER";                                               
		String LOGIN_TRAIL_TABLE = " J1_LOGIN_AUDIT_TRAIL";
		
		
	
	 // Static member holds only one instance of the JDBCSingleton class.  
	 private static JDBCSingleton jdbc;
	
	 // JDBCSingleton prevents the instantiation from any other class.  
     private JDBCSingleton() {  }  

     // Now we are providing gloabal point of access.  
     public static JDBCSingleton getInstance() {    
    	 if (jdbc == null){  
    		 jdbc = new JDBCSingleton();  
    	     //LOGGER.info("This is a logging statement from JDBCSingleton");
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
        	 LOGGER.info(" J1_ACCOUNT_MEMBER :insert() ");
             c= this.getConnection();  
             ps= c.prepareStatement("INSERT INTO "+ ACCOUNT_TABLE +" (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD ) VALUES(?,?,?,?)");  
             ps.setString(1, fname);  
             ps.setString(2, lname);  
             ps.setString(3, email);  
             ps.setString(4, password);  
             recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 LOGGER.info("connection:"+ c);
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
    	   LOGGER.info("J1_ACCOUNT_MEMBER :view() ");
		   connection = this.getConnection();  
		   ps = connection.prepareStatement("SELECT  EMAIL, PASSWORD, FIRST_NAME, USER_ID  FROM "+  ACCOUNT_TABLE +" WHERE EMAIL=  ?");  
		   ps.setString(1, email);  
		   rs=ps.executeQuery();  
       
       } 
       catch (Exception e) { 
    	   LOGGER.info(e);
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
        	 LOGGER.info(" J1_LOGIN_AUDIT_TRAIL: insertLoginAudit()");
             c= this.getConnection();  
             ps= c.prepareStatement("INSERT INTO"+ LOGIN_TRAIL_TABLE +" ( USER_ID, EMAIL) VALUES (?,?)");  
             ps.setLong(1, userId);  
             ps.setString(2, email);  
             recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 LOGGER.info("connection:"+ c);
        	 LOGGER.info(e);
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
    	   LOGGER.info("J1_LOGIN_AUDIT_TRAIL: viewLoginAudit()");
		   connection = this.getConnection();  
		   ps = connection.prepareStatement("SELECT LOGIN_ATTEMPT FROM "+  LOGIN_TRAIL_TABLE +" WHERE EMAIL= ?");  
		   ps.setString(1, email);  
		   rs=ps.executeQuery();   
       
       } 
       catch (Exception e) { 
    	   LOGGER.info(e);
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
        	 LOGGER.info("J1_LOGIN_AUDIT_TRAIL:updateLoginAudit()");
             c= this.getConnection();  
             ps= c.prepareStatement(" UPDATE " + LOGIN_TRAIL_TABLE + " SET LOGIN_ATTEMPT = ? WHERE EMAIL = ?");  
             ps.setLong(1, attemptCount);  
             ps.setString(2, email);  
             recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 LOGGER.info("connection:"+ c);
        	 LOGGER.info(e);
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