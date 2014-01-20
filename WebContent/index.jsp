<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/index.css" rel="stylesheet" type="text/css"> 
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		   setTimeout(function(){
		  $("div.message").fadeOut("slow", function () {
		  	$("div.message").remove();
		      });
		}, 1000);
		 });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>User Login Page</title>
</head>
<body onload="javascript:window.history.forward(1);" background="images/login.jpg">
<form name="loginForm" method="POST" action="signIn">
<center><h1>LOGIN</h1>
<table border="0" cellpadding="5" cellspacing="5">
<tr>
	<td> Username </td> <td> <input type="text" name="uname" placeholder="Enter Username" required="required"> </td>
</tr>

<tr>
	<td> Password </td> <td> <input type="password" name="pass" placeholder="Enter Password" required="required"> </td>
</tr>

<tr>
	<td colspan="2"> <center> <input type="submit" value="Sign In" name="submit"> 
	<input type="button" value="New User ?" onclick="window.location='register.jsp';"></center> </td>
</tr>

</table>  

<%

HttpSession sessions = request.getSession();
String userName = (String) sessions.getAttribute("uname");	

if( userName != null) {
	response.sendRedirect("welcome.jsp");
} 

String flag = (String) request.getAttribute("flag");
if( flag == "false" ) {
	%> <div id="message" class="message"> Invalid Username or Password !! </div> <%
} else if ( flag ==  "unlink") {
	%> <div id="message" class="message"> Your Account is unlinked, To Re-Login you must have to Register again !! </div> <%
} else if ( flag ==  "logout") {
	%> <div id="message" class="message"> You are Logged out !! </div> <%
}

%>
</center>
</form>
</body>
</html>


