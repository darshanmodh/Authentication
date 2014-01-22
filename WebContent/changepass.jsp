<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script >
function checkPwd(){

	var pass = document.getElementById("pass").value;
	var cpass = document.getElementById("cpass").value;
	
	if( pass != cpass) {
		document.getElementById("cpass").focus();
		//alert(" Password are Mismatch !!! ");
		
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
</head>
<body>
<center><h1>Change Password</h1>
<form action="change" method="post" name="myForm">

<table cellpadding="5" cellspacing="5">

<tr>
<td>Old Password <sup><font color="RED">*</font></sup></td>
<td> <input type="password" name="opass" placeholder="Enter Your Old password" required="required"></td>
</tr>

<tr>
<td>Password <sup><font color="RED">*</font></sup></td>
<td><input type="password" id="pass" name="pass" placeholder="Choose your password" required="required"></td>
</tr>

<tr>
<td>Confirm Password <sup><font color="RED">*</font></sup></td>
<td><input type="password" id="cpass" name="cpass" placeholder="Retype your password" required="required" onblur="checkPwd()"></td>
</tr>

<tr></tr>

<tr>
<td colspan="2"><center><input type="submit" value="Change" name="submit">
						<input type="reset" value="Reset">
						<input type="button" value="Back" onclick="window.location='welcome.jsp';"></center></td>
</tr>

</table>

</form>

<div id="message" class="message"> 
<c:set var="flag" scope="request" value="${requestScope.flag}"/>
<c:choose>
    <c:when test="${flag == 'success'}">
       Your password has been changed !!
    </c:when>
    <c:when test="${flag == 'owrong'}">
       Your Old Password is wrong !! 
    </c:when>
    <c:when test="${flag == 'mismatch'}">
       New password & confirm Password are Mismatch !!
    </c:when>
</c:choose>  
</div>
</center>
</body>
</html>