<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
 <%@ page import="model.*" %>
 <%@ page import="controller.*" %>
 <%@ page import="application.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="template_top.jsp" ><jsp:param value="Log In" name="pageName"/></jsp:include>

<%

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
		
      	
        <div class="contact_form">
			<h3>Log In:</h3>
			<form method="post" action="loginservlet">
				<table>
				<colgroup span="2" ></colgroup>
					<tr>
						<td><label class="contact">Username:</label></td>
						<td><input name="username" type="text" class="contact_input"></input></td>
					</tr>
					<tr>
						<td><label class="contact">Password:</label></td>
						<td> <input name="password" type="password" class="contact_input"></input></td>
					</tr>
					<tr>
						<td colspan ="2"><input type="submit" value="Log In" /></td>
					</tr>
				
			
		
		
		
<%
		//get the list of errors (if any)
	    
		ArrayList<String> errors = Errors.getLoginErrors();
		
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
<form class="contact_form" method="post" action="logoutservlet">
	<input type="submit" value="Logout"/>
</form>
	
<%
	}
%>
</table>
			
			</form>
		</div>
<br><br>


<jsp:include page="template_bottom.jsp"/>
</body>
</html>