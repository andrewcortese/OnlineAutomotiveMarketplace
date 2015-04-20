package application;
import java.util.*;
public class Errors {

	private static ArrayList<String> loginErrors;
	private static ArrayList<String> signupErrors;
	private static ArrayList<String> searchErrors;
	
	
	/**
	 * @return the loginErrors
	 */
	public static ArrayList<String> getLoginErrors() {
		return loginErrors;
	}
	/**
	 * @param loginErrors the loginErrors to set
	 */
	public static void setLoginErrors(ArrayList<String> loginErrors) {
		Errors.loginErrors = loginErrors;
	}
	/**
	 * @return the signupErrors
	 */
	public static ArrayList<String> getSignupErrors() {
		return signupErrors;
	}
	/**
	 * @param signupErrors the signupErrors to set
	 */
	public static void setSignupErrors(ArrayList<String> signupErrors) {
		Errors.signupErrors = signupErrors;
	}
	/**
	 * @return the searchErrors
	 */
	public static ArrayList<String> getSearchErrors() {
		return searchErrors;
	}
	/**
	 * @param searchErrors the searchErrors to set
	 */
	public static void setSearchErrors(ArrayList<String> searchErrors) {
		Errors.searchErrors = searchErrors;
	}
	
	
}
