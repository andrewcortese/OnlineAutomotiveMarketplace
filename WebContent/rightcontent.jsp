
 <%@ page import="application.*" %>
 <%@ page import="model.*" %>

<div class="right_content"  style="background-color:white">
      <div class="title_box">Quick Search</div>
      <div class="border_box">
        <input type="text" name="newsletter" class="newsletter_input" value="keyword"/>
        <a href="#" class="join">search</a> </div>
      <div class="shopping_cart">
        <div class="title_box">Shopping cart</div>
        <div class="cart_details"> <%=ShoppingCart.count() %> items <br />
          <span class="border_cart"></span> Total: <span class="price">$<%=ShoppingCart.calculateTotal() %></span> </div>
        <div class="cart_icon"><a href="cart.jsp"><img src="images/shoppingcart.png" alt="" width="35" height="35" border="0" /></a></div>
      </div>
      <div class="title_box">News</div>
      <div class="border_box">
        <div class="product_title">Site Launched!</div>
        We are pleased to announce the grand opening of the site in May 2015
      </div>
      <div class="title_box">Browse Styles</div>
      <ul class="left_menu">
        <li class="odd"><a href="#">Sedan</a></li>
        <li class="even"><a href="#">SUV</a></li>
        <li class="odd"><a href="#">Truck</a></li>
        <li class="even"><a href="#">Convertible</a></li>
        <li class="odd"><a href="#">Minivan</a></li>
        <li class="even"><a href="#">Coup</a></li>
        <li class="odd"><a href="#">Convertible</a></li>
      </ul>
     
    </div>
    <!-- end of right content -->