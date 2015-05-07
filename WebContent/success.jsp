<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="model.*" %>
     <%@ page import="application.*" %>
     
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> --->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="template_top.jsp" ><jsp:param value="Success!" name="pageName"/></jsp:include>
	
		<div id="index-content">
		
		<%
			String message = new String();
			SystemAction a = SessionData.getSuccessAction();
			SessionData.setSuccessAction(SystemAction.None);
			if(a == SystemAction.SignUp) 
			{
				message = "You have successfully registered for an account!";
		%>
				<h2 style="color:green"><%=message %></h2>
				<a href="login.jsp">Click Here To Log In</a>
		<%
			} 
			else if(a == SystemAction.Purchase)
			{
				message = "You have successfully placed your order";
		%>
				<h2 style="color:green"><%=message %></h2>
				<a href="orders.jsp">Click Here To View Your Orders</a>
		<%
			}
			else if(a == SystemAction.Logout)
			{
				message = "You have successfully logged out";
		%>
				<h2 style="color:green"><%=message %></h2>
				<a href="login.jsp">Click Here To Log In</a>
		<%
			}
			else if(a == SystemAction.None)
			{
				message = "Oops! Why are you here?";
		%>
				<h2 style="color:green"><%=message %></h2>
				<a href="index.jsp">Click Here To Return Home</a>
		<%
			}
			else if(a == SystemAction.AddVehicle)
			{
				message = "Vehicle Added!";
		%>
				<h2 style="color:green"><%=message %></h2>
				<a href="profile.jsp">Click Here To View Profile</a>
		<%
			}
			else
			{
				message = "Success";
		%>
				<h2 style="color:green"><%=message %></h2>
				<a href="index.jsp">Click Here To Return Home</a>
		<%
			}
		%>
		
		</div>
		<jsp:include page="template_bottom.jsp" />
</body>
</html>