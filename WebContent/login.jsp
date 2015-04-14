<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
 <%@ page import="model.*" %>
 <%@ page import="controller.*" %>
 <%@ page import="application.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	session = SessionData.getOrStartSession(session);
	boolean loggedIn = false;
	User currentUser = new User();
	if(LoginData.isLoggedIn())
	{
		loggedIn = true;
		currentUser = LoginData.getCurrentUser();
	}
	
	//if we're not logged in, display the form.
	if(loggedIn == false)
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

<br><br>
<a href="index.jsp">Return to Homepage</a>
</body>
</html>