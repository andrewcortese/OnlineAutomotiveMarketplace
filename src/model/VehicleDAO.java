package model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Driver;
import java.util.*;
//import config.Config;


/*
 * TODO:
 * (1) create a method to get a Vehicle by its id
 * (2) create a method to get all Vehicles matching a particular price
 * (3) create a method to get all Vehicles matching the given make
 * (4) create a method to get all Vehicles matching the given model
 * (5) create a method to get all Vehicles matching the given year
 * (6) create a method to get all Vehicles matching the given style
 * (7) create a method to get all Vehicles matching the given seller's userID
 * 
 * 
 * When you return "all vehicles matching..." return it as an ArrayList<Vehicle>
 */


public class VehicleDAO {

	private static Connection connection;
	public static final String DB_URL = "jdbc:mysql://localhost:3306/lab03";
	public static final String DB_USER = "root";
	public static final String DB_PW = "password";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * helper method to load the DB Driver
	 */
	private static void loadDriver()
	{
		try
		{
			
			Class.forName(DB_DRIVER);
		}
		catch(Exception ex)
		{
			handleException(ex);
		}
	}
	
	/**
	 * helper method to get the DB connection
	 * @return the Connection object
	 */
	private static Connection getConnection()
	{
		Connection c = null;
		try
		{
			c = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			System.out.println("Database Schema: " + c.getSchema());
			
		}
		catch(SQLException ex)
		{
			VehicleDAO.handleException(ex);
		}
		
		if(c==null)
		{
			System.out.println("NO CONNECTION");
		}
		
		return c;
	}
	
