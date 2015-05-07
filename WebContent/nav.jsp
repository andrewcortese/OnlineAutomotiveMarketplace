 <%@ page import="application.*" %>
 <%@ page import="model.*" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<div id="menu_tab">
      <ul class="menu">
        <li><a href="index.jsp" class="nav"> Home </a></li>
        <li class="divider"></li>
        <li><a href="search.jsp" class="nav">Search Products</a></li>
        <li class="divider"></li>
        <li><a href="searchusers.jsp" class="nav">Search Users</a></li>
        <li class="divider"></li>
        <li><a href="login.jsp" class="nav">Log In</a></li>
        <li class="divider"></li>
        <li><a href="signup.jsp" class="nav">Sign Up</a></li>
        <li class="divider"></li>
        <li><a href="orders.jsp" class="nav">My Orders</a></li>
        <li class="divider"></li>
        <li><a href="profile.jsp" class="nav">My Profile</a></li>
        <li class="divider"></li>
        <%if(LoginData.isLoggedIn() && LoginData.getCurrentUser().getAccountType().equals(AccountType.Admin)){
        %>
        <li><a href="admincontrol.jsp" class="nav">Control Panel</a></li>
        <li class="divider"></li>
        <%}%>
      </ul>
    </div>
<div class="crumb_navigation"> Navigation: <span class="current"></span> </div>