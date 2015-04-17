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

	<jsp:include page="template_top.jsp" />
	
		<div id="index-content">

		<h2>Welcome</h2><br/><br/>
		<%
			session = SessionData.getOrStartSession(session);
			boolean loggedIn = false;
			User user = new User();
			
			if(LoginData.isLoggedIn())
			{
				loggedIn = true;
				 user = LoginData.getCurrentUser();
			}
			
		
			//if loggedIn==true, display the name
			if(loggedIn  == true)
			{
		%>
				<jsp:include page="greetLoggedIn.jsp">
					<jsp:param value="${user.getFullName() }" name="fullname"/>
					<jsp:param value="${user.getUsername() }" name="username"/>
				</jsp:include>
		<% 	
			}
			else
			{
				String signedUp = (String)session.getAttribute("signedUp");
				if(signedUp == null)
				{
					signedUp = new String();
				}
				if(signedUp.equals("true"))
				{
		%>
				<p style="color:green">Signup Success!!!</p><br>
				<p>Currently <u><b>NOT</b></u> logged-in</p><br>
				<a href="login.jsp">Click Here To Log In</a><br>
				
		<% 
				}
				else
				{
					
				
		%>
				<p>Currently <u><b>NOT</b></u> logged-in</p><br>
				<a href="login.jsp">Click Here To Log In</a><br>
				<a href="signup.jsp">Click Here to Sign Up</a><br>
		<%
				}
				
				signedUp = "false";
			}
		%>
		</div>
		<jsp:include page="template_bottom.jsp" />
</body>
</html>