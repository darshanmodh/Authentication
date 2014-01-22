package com.axelor.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.axelor.model.User;


public class DAO {
	
	private static Connection connection;
	private static Statement statement;
	private static String sql;
	static ResultSet  resultSet;

	static public Connection connect() throws Exception {
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(
		   "jdbc:postgresql://localhost:5432/mydb","axelor", "lmdpdlaa");
		return connection;
	}
	
	static public void insert(User user) throws Exception {
		
		connect();
		connection.setAutoCommit(false);
		statement = connection.createStatement();
		sql = "INSERT INTO users (uname, pass, name, city, email, mno, bdate) "
	               + "VALUES ('" + user.getUserName() + "','" + user.getPassword() + "','" + user.getName() + "','" + user.getCity() + "','" + user.getEmail() + "','" + user.getMobile() + "','" + user.getBirthDate() + "');";
	    statement.executeUpdate(sql);
	    statement.close();
       	connection.commit();
       	connection.close();
	}
	
	static public int update(User user) throws Exception {
		connect();
		connection.setAutoCommit(false);
		statement = connection.createStatement();
		sql = "UPDATE users SET name='" + user.getName() + "', city= '" + user.getCity() + "', email='" + user.getEmail() + "', mno='" + user.getMobile() + "', bdate='" + user.getBirthDate() + "' where uname='" + user.getUserName() + "';";
       	int rows = statement.executeUpdate(sql);
       	statement.close();
       	connection.commit();
       	connection.close();
       	return rows;
	}

	public static void updateME(User user) throws Exception {

		connect();
   	 	connection.setAutoCommit(false);
        statement = connection.createStatement();
        sql = "UPDATE users SET email=' ', mno=' ' where uname='" + user.getUserName() + "';";
      	int rows = statement.executeUpdate(sql);
      	statement.close();
     	connection.commit();
     	connection.close();
	}
	
	public static User retrive(String userName) throws Exception {
		connect();
		User user =new User();
        connection.setAutoCommit(false);
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery( "SELECT * FROM users WHERE uname = '" + userName + "';");
        
        while ( resultSet.next() ) {
	       	user.setUserName( resultSet.getString("uname") );
	       	user.setPassword( resultSet.getString("pass") );
	       	user.setName( resultSet.getString("name") );
	       	user.setCity( resultSet.getString("city") );
	       	user.setEmail( resultSet.getString("email") );
	       	user.setMobile( resultSet.getString("mno") );
	       	user.setBirthDate( resultSet.getString("bdate") );
        }
        
        resultSet.close();
        statement.close();
        connection.commit();
        connection.close();
		return user;
        

	}
		
	static public boolean check(String columnName, String columnValue) throws Exception {
		 
		 boolean checkFlag = false;
		 
		 connect();    			 
		 connection.setAutoCommit(false);
         statement = connection.createStatement();
         sql = " SELECT * FROM users where " + columnName + "= '" + columnValue + "';";
         resultSet = statement.executeQuery(sql);
         
         while ( resultSet.next() ) {
        	checkFlag = true;
         }	        	 
        statement.close();
		return checkFlag;	
	}	
}

