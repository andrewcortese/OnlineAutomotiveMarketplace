<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
 <%@ page import="model.*" %>
 <%@ page import="controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String loggedIn = new String();
	String username = new String();
	
	User user = new User();
	user = (User)session.getAttribute("user");
	
	loggedIn = (String)session.getAttribute("loggedIn");
	username = (String)session.getAttribute("name");
	
	

	
	//if the loggedIn string is null or empty, then we're not logged in
	if(loggedIn == null || 
	   loggedIn.isEmpty())
	{
		loggedIn = new String();
		loggedIn = "false";
	}
	System.out.println(loggedIn);
	
	//we don't want user to be a null pointer
		if(user == null || user.isEmpty())
		{
			user = new User();
			loggedIn = "false";
		}
	
	//we don't want username to be a null pointer
	if(username == null || username.isEmpty())
	{
		username = new String();
		loggedIn = "false";
	}
	
	
	//if we're not logged in, display the form.
	if(loggedIn.equals("false"))
	{
%>	
		<h3>Log In:</h3>
		<form method="post" action="loginservlet">
			<table>
			<colgroup span="2" ></colgroup>
				<tr>
					<td>Username:</td>
					<td><input name="username" type="text"></input></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td> <input name="password" type="password"></input></td>
				</tr>
			</table>
		<input type="submit" value="Log In" />
		</form>
		
		
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
	else
	{
%>
	<p>Currently logged in as <%= user.getUsername() %> </p>
	<p> Welcome, <%= user.getFirstName() + " " + user.getLastName() + "!" %>	
	<br>
	<form method="post" action="logoutservlet">
	<input type="submit">Logout</input>
	</form>
	
<%
	}
%>

<br><br>
<a href="index.jsp">Return to Homepage</a>
</body>
</html>