<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Welcome</h2><br><br>
<%
	
	String name = (String)session.getAttribute("name");
	String loggedIn = (String)session.getAttribute("loggedIn");
	User user = (User)session.getAttribute("user");
	
	if(loggedIn == null || loggedIn.isEmpty())
	{
		loggedIn = "false";
	}
	if(user == null || user.isEmpty())
	{
		loggedIn = "false";
		user = new User();
	}
	if(name == null || name.isEmpty())
	{
		name = "NAME NOT FOUND";
	}
	
	
	System.out.println(loggedIn);
	//if loggedIn==true, display the name
	if(loggedIn.equals("true"))
	{
%>
		<p style="color:green">
		Currently logged in as:<br> 
			<b><%=user.getFirstName() + " " + user.getLastName() %></b><br>
		Username:
			<b><%=user.getUsername() %></b>
		
		</p><br><br>
		<form method="post" action="logoutservlet">
		<input type="submit" value="Logout"/>
		</form>
<% 	
	}
	else
	{
		String signedUp = (String)session.getAttribute("signedUp");
		if(signedUp == null)
		{
			signedUp = new String();
		}
		if(signedUp.equals("true"))
		{
%>
		<p style="color:green">Signup Success!!!</p><br>
		<p>Currently <u><b>NOT</b></u> logged-in</p><br>
		<a href="login.jsp">Click Here To Log In</a><br>
		
<% 
		}
		else
		{
			
		
%>
		<p>Currently <u><b>NOT</b></u> logged-in</p><br>
		<a href="login.jsp">Click Here To Log In</a><br>
		<a href="signup.jsp">Click Here to Sign Up</a><br>
<%
		}
		
		signedUp = "false";
	}
%>

</body>
</html>