package com.axelor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Test {

	static public Connection connect() throws Exception {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(
		   "jdbc:postgresql://localhost:5432/mydb","axelor", "lmdpdlaa");
		return connection;
	}
	
	static public boolean checkUname(String columnName) throws Exception {
		
		 Connection connect = Test.connect();
    	 Statement statement = null;
    	 String sql;
    	 ResultSet  resultSet = null;
    	 boolean checkFlag = false;
    			 
         connect.setAutoCommit(false);
         statement = connect.createStatement();
                  sql = " SELECT * FROM users where uname='" + columnName + "';";
         resultSet = statement.executeQuery(sql);
         
         while ( resultSet.next() ) {
        	checkFlag = true;
         }	        	 
               
		return checkFlag;
		
	}
	
	static public boolean checkEmail(String columnName) throws Exception {
		
		 Connection connect = Test.connect();
   	 Statement statement = null;
   	 String sql;
   	 ResultSet  resultSet = null;
   	 boolean checkFlag = false;
   			 
        connect.setAutoCommit(false);
        statement = connect.createStatement();
                 sql = " SELECT * FROM users where email='" + columnName + "';";
        resultSet = statement.executeQuery(sql);
        
        while ( resultSet.next() ) {
       	checkFlag = true;
        }	        	 
              
		return checkFlag;
		
	}
	
	static public boolean checkMobile(String columnName) throws Exception {
		
		 Connection connect = Test.connect();
   	 Statement statement = null;
   	 String sql;
   	 ResultSet  resultSet = null;
   	 boolean checkFlag = false;
   			 
        connect.setAutoCommit(false);
        statement = connect.createStatement();
                 sql = " SELECT * FROM users where mno='" + columnName + "';";
        resultSet = statement.executeQuery(sql);
        
        while ( resultSet.next() ) {
       	checkFlag = true;
        }	        	 
              
		return checkFlag;
		
	}
}

