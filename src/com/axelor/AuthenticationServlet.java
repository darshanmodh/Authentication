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
import javax.servlet.http.HttpSession;

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
		
		/*String name = null;
		String city = null;
		String birthDate = null;
		String mobile = null;
		String email = null;*/
		
		
		response.setContentType("text/html");
	      try {
	    	 Connection connect = Test.connect();
	         connect.setAutoCommit(false);
	         statement = connect.createStatement();
	         ResultSet resultSet = statement.executeQuery( "SELECT * FROM users WHERE uname = '" + userName + "';");
	         
	         while ( resultSet.next() ) {
	        	//dbPassword = resultSet.getString("pass");
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
	         connect.commit();
	         connect.close();
	         
	         if ( password.equals( user.getPassword() ) ) {
	        	 HttpSession session = request.getSession();
	        	 session.setAttribute("user", user);
	        	 /*session.setAttribute("uname",userName);
	        	 session.setAttribute("pass", dbPassword);
	        	 session.setAttribute("bdate",birthDate);
	        	 session.setAttribute("name",name);
	        	 session.setAttribute("city",city);
	        	 session.setAttribute("email",email);
	        	 session.setAttribute("mno",mobile);*/	        	 
	        	 response.sendRedirect("welcome.jsp");
	         } else {
	        	 //out.println("<script>alert('Username or Password incorrect.');</script>");
	        	 ServletContext context = getServletContext();
	        	 RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
	        	 request.setAttribute("flag", "false");
	        	 rd.forward(request, response);
	        	// response.sendRedirect("index.jsp?flag=false");
	         }
	         
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}

}
