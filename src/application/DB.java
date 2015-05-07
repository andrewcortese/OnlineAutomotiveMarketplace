package application;
import model.*;
public class DB {

	public static void closeAll()
	{
		try
		{
			AuthDAO.DB_Close();
			VehicleDAO.DB_Close();
			OrderDAO.DB_Close();
		}
		catch(Throwable e)
		{
			
		}
	}
}
