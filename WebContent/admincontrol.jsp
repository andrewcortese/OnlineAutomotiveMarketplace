<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
 <%@ page import="model.*" %>
 <%@ page import="controller.*" %>
 <%@ page import="application.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="template_top.jsp" ><jsp:param value="Admin Control Panel" name="pageName"/></jsp:include>

	<%
		if(LoginData.isLoggedIn() == false || LoginData.getCurrentUser().getAccountType() != AccountType.Admin)
		{
	%>
			<h1 style="color:red">You Are Not Authorized To View This Page</h1>
	<%
		}
		else
		{
	%>
	<%
		ArrayList<Vehicle> results = VehicleDAO.getAll();
		if(results == null || results.isEmpty())
		{
	%>
			<p>No Results</p>	
	<%
		}
		else
		{
			//SessionData.removeAttribute("searchResults");
	%>
			<table>
			<colgroup span="4">
			<tr>
				<th>
					Make
				</th>
				<th>
					Model
				</th>
				<th>
					Year
				</th>
				<th>
					Price
				</th>
				<th>
					Mileage
				</th>
				<th>
				    ID
				</th>
				<th>
					Delete
				</th>
			</tr>
	<% 
			for(Vehicle v : results)
			{
	%>
			<tr>
				<td>
					<%=v.getMake() %>
				</td>
				<td>
					<%=v.getModel() %>
				</td>
				<td>
					<%=v.getYear() %>
				</td>
				<td>
					<%=v.getPrice() %>
				</td>
				<td>
					<%=v.getMileage() %>
				</td>
				<td>
				    <%=v.getId() %>
				</td>
				<td>
					<form action="deleteservlet" method="post">
						<input type="hidden" name="targetType" value="vehicle" />
						<input type="hidden" name="id" value=" <%=v.getId()%> "/>
						<input type="hidden" name="sender" value="/admincontrol.jsp"/>
						<input type="submit" name="name" value="Delete" />
					</form> 
				</td>
			</tr>			
					
	<% 
			}
	%>
			</table>
	<% 
		}
	%>			
	
	
<p></p>
<p></p>

		<%
		ArrayList<User> userResults = AuthDAO.getAll();
		if(userResults == null || userResults.isEmpty())
		{
	%>
			<p>No Users</p>	
	<%
		}
		else
		{
			//SessionData.removeAttribute("searchResults");
	%>
			<table>
			<colgroup span="4">
			<tr>
				<th>
					Name
				</th>
				<th>
					Type
				</th>
				<th>
					Username
				</th>
				<th>
					Password
				</th>
				<th>
				    ID
				</th>
				<th>
					Delete
			 	</th>
			</tr>
	<% 
			for(User u: userResults)
			{
	%>
			<tr>
				<td>
					<%=u.getFullName() %>
				</td>
				<td>
					<%=u.getAccountType() %>
				</td>
				<td>
					<%=u.getUsername() %>
				</td>
				<td>
					<%=u.getPassword() %>
				</td>
				<td>
					<%=u.getUserId() %>
				</td>
				<td>
					<form action="deleteservlet" method="post">
						<input type="hidden" name="targetType" value="user" />
						<input type="hidden" name="id" value=" <%=u.getUserId()%> "/>
						<input type="hidden" name="sender" value="/admincontrol.jsp"/>
						<input type="submit" name="name" value="Delete" />
					</form> 
				</td>
			</tr>			
					
	<% 
			}
	%>
			</table>
	<% 
		}
		}
	%>			
	
			
<jsp:include page="template_bottom.jsp" />
</body>
</html>