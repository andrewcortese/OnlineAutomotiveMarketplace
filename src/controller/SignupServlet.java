package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AuthDAO;
import model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		String targetURL = "/login.jsp";
		HttpSession session = request.getSession();
		
		
		
		ArrayList<String> errors = new ArrayList<String>();
		
		session.setAttribute("errors", errors);
		//User user = new User();
		

		ArrayList<String> fields = new ArrayList<String>();
		
		//get the form values
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String confirmPassword = request.getParameter("confirmPassword");
		
	
		
		//null check
		if(username==null)
		{
			username = new String();
			
		}
		if(password==null)
		{
			password = new String();
		}
		if(confirmPassword == null)
		{
			confirmPassword = new String();
		}
		if(firstName == null)
		{
			firstName = new String();
		}
		if(lastName == null)
		{
			lastName = new String();
		}
		
		boolean allFilled = true;
		//if name is empty, send an error
		if(username.length() == 0)
		{
			String error = "Please enter your username";
			errors.add(error);
			allFilled = false;
	
		}
		
		//otherwise, check password for emptiness
		if(password.length() == 0)
		{
			String error = "Please enter your password";
			errors.add(error);
			allFilled = false;
		}
		
		if(confirmPassword.length() == 0)
		{
			String error = "Please confirm your password";
			errors.add(error);
			allFilled = false;
		}
		
		if(firstName.length() == 0)
		{
			String error = "Please enter your first name";
			errors.add(error);
			allFilled = false;
		}
		
		if(lastName.length() == 0)
		{
			String error = "Please enter your last name";
			errors.add(error);
			allFilled = false;
		}
		
		
		//otherwise, iff they match the expected values, login.
		if(allFilled == true)
		{
			if(password.equals(confirmPassword))
			{
				AuthDAO.enterNewUser(username, password);
				int id = AuthDAO.getUserId(username);
				AuthDAO.enterUserName(id, firstName, lastName);
				targetURL = "/index.jsp";
				errors.clear();
				//session.setAttribute("user", user);
				session.setAttribute("signedUp", "true");
			}
			else
			{
				errors.add("Passwords do not match");
				session.setAttribute("signedUp", "false");
				targetURL = "/signup.jsp";
			}
		}
		else
		{
			session.setAttribute("signedUp", "false");
			targetURL = "/signup.jsp";
		}
	
		
		
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(targetURL);
		dispatcher.forward(request, response);
		
		try
		{
			AuthDAO.DB_Close();
		}
		catch(Throwable ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
	}

}
