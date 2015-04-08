package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AuthDAO;

import java.util.*;
import model.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 
     */
    public LoginServlet() {
        
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
		
		String loggedIn = "false";
		
		ArrayList<String> errors = new ArrayList<String>();
		
		session.setAttribute("errors", errors);
		User user = new User();
		


		//get the form values
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
	
		
		//null check
		if(username==null)
		{
			username = new String();
			
		}
		if(password==null)
		{
			password = new String();
		}
		
		//if name is empty, send an error
		if(username.length() == 0)
		{
			String error = "Please enter your username";
			errors.add(error);
	
		}
		
		//otherwise, check password for emptiness
		else if(password.length() == 0)
		{
			String error = "Please enter your password";
			errors.add(error);
		}
		
		
		//otherwise, iff they match the expected values, login.
		else if(AuthDAO.checkUserPass(username, password))
		{
			int id = AuthDAO.getUserId(username);
			user = AuthDAO.getUserById(id);
			loggedIn = "true";
			targetURL = "/index.jsp";
			errors.clear();
			session.setAttribute("user", user);
			session.setAttribute("loggedIn", "true");
		}
		else
		{
			
			loggedIn = "false";
			errors.add("Invalid credentials");
			session.setAttribute("loggedIn", "false");
			
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
		
		session.invalidate();
		
	}

}
