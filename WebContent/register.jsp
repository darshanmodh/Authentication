<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-2.0.3.js"></script>
<script >
function checkPwd(){

	var pass = document.getElementById("pass").value;
	var cpass = document.getElementById("cpass").value;
	
	if( pass != cpass) {
		document.getElementById("cpass").focus();
		alert(" Password are Mismatch !!! ");
		
	}
}

$(document).ready(function(){
	   setTimeout(function(){
	  $("div.message").fadeOut("slow", function () {
	  	$("div.message").remove();
	      });
	}, 1000);
	   });
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
<center><h1>Registration</h1>
<form action="add" method="post" name="myForm">

<table cellpadding="5" cellspacing="5">

<tr>
<td>Name <sup><font color="RED">*</font></sup></td>
<td><input type="text" name="name" placeholder="Enter your name" required="required"></td>
</tr>

<tr>
<td>User Name <sup><font color="RED">*</font></sup></td>
<td> <input type="text" name="uname" placeholder="Choose your username" required="required"></td>
</tr>

<tr>
<td>Password <sup><font color="RED">*</font></sup></td>
<td><input type="password" id="pass" name="pass" placeholder="Choose your password" required="required"></td>
</tr>

<tr>
<td>Confirm Password <sup><font color="RED">*</font></sup></td>
<td><input type="password" id="cpass" name="cpass" placeholder="Retype your password" required="required" onblur="checkPwd()"></td>
</tr>

<tr>
<td>City</td>
<td><input type="text" name="city" placeholder="Enter your city"></td>
</tr>

<tr>
<td>Birthdate <sup><font color="RED">*</font></sup></td>
<td><input type="text" name="bdate" placeholder="YYYY-MM-DD" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required="required"></td>
</tr>

<tr>
<td>Email ID</td>
<td><input type="email" name="email"  placeholder="abc@pqr.com" ></td>
</tr>

<tr>
<td>Mobile </td>
<td><input type="text" name="mno" maxlength="10"  placeholder="1234567890"></td>
</tr>

<tr></tr>

<tr>
<td colspan="2"><center><input type="submit" value="Register" name="submit">
						<input type="reset" value="Reset">
						<input type="button" value="Back" onclick="window.location='index.jsp';"></center></td>
</tr>
</table>
</form>
<div id="message" class="message"> 
<c:set var="flag" scope="request" value="${requestScope.flag}"/>
<c:choose>
    <c:when test="${flag == 'uname'}">
       Username already exists !! 
    </c:when>
    <c:when test="${flag == 'email'}">
       Email already exists !! 
    </c:when>
    <c:when test="${flag == 'mno'}">
       Mobile number already exists !!
    </c:when>
    <c:when test="${flag == 'mismatch'}">
       Password and confirm password are mismatched !!
    </c:when>
    <c:when test="${flag == 'fail'}">
       <script> alert('Registration Failed !!'); </script>
    </c:when>
</c:choose>  
</div>
</center>
</body>
</html>