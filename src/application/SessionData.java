package application;

import javax.servlet.http.HttpSession;

public class SessionData {

	private static HttpSession currentSession;
	private static boolean active;
	
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
