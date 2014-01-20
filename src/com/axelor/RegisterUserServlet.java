package com.axelor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet({ "/RegisterUserServlet", "/add" })
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
		User user = new User();
		
		response.setHeader("Pragma","no-cache");  
		response.setHeader("Cache-Control","no-store");  
		response.setHeader("Expires","0");  
		response.setDateHeader("Expires",-1); 
		
		user.setUserName( request.getParameter("uname") );
		user.setPassword( request.getParameter("pass") );
		user.setName( request.getParameter("name") );
		user.setCity( request.getParameter("city") );
		user.setMobile( request.getParameter("mno") );
		user.setEmail( request.getParameter("email") );
		user.setBirthDate( request.getParameter("bdate") );
		
		String password = user.getPassword();
		String confirmPassword = request.getParameter("cpass");
		
		PrintWriter out = response.getWriter();
		Statement statement = null;
		String sql;
		
		response.setContentType("text/html");
		out.println("Hello " + user.getName() + ",");
		
		if ( password.equals( confirmPassword ) ) {
	      try {
	    	 Connection connect = Test.connect();
	    	 
	         connect.setAutoCommit( false );
	         statement = connect.createStatement();
	         
	         if( Test.checkUname( user.getUserName() ) ) {	        	 
	        	 out.println("User Name allready Exist !!.");
		         out.println("<a href='register.jsp'>Register Again...</a>");
	         } else if ( Test.checkEmail( user.getEmail() ) ) {
	        	 
	        	 out.println("Email allready Exist !!.");
		         out.println("<a href='register.jsp'>Register Again...</a>");
	         } else if ( Test.checkMobile( user.getMobile() ) ) {
	        	 out.println("Mobile No. allready Exist !!.");
		         out.println("<a href='register.jsp'>Register Again...</a>");
	         } else {
	         
	        	 sql = "INSERT INTO users (uname, pass, name, city, email, mno, bdate) "
	               + "VALUES ('" + user.getUserName() + "','" + user.getPassword() + "','" + user.getName() + "','" + user.getCity() + "','" + user.getEmail() + "','" + user.getMobile() + "','" + user.getBirthDate() + "');";
	         
	        	 statement.executeUpdate(sql);
	        	 out.println("You Have Registered successfully.");
	        	 out.println("<a href='index.jsp'>Login here...</a>");
	        } 
	         
	         statement.close();
	         connect.commit();
	         connect.close();
	      } catch (Exception e) {
	    	 out.println("Registration Failed.");
	    	 out.println("<a href='register.jsp'>Register Again...</a>");
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }	 
		} else {
			out.println("Password mismatch. Please enter password and confirm password same");
			out.println("<a href='register.jsp'>Register Again...</a>");
		}
	}

}
