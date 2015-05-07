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
<jsp:include page="template_top.jsp" ><jsp:param value="User Details" name="pageName"/></jsp:include>


<%
	User selectedUser = new User();
	boolean selected = false;

	selectedUser = SessionData.getSelectedUser();
	if(selectedUser == null)
	{
		selected = false;
	}
	else
	{
		selected = true;
	}
	
	
	//if we're not logged in, display the form.
	if(selected == false)
	{
%>	
		<p>User Not Found</p>
		<a href="searchusers.jsp">Click To Search Users</a>	
<%
	}
	else
	{
%>
<h2 align="center">User Details For: <%=selectedUser.getFullName() %></h2>
	<table align="center" style="fontsize:18">
	<colgroup span="2">
	<tr>
		<th>
			Name
		</th>
		<td>
			<%=selectedUser.getFullName() %>
		</td>
	</tr>
	<tr>
		<th>
			Username
		</th>
		<td>
			<%=selectedUser.getUsername() %>
		</td>
	</tr>
	<tr>
		<th>
			Email
		</th>
		<td>
			<%=selectedUser.getEmail() %>
		</td>
	</tr>
	<tr>
		<th>
			Account Type
		</th>
		<td>
			<%=selectedUser.getAccountType().toString() %>
		</td>
	</tr>
	<tr>
		<th>
			Contact
		</th>
		<td>
			<%
				String s = "mailto:" + selectedUser.getEmail();
			%>
			<a href=" <%=s %> ">Click Here</a>
		</td>
	</tr>
	
	</table>
<%
	}
%>


	<jsp:include page="template_bottom.jsp" />
	
</body>
</html>