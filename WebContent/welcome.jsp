<%@ page import="java.sql.*"%>
<%@ page import="com.axelor.controller.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:if test="${user == null}">
   <c:redirect url="index.jsp"/>
</c:if>

<link href="css/welcome.css" rel="stylesheet" type="text/css">
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script>
		$(function() {
		$( document ).tooltip({
		track: true
		});
		});	
		
		$(document).ready(function(){
			   setTimeout(function(){
			  $("div.message").fadeOut("slow", function () {
			  	$("div.message").remove();
			      });
			}, 1000);
			   });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
<p>
<div id="bloc1"> Welcome, ${user.getUserName()} </div>
<div id="bloc2">
<a href="showalluser.jsp" ><img src="images/showusers.jpg" width="50" height="50" title="Show All Users"></a>
<a href="edit.jsp" ><img src="images/edit.jpeg" width="50" height="50" title="Edit Profile"></a> 
<a href="changepass.jsp"><img src="images/change-password.png" width="50" height="50" title="Change Password"></a>
<a href="unlink"><img src="images/close.jpg" width="50" height="50" title="Unlink your Account"></a>
<a href="logout"><img src="images/logout.jpeg" width="50" height="50" title="Logout"></a></div>
<div><img src="images/profile.png" width="100" height="100" title="profile picture"></div>
<center>
<h3>Employee's Information</h3>
<table border="1" cellspacing="5" cellpadding="5">
<tr>
<th> Name </th>
<td> ${ user.getName() } </td>
</tr>

<tr>
<th> City </th>
<td> ${ user.getCity() } </td>
</tr>

<tr>
<th> BirthDate </th>
<td> ${ user.getBirthDate() } </td>
</tr>

<tr>
<th> Email </th>
<td> ${ user.getEmail() } </td>
</tr>

<tr>
<th> Mobile </th>
<td> ${ user.getMobile() } </td>
</tr>

</table>
<div id="message" class="message"> 
<c:set var="flag" scope="request" value="${requestScope.flag}"/>
<c:choose>
    <c:when test="${flag == 'success'}">
       Your profile is successfully updated !!
    </c:when>
</c:choose>  
</div>
</center>
</body>
</html>