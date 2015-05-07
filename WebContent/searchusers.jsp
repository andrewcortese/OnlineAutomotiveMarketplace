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
		<form method="post" action="searchusersservlet">
			<table>
			<colgroup span="2" ></colgroup>
				<tr>
					<td><label class="contact">Username:</label></td>
					<td><input name="username" type="text" class="contact_input"></input></td>
				</tr>
				<tr>
			<td colspan="2">
		<input type="submit" value="Search" />
		</td>
	</tr>
		
		
		


</table>
</form>
</div>
	<jsp:include page="template_bottom.jsp" />
	
</body>
</html>