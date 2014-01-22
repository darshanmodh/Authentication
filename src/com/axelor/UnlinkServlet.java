package com.axelor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class UnlinkServlet
 */
@WebServlet({ "/UnlinkServlet", "/unlink" })
public class UnlinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnlinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Statement statement;
			String sql = null;
			Connection connect = DAO.connect();	    	 
	        connect.setAutoCommit(false);
	        statement = connect.createStatement();
	        HttpSession session = request.getSession();
	        User user = (User)session.getAttribute("user");
	        
	      	sql = "DELETE FROM users where uname='" + user.getUserName() + "';";
	       	statement.executeUpdate(sql);
	       	session.invalidate();
	       	connect.commit();
	       	statement.close();
	       	connect.close();
           	 
           	ServletContext context = getServletContext();
	       	RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
	       	request.setAttribute("flag", "unlink");
	       	rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
