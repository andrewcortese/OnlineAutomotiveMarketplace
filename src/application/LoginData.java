package application;
import model.*;
import controller.*;

public class LoginData {

	private static User currentUser;
	private static boolean loggedIn;
	
	/**
	 * method to "start" a session for the specified user.
	 * @param user
	 */
	public static void login(User user)
	{
		currentUser = user;
		loggedIn = true;
	}
	
	
	public static void logout()
	{
		currentUser = null;
		loggedIn = false;
	}
	
	/**
	 * @return the currentUser
	 */
	public static User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * @return the loggedIn
	 */
	public static boolean isLoggedIn() {
		return loggedIn;
	}
	
	

	
	
	
}
