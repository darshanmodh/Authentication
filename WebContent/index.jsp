<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:if test="${user != null}">
   <c:redirect url="welcome.jsp"/>
</c:if>
<tr>
	<td colspan="2"> <center> <input type="submit" value="Sign In" name="submit"> 
	<input type="button" value="New User ?" onclick="window.location='register.jsp';"></center> </td>
</tr>
</table>
<div id="message" class="message"> 
<c:set var="flag" scope="request" value="${requestScope.flag}"/>
<c:choose>
	<c:when test="${flag == 'unlink'}">
       Your Account is unlinked, To Re-Login you must have to Register again !! 
    </c:when>
    <c:when test="${flag == 'false'}">
       Invalid Username or Password !! 
    </c:when>
    <c:when test="${flag == 'logout'}">
       You are Logged out !! 
    </c:when>
    <c:when test="${flag == 'success'}">
       You have registered successfully !!
    </c:when>
</c:choose>  
</div>
</center>
</form>
</body>
</html>


