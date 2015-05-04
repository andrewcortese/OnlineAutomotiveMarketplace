package application;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import model.Vehicle;

public class SessionData {

	private static HttpSession currentSession;
	private static boolean active;
	
	private static Vehicle selectedProduct;
	
	
	
	/**
	 * @return the selectedProduct
	 */
	public static Vehicle getSelectedProduct() {
		return selectedProduct;
	}

	/**
	 * @param selectedProduct the selectedProduct to set
	 */
	public static void setSelectedProduct(Vehicle selectedProduct) {
		SessionData.selectedProduct = selectedProduct;
	}

	
	
	private static Map<String, Object> attributes = new HashMap<String, Object>();
	
	public static void setAttribute(String key, Object value)
	{
		SessionData.attributes.put(key, value);
	}
	
	public static Object getAttribute(String key)
	{
		return attributes.get(key);
	}
	
	public static void setStringAttribute(String key, String value)
	{
		SessionData.setAttribute(key, ((Object)value));
	}
	
	
	
	public static HttpSession start(HttpSession session)
	{
		currentSession = session;
		active = true;
		
		return currentSession;
	}
	
	public static void end()
	{
		if(isActive() && currentSession != null)
		{
			/*try
			{
				currentSession.invalidate();
			}
			catch(IllegalStateException e)
			{
				System.err.println("Attempted to invalidate an already invalidated session. Enforcing idempotence.");
			}
			currentSession = null;*/
			active = false;
		}
	}
	
	public static boolean isActive()
	{
		return active;
	}
	
	public static HttpSession getSession()
	{
		return currentSession;
	}
	
	/**
	 * If there is an active session, return it.
	 * Otherwise, start one from the parameter.
	 * @param newSession session to set as current if there isn't already one
	 * @return the current session
	 */
	public static HttpSession getOrStartSession(HttpSession newSession)
	{
		if(SessionData.isActive())
		{
			return SessionData.getSession();
		}
		else
		{
			return SessionData.start(newSession);
		}
	}
	
	
}
