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
<jsp:include page="template_top.jsp" ><jsp:param value="Search Results" name="pageName"/></jsp:include>

<%
	if(false == LoginData.isLoggedIn())
	{
%>
		<h2 style="color:red">You must be logged in to see orders</h2><br>
		<a href="login.jsp">Click Here To Log In</a><br>
		<a href="signup.jsp">Click Here To Sign Up</a><br>
<% 
	}
	else
	{

%>

	<%
		ArrayList<Order> results = OrderDAO.getOrdersByUser(LoginData.getCurrentUser().getUserId(), LoginData.getCurrentUser().getAccountType());
		if(results == null || results.isEmpty())
		{
	%>
			<p>No Orders</p>	
	<%
		}
		else
		{
			
	%>
	<%=results.size() %>
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
					<%
						if(LoginData.getCurrentUser().getAccountType()==AccountType.Seller)
							{
					%>
								Buyer
					<%
							}
						else
						{
					%>
								Seller
					<%
						}
						%>
				</th>
			
			</tr>
	<% 
			for(Order v : results)
			{
	%>
			<tr>
				<td>
					<%=v.getVehicle().getMake() %>
				</td>
				<td>
					<%=v.getVehicle().getModel() %>
				</td>
				<td>
					<%=v.getVehicle().getYear() %>
				</td>
				<td>
					<%=v.getVehicle().getPrice() %>
				</td>
				<td>
					<%
						if(LoginData.getCurrentUser().getAccountType()==AccountType.Seller)
							{
					%>
								<%=v.getBuyer().getUsername() %>
					<%
							}
						else
						{
					%>
								<%=v.getSeller().getUsername() %>
					<%
						}
						%>
				</td>
				<td>
					<form action="deleteservlet" method="post">
						<input type="hidden" name="targetType" value="order" />
						<input type="hidden" name="id" value=" <%=v.getId()%> "/>
						<input type="hidden" name="sender" value="/orders.jsp"/>
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