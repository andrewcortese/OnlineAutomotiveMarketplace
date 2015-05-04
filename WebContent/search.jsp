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
<jsp:include page="template_top.jsp" ><jsp:param value="Search" name="pageName"/></jsp:include>
<h3>Search:</h3>
		<div class="contact_form">
		<form method="post" action="searchservlet">
			<table>
			<colgroup span="2" ></colgroup>
				<tr>
					<td><label class="contact">Make:</label></td>
					<td><input name="make" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
					<td><label class="contact">Model:</label></td>
					<td><input name="model" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
					<td><label class="contact">Style:</label></td>
					<td>
					<select style="width:100%" class="contact_input" name="style">
							<option value="any">Any Style</option>
							<option value="Sedan">Sedan</option>
							<option value="Coup">Coup</option>
							<option value="SUV"> SUV</option>
							<option value="Truck">Truck</option>
							<option value="Convertible">Convertible</option>
							<option value="Minivan">Minivan</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label class="contact">Year</label></td>
					<td> 
						<select name="year" class="contact_input" style="width:100%">
									<option value="any">Any Year</option>
							<%
							for(int i=1900; i<2020; i++)
							{
								%>
									<option value="${i}"><%=i%></option>
							<%
							}
							%>
							
						</select>
				
					</td>
				</tr>
				<tr>	
					<td><label class="contact">Mileage</label></td>
					<td> <input name="confirmPassword" type="password" class="contact_input"></input></td>
				</tr>
				<tr>	
					<td><label class="contact">Price</label></td>
					<td> <input name="confirmPassword" type="password" class="contact_input"></input></td>
				</tr>
				<tr>	
					<td><label class="contact">Seller</label></td>
					<td> <input name="confirmPassword" type="password" class="contact_input"></input></td>
				</tr>
				<tr>	
					<td></td>
			</tr>
			<tr>
			<td colspan="2">
		<input type="submit" value="Search" />
		</td>
	</tr>
		
		
		
		
<%
		//get the list of errors (if any)
	    
		ArrayList<String> errors = Errors.getSignupErrors();
		
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


</table>
</form>
</div>
	<jsp:include page="template_bottom.jsp" />
	
</body>
</html>