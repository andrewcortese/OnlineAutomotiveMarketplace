package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.SessionData;
import model.AuthDAO;
import model.Vehicle;
import model.VehicleDAO;
import model.VehicleStyle;
import static utilities.StringUtility.exists;
import static utilities.ArrayListUtility.intersection;
import static utilities.ArrayListUtility.rightToLeftCombine;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		ArrayList<Vehicle> queryResults = new ArrayList<Vehicle>();
		System.out.printf("make: %s model: %s style: %s year: %s", make, model, style, year);
		if(exists(make))
		{
			queryResults = VehicleDAO.getByMake(make);
			vehicles = rightToLeftCombine(vehicles, queryResults);
			
		}
		
		if(exists(model))
		{
			queryResults = VehicleDAO.getByModel(model);
			vehicles = rightToLeftCombine(vehicles, queryResults);
		}
		
		if(exists(style) && !style.equals("any"))
		{
			queryResults = VehicleDAO.getByModel(style);
			vehicles = rightToLeftCombine(vehicles, queryResults);
		}
		
		if(exists(year) && !year.equals("any"))
		{
			int intYear = Integer.parseInt(year);
			queryResults = VehicleDAO.getByYear(intYear);
			vehicles = rightToLeftCombine(vehicles, queryResults);
		}
		
		if(exists(price))
		{
			double decPrice = Double.parseDouble(price);
			queryResults = VehicleDAO.getByPrice(decPrice);
			vehicles = rightToLeftCombine(vehicles, queryResults);
		}
	
		SessionData.setAttribute("searchResults", vehicles);
		
		
		
		try
		{
			VehicleDAO.DB_Close();
		}
		catch(Throwable ex)
		{
			System.out.println(ex.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchresults.jsp");
		dispatcher.forward(request, response);
		
	}


	

}
