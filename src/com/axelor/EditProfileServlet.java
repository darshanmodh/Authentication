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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet({ "/EditProfileServlet", "/edit" })
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("welcome.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName( request.getParameter("uname") );
		user.setName( request.getParameter("name") );
		user.setCity( request.getParameter("city") );
		user.setEmail( request.getParameter("email") );
		user.setMobile( request.getParameter("mno") );
		user.setBirthDate( request.getParameter("bdate") );
		
		PrintWriter out = response.getWriter();
		Statement statement = null;
		String sql;
		
		response.setContentType("text/html");
				
		try {
	    	 Connection connect = Test.connect();	    	 
	         connect.setAutoCommit(false);
	         statement = connect.createStatement();
	         sql = "UPDATE users SET email=' ', mno=' ' where uname='" + user.getUserName() + "';";
           	 int rows = statement.executeUpdate(sql);
           	 statement.close();
          	 connect.commit();
           	 
	         statement = connect.createStatement();
	         
           	 
	         if ( Test.checkEmail( user.getEmail() ) ) {
	        	 out.println("Email allready Exist !!.");
		         out.println("<a href='edit.jsp'>Editing Again...</a>");
	         } else if ( Test.checkMobile( user.getMobile() ) ) {
	        	 out.println("Mobile No. allready Exist !!.");
		         out.println("<a href='edit.jsp'>Editing Again...</a>");
	         } else {
	          	 sql = "UPDATE users SET name='" + user.getName() + "', city= '" + user.getCity() + "', email='" + user.getEmail() + "', mno='" + user.getMobile() + "', bdate='" + user.getBirthDate() + "' where uname='" + user.getUserName() + "';";
	           	 rows = statement.executeUpdate(sql);
	           	 statement.close();
	           	 connect.commit();
	           	 connect.close();
	           	 
	        	 HttpSession session = request.getSession();
	        	 session.setAttribute("user", user);;	
	           	 
	           	 if( rows > 0) {
	           		 response.sendRedirect("welcome.jsp");
	           	 } else {
	           		out.println("Editing Failed...");
	           		 out.println("<a href='edit.jsp'>Edit Again...</a>");
	           	 }
	         }
	      } catch (Exception e) {
	    	 out.println("Editing Failed.");
	    	 out.println("<a href='edit.jsp'>Edit Again...</a>");
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }	
	}

}
