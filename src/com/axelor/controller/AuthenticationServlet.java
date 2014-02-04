package com.axelor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

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
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet({ "/AuthenticationServlet", "/signIn" })
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		PrintWriter out = response.getWriter();
		Statement statement = null;
		String userName = request.getParameter("uname");
		String password = request.getParameter("pass");
		String dbPassword = null;
		DAO dao = new DAO();
		
		response.setContentType("text/html");
	      try {
	    	 user = dao.retrive(userName);
	    	 
	         if ( password.equals( user.getPassword() ) ) {
	        	 HttpSession session = request.getSession();
	        	 session.setAttribute("user", user);
	        	 session.setAttribute("allUserList", dao.setAllUser() );
	        	 List<User> lst = dao.setAllUser();
	        	 Iterator<User> it = lst.iterator();
	        	 
	        	 while(it.hasNext()) {
	        		 System.out.println(" " + it.next().getUserName());
	        	 }
	        	 
	        	 
	        	 response.sendRedirect("welcome.jsp");
	         } else {
	        	 ServletContext context = getServletContext();
	        	 RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
	        	 request.setAttribute("flag", "false");
	        	 rd.forward(request, response);
	         }
	         
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}

}
