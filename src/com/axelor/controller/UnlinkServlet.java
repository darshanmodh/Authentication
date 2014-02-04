package com.axelor.controller;

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

import com.axelor.model.User;
import com.axelor.services.DAO;

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
		
		DAO dao = new DAO();			
		try {			
			HttpSession session = request.getSession();
	        User user = (User)session.getAttribute("user");
	        boolean rows = dao.unlink(user);
	        session.invalidate();
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