	/**
	 * helper method to get a prepared statement from the text of the query
	 * @param queryText the SQL query text
	 * @return a PreparedStatement object
	 */
	private static PreparedStatement getPreparedStatement(String queryText)
	{
		VehicleDAO.loadDriver();
		VehicleDAO.connection = VehicleDAO.getConnection();
		PreparedStatement query = null;
		try
		{
			query = connection.prepareStatement(queryText);
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		return query;
	}
	
	/**
	 * helper method to handle exceptions using the Logger
	 */
	private static void handleException(Exception ex)
	{
		Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	/**
	 * helper method to excecute a query
	 * @param query as a PreparedStatement
	 * @return the ResultSet
	 */
	private static ResultSet executeQuery(PreparedStatement query) {
		ResultSet result = null;
		try
		{
			result = query.executeQuery();
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		return result;
	}
	
	
	private static int executeUpdate(PreparedStatement query) {
		int result = 0;
		try
		{
			result = query.executeUpdate();
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		return result;
	}
	
	/*
	private static ResultSet executeQuery(String queryText)
	{
		VehicleDAO.loadDriver();
		VehicleDAO.connection = VehicleDAO.getConnection();
		PreparedStatement statement = VehicleDAO.getPreparedStatement(queryText);
		return VehicleDAO.executeQuery(statement);
	}*/
	
	

	public static boolean checkUserPass(String userName, String password)
	{
		boolean match = false;
		String queryText = "SELECT password FROM user WHERE username = ?";
		PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		
		String resultPassword = new String();
		try
		{
			ps.setString(1, userName);
			ResultSet r = VehicleDAO.executeQuery(ps);
			if(r.next())
			{
				resultPassword = r.getString("password");
			}
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		
		
		if(password.equals(resultPassword))
		{
			match = true;
		}
		
		return match;
		
	
		
	}
	
	public static int getUserId(String userName)
	{
		 String queryText = "SELECT userId FROM user WHERE username = ?";
		 PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		 int id=-1;
		 try
		 {
			 ps.setString(1, userName);
			 ResultSet r = VehicleDAO.executeQuery(ps);
			 if(r.next())
			 {
				 id = r.getInt("userId");
			 }
		 }
		 catch(Exception ex)
		 {
			 VehicleDAO.handleException(ex);
		 }
		 return id;
		 
	}
	
	
	
	

	public static User getUserById(int userId)
	{
		User user = new User();
		
		//lookup in user table
		String queryText = "SELECT * FROM user WHERE userId = ?";
		PreparedStatement ps1 = VehicleDAO.getPreparedStatement(queryText);
		
		
		//lookup in user_profile table
		queryText = "SELECT * FROM user_profile WHERE userId = ?";
		PreparedStatement ps2 = VehicleDAO.getPreparedStatement(queryText);
		
		
		try
		{
			ps1.setInt(1, userId);
			ps2.setInt(1, userId);
			
			ResultSet rs1 = VehicleDAO.executeQuery(ps1);
			ResultSet rs2 = VehicleDAO.executeQuery(ps2);
			
			if(rs1.next() && rs2.next())
			{
			
				user.setFirstName(rs2.getString("firstName"));
				user.setLastName(rs2.getString("lastName"));
				user.setUsername(rs1.getString("username"));
				user.setPassword(rs1.getString("password"));
				user.setUserId(userId);
			}
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		
		return user;
	}
	
	public static int enterNewUser(String userName, String password)
	{
		if(VehicleDAO.isUserNameAvailable(userName) == true)
		{
			String queryText = "INSERT INTO user (userName, password) VALUES (?,?)";
			PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
			try
			{
				ps.setString(1, userName);
				ps.setString(2, password);
			}
			catch(Exception ex)
			{
				VehicleDAO.handleException(ex);
			}
			VehicleDAO.executeUpdate(ps);
			return VehicleDAO.getUserId(userName);
		}
		else
		{
			return -1;
		}
	}
	
	public static boolean enterUserName(int userId, String firstName, String lastName)
	{
		
			String updateText = "INSERT INTO user_profile (userId, firstName, lastName) VALUES (?, ?, ?)";
			PreparedStatement ps2 = VehicleDAO.getPreparedStatement(updateText);
			try
			{
				ps2.setString(2, firstName);
				ps2.setString(3, lastName);
				ps2.setInt(1, userId);
			}
			catch(Exception ex)
			{
				VehicleDAO.handleException(ex);
			}
			VehicleDAO.executeUpdate(ps2);
			return true;
		
		
	}
	/*
	public static boolean enterUserName(int userId, String firstName, String lastName)
	{
		String lookupText = "SELECT * FROM user_profile WHERE userId = ?";
		PreparedStatement ps1 = VehicleDAO.getPreparedStatement(lookupText);
		try
		{
			ps1.setInt(1, userId);
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		ResultSet lookupResult = VehicleDAO.executeQuery(ps1);
		boolean exists = false;
		try
		{
			if(lookupResult.next() == true)
			{
				exists = true;
			}
			else
			{
				exists = false;
			}
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		
		if(exists)
		{
			String updateText = "INSERT INTO user_profile (userId, firstName, lastName) VALUES (?, ?, ?)";
			PreparedStatement ps2 = VehicleDAO.getPreparedStatement(updateText);
			try
			{
				ps2.setString(2, firstName);
				ps2.setString(3, lastName);
				ps2.setInt(1, userId);
			}
			catch(Exception ex)
			{
				VehicleDAO.handleException(ex);
			}
			VehicleDAO.executeUpdate(ps2);
			return true;
		}
		else
		{
			return false;
		}

		
	}*/
	
	public static boolean isUserNameAvailable(String userName)
	{
		String queryText = "SELECT * FROM user WHERE username = ?";
		PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		try
		{
			ps.setString(1, userName);
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		ResultSet r = VehicleDAO.executeQuery(ps);
		boolean available = false;
		try
		{
			if(r.next() == false)
			{
				available = true;
			}
			else
			{
				available = false;
			}
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		return available;
	}
	
	public static void DB_Close() throws Throwable
	{
		try {
				if(connection!=null) 
				{
					connection.close(); 
				}
			}
		
		catch(SQLException e) 
			{
				System.out.println(e);
			} 
	}
	
	
}
