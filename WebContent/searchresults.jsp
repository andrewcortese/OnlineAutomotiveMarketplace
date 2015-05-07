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
		ArrayList<Vehicle> results = (ArrayList<Vehicle>)SessionData.getAttribute("searchResults");
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
					Seller
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
					<%=v.getSeller().getUsername() %>
				</td>
				<td>
					<form action="cartservlet" method="post">
					<input type="hidden" name="cartAction" value="add"/>
					<input type="hidden" name="vID" value="<%=v.getId() %>"/>
					<input type="submit" value="Add To Cart" />
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
			

<jsp:include page="template_bottom.jsp" />
</body>
</html>