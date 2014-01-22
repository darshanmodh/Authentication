package com.axelor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
		String confirmPassword = request.getParameter("cpass");
		user.setName( request.getParameter("name") );
		user.setCity( request.getParameter("city") );
		user.setMobile( request.getParameter("mno") );
		user.setEmail( request.getParameter("email") );
		user.setBirthDate( request.getParameter("bdate") );
		
		String password = user.getPassword();
		
		ServletContext context = getServletContext();
   	 	RequestDispatcher rd = context.getRequestDispatcher("/register.jsp");
		
   	 	if ( password.equals( confirmPassword ) ) {
   	 		try {
		    	 Connection connect = DAO.connect();
		         Statement statement = connect.createStatement();
		         
		         if( DAO.check( "uname", user.getUserName() ) ) {	 
		        	 request.setAttribute("flag", "uname");
		         } else if ( DAO.check("email", user.getEmail() ) ) {
		        	 request.setAttribute("flag", "email");
		         } else if ( DAO.check( "mno", user.getMobile() ) ) {
		        	 request.setAttribute("flag", "mno");
		         } else {
		        	 DAO.insert(user);
		        	 rd = context.getRequestDispatcher("/index.jsp");
		        	 request.setAttribute("flag", "success");
		        	 }
		         rd.forward(request, response);
		         } catch (Exception e) {
			    	 request.setAttribute("flag", "fail");
			    	 rd.forward(request, response);
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
		         }	 
	      } else {
	    	  request.setAttribute("flag", "mismatch");
	    	  rd.forward(request, response);
	    	  }
		}
	}
