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
<jsp:include page="template_top.jsp" ><jsp:param value="Profile" name="pageName"/></jsp:include>

<h2 align="center">My Account</h2>
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
	
	if(currentUser.getAccountType()==AccountType.Seller)
	{
		ArrayList<Vehicle> results = VehicleDAO.getBySeller(currentUser.getUserId());
		%>
		<br><br><br>
		<h2 align="center">My Vehicles</h2>
		<table align="center">
			<colgroup span="5">
			<tr>
				<th>
					Make
				</th>
				<th>
					Model
				</th>
				<th>
					Style
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
					<%=v.getStyle().toString() %>
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
					<form action="deleteservlet" method="post">
						<input type="hidden" name="targetType" value="vehicle" />
						<input type="hidden" name="id" value=" <%=v.getId()%> "/>
						<input type="hidden" name="sender" value="/profile.jsp"/>
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