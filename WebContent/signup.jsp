<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
 <%@ page import="application.*" %>
 <%@ page import="model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="template_top.jsp" ><jsp:param value="Log In" name="pageName"/></jsp:include>
<%
	User currentUser = new User();
	boolean isLoggedIn = false;

	if(LoginData.isLoggedIn())
	{
		currentUser = LoginData.getCurrentUser();
		isLoggedIn = true;
	}
	
	
	
	

	

	
	
	//if we're not logged in, display the form.
	if(isLoggedIn == false)
	{
%>	
		<h3>Sign Up:</h3>
		<div class="contact_form">
		<form method="post" action="signupservlet">
			<table>
			<colgroup span="2" ></colgroup>
				<tr>
					<td><label class="contact">Username:</label></td>
					<td><input name="username" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
					<td><label class="contact">First Name:</label></td>
					<td><input name="firstName" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
					<td><label class="contact">Last Name:</label></td>
					<td><input name="lastName" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
					<td><label class="contact">Email:</label></td>
					<td><input name="email" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
					<td><label class="contact">Password:</label></td>
					<td> <input name="password" type="password" class="contact_input"></input></td>
				</tr>
				<tr>	
					<td><label class="contact">Confirm:</label></td>
					<td> <input name="confirmPassword" type="password" class="contact_input"></input></td>
				</tr>
				<tr>	
					<td><label class="contact">Paypal Acct Num:</label></td>
					<td> <input name="paypal" type="text" class="contact_input"></input></td>
				</tr>
				<tr>	
					<td></td>
			</tr>
			<tr>
			<td colspan="2">
		<input type="submit" value="Sign Up" />
		</td>
	</tr>
		<br><br>
		
		
		
<%
		//get the list of errors (if any)
	    
		ArrayList<String> errors = Errors.getSignupErrors();
		
		//if there are errors, display them
		if(errors != null && 
		   errors.isEmpty() == false)
		{
			for(String error : errors)
			{
%>
				<tr>
				<td colspan ="2"><label style="color:red"><%=error %></label></td>
				</tr>
<% 
			}
		}
%>
		</p>
<%
	}
	
	//otherwise, display username
	else if(isLoggedIn == true)
	{
		String un = currentUser.getUsername();
		String fn = currentUser.getFullName();
%>	
		<p style="color:green">
		Currently logged in as:<br> 
		<b><%=fn %></b><br>
		Username:
		<b><%=un %></b>	
</p>
<br>
<br>
<form method="post" action="logoutservlet">
	<input type="submit" value="Logout"/>
</form>
	
<%
	}
%>
	</table>
		</form>
		</div>
	<jsp:include page="template_bottom.jsp" />
	
</body>
</html>