<%@ page import="java.sql.*"%>
<%@ page import="com.axelor.controller.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	


<c:set var="allUserList" scope="session" value="${sessionScope.allUserList}"/>
<c:if test="${allUserList == null}">
   <c:redirect url="index.jsp"/>
</c:if>

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

<center>
<h3>Employee's Information</h3>
<table border="1" cellspacing="5" cellpadding="5">

<tr>
<th>UserName</th>
<th>Name</th>
<th>City</th>
<th>EMail</th>
<th>Mobile</th>
<th>BirthDate</th>
</tr>


<c:forEach var="lst" items="${allUserList }">  
        <tr>
        <td> ${lst.userName } </td>
        <td> ${lst.name } </td>
        <td> ${lst.city } </td>
        <td> ${lst.email } </td>
        <td> ${lst.mobile } </td>
        <td> ${lst.birthDate } </td>        
        </tr>
</c:forEach>  

</table>

<div id="message" class="message"> 
<br>
<input type="button" value="Back" onclick="window.location='welcome.jsp';"></center> </td>
</div>
</center>
</body>
</html>