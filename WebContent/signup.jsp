<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
 <%@ page import="application.*" %>
 <%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	User currentUser = new User();
	boolean isLoggedIn = false;

	if(LoginData.isLoggedIn())
	{
		currentUser = LoginData.getCurrentUser();
		isLoggedIn = true;
	}
	
	
	
	
	String signedUp = new String();
	String username = new String();
	
	signedUp = (String)session.getAttribute("signedUp");
	username = (String)session.getAttribute("name");
	
	

	
	//if the signedUp string is null or empty, then we're not logged in
	if(signedUp == null || 
	   signedUp.isEmpty())
	{
		signedUp = "false";
	}
	
	//we don't want username to be a null pointer
	if(username == null)
	{
		username = new String();
	}
	
	
	//if we're not logged in, display the form.
	if(isLoggedIn == false)
	{
%>	
		<h3>Sign Up:</h3>
		<form method="post" action="signupservlet">
			<table>
			<colgroup span="2" ></colgroup>
				<tr>
					<td>Username:</td>
					<td><input name="username" type="text"></input></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><input name="firstName" type="text"></input></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input name="lastName" type="text"></input></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td> <input name="password" type="password"></input></td>
				</tr>
				<tr>	
					<td>Confirm Password:</td>
					<td> <input name="confirmPassword" type="password"></input></td>
				</tr>
				<tr>	
					<td></td>
			</tr>
			</table>
		<input type="submit" value="Sign Up" />
		</form>
		<br><br>
		
		
		<p style="color:red">
<%
		//get the list of errors (if any)
	    
		ArrayList<String> errors = (ArrayList<String>)session.getAttribute("errors");
		
		//if there are errors, display them
		if(errors != null && 
		   errors.isEmpty() == false)
		{
			for(String error : errors)
			{
%>
				<%=error %><br>
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
</body>
</html>