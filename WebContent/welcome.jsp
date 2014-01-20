<%@ page import="java.sql.*"%>
<%@ page import="com.axelor.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
		Statement statement = null;
		HttpSession sessions;
		User user = null;
		String userName = null;
%>

<%
		response.setHeader("Pragma","no-cache");  
		response.setHeader("Cache-Control","no-store");  
		response.setHeader("Expires","0");  
		response.setDateHeader("Expires",-1); 
		
		try {
			HttpSession sessions = request.getSession();
			user = (User) sessions.getAttribute("user");
			userName = user.getUserName();
		
			if( userName == null ) {
				response.sendRedirect("index.jsp");
			} 
		} catch( Exception e) {
			response.sendRedirect("index.jsp");
		}
%>

<link href="css/welcome.css" rel="stylesheet" type="text/css"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
Welcome, <%= userName %>
<div><img src="images/profile.png" width="100" height="100"></div>
<div align="right"><ul type="none"><li><a href="edit.jsp" >Edit Profile</a> 
<li><a href="unlink">Unlink your Account</a>
<li><a href="changepass.jsp">Change Password</a>
<li><a href="logout">Logout</a></ul></div>

<center>

<table border="1" cellspacing="5" cellpadding="5">

<tr>
<th> Name </th>
<td> <%= user.getName() %> </td>
</tr>

<tr>
<th> City </th>
<td> <%= user.getCity() %> </td>
</tr>

<tr>
<th> BirthDate </th>
<td> <%= user.getBirthDate() %> </td>
</tr>

<tr>
<th> Email </th>
<td> <%= user.getEmail() %> </td>
</tr>

<tr>
<th> Mobile </th>
<td> <%= user.getMobile() %> </td>
</tr>

</table>
</center>
</body>
</html>