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
<jsp:include page="template_top.jsp" ><jsp:param value="Shopping Cart" name="pageName"/></jsp:include>

	<%
		ArrayList<Vehicle> cart = ShoppingCart.getVehicles();
		if(cart == null || cart.isEmpty())
		{
	%>
			<p>Cart is Empty</p>	
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
					Price
				</th>
			</tr>
	<% 
			for(Vehicle v : cart)
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
					<%=v.getPrice() %>
				</td>
			</tr>			
					
	<% 
			}
	%>
			</table>
			<p>Total: <%=ShoppingCart.calculateTotal() %> </p>
			<form action="cartservlet" method="post">
			<input type="hidden" name="cartAction" value="checkout" />
			<input type="submit" value="Checkout"/>
			</form>
	<% 
		}
	%>			
			

<jsp:include page="template_bottom.jsp" />
</body>
</html>