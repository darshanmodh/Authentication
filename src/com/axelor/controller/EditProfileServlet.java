package com.axelor.controller;

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
import javax.servlet.http.HttpSession;

import com.axelor.model.User;
import com.axelor.services.DAO;

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
		
		response.setContentType("text/html");
		ServletContext context = getServletContext();
   	 	RequestDispatcher rd = context.getRequestDispatcher("/edit.jsp");
				
		try {
			 DAO.updateME(user);
			 
	         if ( DAO.check( "email", user.getEmail() ) ) {
	        	 request.setAttribute("flag", "email");
	         } else if ( DAO.check( "mno", user.getMobile() ) ) {
	        	 request.setAttribute("flag", "mno");
	         } else {
	          	 int rows = DAO.update(user);
	        	 HttpSession session = request.getSession();
	        	 session.setAttribute("user", user);;	
	           	 if( rows > 0) {
	           		rd = context.getRequestDispatcher("/welcome.jsp");
	           		request.setAttribute("flag", "success");
	           	 } else {
	           		request.setAttribute("flag", "fail");
	           	 }
	         }
	         rd.forward(request, response);
	      } catch (Exception e) {
	    	  request.setAttribute("flag", "fail");
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	      }	
	}

}
