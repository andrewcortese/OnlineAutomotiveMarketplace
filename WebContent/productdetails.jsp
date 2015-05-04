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
<jsp:include page="template_top.jsp" ><jsp:param value="Product Details" name="pageName"/></jsp:include>

	<%
		SessionData.setSelectedProduct(DummyData.vehicle());
		Vehicle product = SessionData.getSelectedProduct();
		if(product == null)
		{
	%>
			<p>No Product Selected</p>	
	<%
		}
		else
		{
			SessionData.setSelectedProduct(null);
	%>		
			<h2>
			<%=product.getYear() %> 
			<%=product.getMake() %> 
			<%=product.getModel() %> 
			</h2>
			
			<br><br>
			<table id="productDetails" align="center">
				<tr>
					<td>
						Price: 
					</td>
					<td>
						<%=product.getPrice() %>
					</td>
				</tr>
				<tr class="alt">
					<td>
						Style: 
					</td>
					<td>
						<%=product.getStyle().toString() %>
					</td>
				</tr>
				<tr>
					<td>
						Mileage:
					</td>
					<td>
						<%=product.getMileage() %>
					</td>
				</tr>
				<tr class="alt">
					<td>
						Seller:
					</td>
					<td>
						<%=product.getSellerID() %>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<%=product.getDescription() %>
					</td>
				</tr>
				<tr class="alt">
					<td colspan="2">
						<img src="${product.getPhotoURL()}"></img>
					</td>
				</tr>
			</table>
				
	<%
		}
	%>
			
			

<jsp:include page="template_bottom.jsp" />
</body>
</html>