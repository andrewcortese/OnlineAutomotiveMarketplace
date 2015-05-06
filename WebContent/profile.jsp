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
		<p>You Are Not Logged In</p>
		<a href="login.jsp">Click Here To Log In</a>	
<%
	}
	else
	{
%>
	<table align="center" style="fontsize:18">
	<colgroup span="2">
	<tr>
		<th>
			Name
		</th>
		<td>
			<%=currentUser.getFullName() %>
		</td>
	</tr>
	<tr>
		<th>
			Username
		</th>
		<td>
			<%=currentUser.getUsername() %>
		</td>
	</tr>
	<tr>
		<th>
			Email
		</th>
		<td>
			<%=currentUser.getEmail() %>
		</td>
	</tr>
	<tr>
		<th>
			Paypal Account Number
		</th>
		<td>
			<%=currentUser.getPaypal() %>
		</td>
	</tr>
	<tr>
		<th>
			Account Type
		</th>
		<td>
			<%=currentUser.getAccountType().toString() %>
		</td>
	</tr>
	
	</table>
<%
	}
%>


	<jsp:include page="template_bottom.jsp" />
	
</body>
</html>