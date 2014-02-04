package com.axelor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet({ "/ChangePasswordServlet", "/change" })
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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

		DAO dao  = new DAO();
		
		User user = (User) request.getSession().getAttribute("user");
		String newPassword = request.getParameter("pass");
		String confirmPassword = request.getParameter("cpass");
		String oldPassword = request.getParameter("opass");
		String dbPass = null;
		
		ServletContext context = getServletContext();
       	RequestDispatcher rd = context.getRequestDispatcher("/changepass.jsp");
		
		try {
			dbPass = dao.getDbPassword( user.getUserName() );
			
			if ( oldPassword.equalsIgnoreCase(dbPass) ) {				 
	           	if ( newPassword.equals(confirmPassword) ) {
	           		 
	           		boolean ans = dao.changePass(user, newPassword);
	           		
	           		request.setAttribute("flag", "success");
		           	HttpSession session = request.getSession();
		           	session.setAttribute("user", user);
		           	
				} else {
		           	request.setAttribute("flag", "mismatch");
				}
			} else {
	           	request.setAttribute("flag", "owrong");
			}
			rd.forward(request, response);
		} catch (Exception e) {
	    	 System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
		} 
	}

}
