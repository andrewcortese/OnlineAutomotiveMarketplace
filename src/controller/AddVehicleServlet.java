package controller;

import static utilities.ArrayListUtility.rightToLeftCombine;
import static utilities.StringUtility.exists;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SystemAction;
import model.Vehicle;
import model.VehicleDAO;
import application.LoginData;
import application.SessionData;

/**
 * Servlet implementation class AddVehicleServlet
 */
@WebServlet("/AddVehicleServlet")
public class AddVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String style = request.getParameter("style");
		String year = request.getParameter("year");
		String price = request.getParameter("price");
		String mileage = request.getParameter("mileage");
		int yearInt;
		double priceDec;
		int miles;
		
		if(!exists(make))
		{
			make = new String();
			
		}
		
		if(!exists(model))
		{
			model = new String();
		}
		
		if(!exists(style))
		{
			style = new String();
		}
		
		if(!exists(year))
		{
			year = new String();
			yearInt = 0;
		}
		else
		{
			yearInt = Integer.parseInt(year);
		}
		
		if(!exists(mileage))
		{
			mileage = new String();
			miles = 0;
		}
		else
		{
			miles = Integer.parseInt(mileage);
		}
		
		if(!exists(price))
		{
			price = new String();
			priceDec = 0.0;
		}
		else
		{
			priceDec = Double.parseDouble(price);
		}
	
		//SessionData.setAttribute("searchResults", vehicles);
		
		VehicleDAO.enterNewVehicle(make, model, style, yearInt, priceDec, LoginData.getCurrentUser().getUserId(), miles);
		
		try
		{
			VehicleDAO.DB_Close();
		}
		catch(Throwable ex)
		{
			System.out.println(ex.getMessage());
		}
		
		SessionData.setSuccessAction(SystemAction.AddVehicle);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
		dispatcher.forward(request, response);
	}

}
