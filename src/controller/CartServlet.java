package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.LoginData;
import application.SessionData;
import application.ShoppingCart;
import model.Order;
import model.OrderDAO;
import model.SystemAction;
import model.Vehicle;
import model.VehicleDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartAction = request.getParameter("cartAction");
		
		String targetURL = "/cart.jsp";
		
		if(cartAction == null)
		{
			cartAction = new String();
		}
		
		if(cartAction.equals("add"))
		{
			int id = Integer.parseInt(request.getParameter("vID"));
			Vehicle product = VehicleDAO.getVehicleById(id);
			ShoppingCart.add(product);
		}
		else if(cartAction.equals("checkout"))
		{
			if(ShoppingCart.getVehicles() == null)
			{
				ShoppingCart.setVehicles(new ArrayList<Vehicle>());
			}
			
			for(Vehicle v : ShoppingCart.getVehicles())
			{
				Order o = new Order();
				o.setBuyerId(LoginData.getCurrentUser().getUserId());
				o.setSellerId(v.getSellerID());
				o.setOpen(true);
				o.setVehicle(v);
				
				OrderDAO.enterNewOrder(o.getBuyerId(), o.getSellerId(), o.getVehicle().getId());
			}
			ShoppingCart.setVehicles(new ArrayList<Vehicle>());
			SessionData.setSuccessAction(SystemAction.Purchase);
			targetURL="/success.jsp";
		}
		else
		{
			
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(targetURL);
		if(dispatcher == null) System.out.println("WTF");
		dispatcher.forward(request, response);
	}

}
