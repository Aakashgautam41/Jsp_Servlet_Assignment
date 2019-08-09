package com.aakash.servlet;

import java.io.*;
import java.sql.*;

public class JDBCSingleton {
		
	// STEP 1  
	// Create a JDBCSingleton class.
	// Static member holds only one instance of the JDBCSingleton class.  
	
	 private static JDBCSingleton jdbc;  
	
	 // JDBCSingleton prevents the instantiation from any other class.  
     private JDBCSingleton() {  }  

   // Now we are providing gloabal point of access.  
     public static JDBCSingleton getInstance() {    
                     if (jdbc==null)  
                         {  
                                    jdbc = new  JDBCSingleton();  
                           }  
                     return jdbc;  
         }  
     
     //  To get the connection from methods like insert, view etc.   
     public static Connection getConnection()throws ClassNotFoundException, SQLException  
     {    
         Connection connection = null;  
     	Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USER_DETAILS","root","root");
         return connection;  
     }  
     
   // To insert the record into the database   
     public int insert( String fname,String lname, String email, String password ) throws SQLException  
     {  
         Connection c = null;  
         PreparedStatement ps = null;     
         int recordCounter = 0;  
           
         try {  
               System.out.println("connection creation started");
                 c= this.getConnection();  
                 ps= c.prepareStatement("INSERT INTO J1_ACCOUNT_MEMBER(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD ) VALUES(?,?,?,?)");  
                 ps.setString(1, fname);  
                 ps.setString(2, lname);  
                 ps.setString(3, email);  
                 ps.setString(4, password);  
                 recordCounter = ps.executeUpdate();  
                   
         } 
         catch (Exception e) { 
        	 System.out.println("connection:"+c);
        	 e.printStackTrace(); }
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
     
   //  To view the data from the database        
     public  ResultSet view(String email) throws SQLException  
     {  
           Connection connection = null;  
           PreparedStatement ps = null;  
           ResultSet rs = null;  
                 
               try {  
                     
                       connection = this.getConnection();  
                       ps = connection.prepareStatement("SELECT  EMAIL, PASSWORD, FIRST_NAME  FROM J1_ACCOUNT_MEMBER WHERE EMAIL=  ?");  
                       ps.setString(1, email);  
                       rs=ps.executeQuery();  
                       System.out.println(rs.getClass().getName());    
//                       while (rs.next()) {  
//                                System.out.println("Name= " + rs.getString(3) + "\t" + "Email= " + rs.getString(1));      
//                        
//                       } 
                      // return rs;
               
         } 
               catch (Exception e) { 
            	   System.out.println(e);
            	   }  
//         finally{  
//                   if(rs!=null){  
//                       rs.close();  
//                   }
//                   if (ps!=null){  
//                     ps.close();  
//                 }
//                   if(connection!=null){  
//                     connection.close();  
//                 }   
//               }
			return rs;  
     }  

}
//End of JDBCSingleton class  
