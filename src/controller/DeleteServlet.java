package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthDAO;
import model.VehicleDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet({ "/DeleteServlet" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		String targetType = request.getParameter("targetType");
		String idStr = request.getParameter("id");
		String senderURL = request.getParameter("sender");
		
		if(senderURL == null || senderURL.isEmpty())
		{
			senderURL = "/index.jsp";
		}
		if(targetType != null && idStr != null && !idStr.isEmpty())
		{
			int id = Integer.parseInt(idStr);
			
			if(targetType.equals("user"))
			{
				AuthDAO.deleteUser(id);
			}
			else if(targetType.equals("vehicle"))
			{
				VehicleDAO.deleteVehicle(id);
			}
			
			
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(senderURL);
		if(dispatcher == null) System.out.println("WTF");
		dispatcher.forward(request, response);
	}

}
