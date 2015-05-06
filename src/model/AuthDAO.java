package model;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Driver;
//import config.Config;

public class AuthDAO {

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
			AuthDAO.handleException(ex);
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
		AuthDAO.loadDriver();
		AuthDAO.connection = AuthDAO.getConnection();
		PreparedStatement query = null;
		try
		{
			query = connection.prepareStatement(queryText);
		}
		catch(Exception ex)
		{
			AuthDAO.handleException(ex);
		}
		return query;
	}
	
	/**
	 * helper method to handle exceptions using the Logger
	 */
	private static void handleException(Exception ex)
	{
		Logger.getLogger(AuthDAO.class.getName()).log(Level.SEVERE, null, ex);
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
			AuthDAO.handleException(ex);
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
			AuthDAO.handleException(ex);
		}
		return result;
	}
	
	/*
	private static ResultSet executeQuery(String queryText)
	{
		AuthDAO.loadDriver();
		AuthDAO.connection = AuthDAO.getConnection();
		PreparedStatement statement = AuthDAO.getPreparedStatement(queryText);
		return AuthDAO.executeQuery(statement);
	}*/
	
	

	public static boolean checkUserPass(String userName, String password)
	{
		boolean match = false;
		String queryText = "SELECT password FROM user WHERE username = ?";
		PreparedStatement ps = AuthDAO.getPreparedStatement(queryText);
		
		String resultPassword = new String();
		try
		{
			ps.setString(1, userName);
			ResultSet r = AuthDAO.executeQuery(ps);
			if(r.next())
			{
				resultPassword = r.getString("password");
			}
		}
		catch(Exception ex)
		{
			AuthDAO.handleException(ex);
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
		 PreparedStatement ps = AuthDAO.getPreparedStatement(queryText);
		 int id=-1;
		 try
		 {
			 ps.setString(1, userName);
			 ResultSet r = AuthDAO.executeQuery(ps);
			 if(r.next())
			 {
				 id = r.getInt("userId");
			 }
		 }
		 catch(Exception ex)
		 {
			 AuthDAO.handleException(ex);
		 }
		 return id;
		 
	}
	
	
	
	

	public static User getUserById(int userId)
	{
		User user = new User();
		
		//lookup in user table
		String queryText = "SELECT * FROM user WHERE userId = ?";
		PreparedStatement ps1 = AuthDAO.getPreparedStatement(queryText);
		
		
		//lookup in user_profile table
		queryText = "SELECT * FROM user_profile WHERE userId = ?";
		PreparedStatement ps2 = AuthDAO.getPreparedStatement(queryText);
		
		
		try
		{
			ps1.setInt(1, userId);
			ps2.setInt(1, userId);
			
			ResultSet rs1 = AuthDAO.executeQuery(ps1);
			ResultSet rs2 = AuthDAO.executeQuery(ps2);
			
			if(rs1.next() && rs2.next())
			{
			
				user.setFirstName(rs2.getString("firstName"));
				user.setLastName(rs2.getString("lastName"));
				user.setUsername(rs1.getString("username"));
				user.setPassword(rs1.getString("password"));
				user.setUserId(userId);
				String acctType = rs2.getString("accountType");
				if(acctType == null || acctType.equalsIgnoreCase("buyer"))
				{
					user.setAccountType(AccountType.Buyer);
				}
				else if(acctType.equalsIgnoreCase("seller"))
				{
					user.setAccountType(AccountType.Seller);
				}
				else if(acctType.equalsIgnoreCase("admin"))
				{
					user.setAccountType(AccountType.Admin);
				}
				else
				{
					user.setAccountType(AccountType.Buyer);
				}
				user.setEmail(rs2.getString("email"));
				user.setPaypal(rs2.getString("paypal"));
				System.out.println(user.getEmail());
			}
		}
		catch(Exception ex)
		{
			AuthDAO.handleException(ex);
		}
		
		return user;
	}
	
	public static int enterNewUser(String userName, String password)
	{
		if(AuthDAO.isUserNameAvailable(userName) == true)
		{
			String queryText = "INSERT INTO user (userName, password) VALUES (?,?)";
			PreparedStatement ps = AuthDAO.getPreparedStatement(queryText);
			try
			{
				ps.setString(1, userName);
				ps.setString(2, password);
			}
			catch(Exception ex)
			{
				AuthDAO.handleException(ex);
			}
			AuthDAO.executeUpdate(ps);
			return AuthDAO.getUserId(userName);
		}
		else
		{
			return -1;
		}
	}
	
	public static boolean enterUserInfo(int userId, String firstName, String lastName)
	{
		
			String updateText = "INSERT INTO user_profile (userId, firstName, lastName) VALUES (?, ?, ?)";
			PreparedStatement ps2 = AuthDAO.getPreparedStatement(updateText);
			try
			{
				ps2.setString(2, firstName);
				ps2.setString(3, lastName);
				ps2.setInt(1, userId);
			}
			catch(Exception ex)
			{
				AuthDAO.handleException(ex);
			}
			AuthDAO.executeUpdate(ps2);
			return true;
		
		
	}
	public static boolean enterUserInfo(int userId, String firstName, String lastName, AccountType type, String email, String paypal)
	{
		
			String updateText = "INSERT INTO user_profile (userId, firstName, lastName, accountType, email, paypal) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps2 = AuthDAO.getPreparedStatement(updateText);
			try
			{
				ps2.setString(2, firstName);
				ps2.setString(3, lastName);
				ps2.setInt(1, userId);
				ps2.setString(4, type.toString());
				ps2.setString(5, email);
				ps2.setString(6, paypal);
			}
			catch(Exception ex)
			{
				AuthDAO.handleException(ex);
			}
			AuthDAO.executeUpdate(ps2);
			return true;
		
		
	}
	/*
	public static boolean enterUserName(int userId, String firstName, String lastName)
	{
		String lookupText = "SELECT * FROM user_profile WHERE userId = ?";
		PreparedStatement ps1 = AuthDAO.getPreparedStatement(lookupText);
		try
		{
			ps1.setInt(1, userId);
		}
		catch(Exception ex)
		{
			AuthDAO.handleException(ex);
		}
		ResultSet lookupResult = AuthDAO.executeQuery(ps1);
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
			AuthDAO.handleException(ex);
		}
		
		if(exists)
		{
			String updateText = "INSERT INTO user_profile (userId, firstName, lastName) VALUES (?, ?, ?)";
			PreparedStatement ps2 = AuthDAO.getPreparedStatement(updateText);
			try
			{
				ps2.setString(2, firstName);
				ps2.setString(3, lastName);
				ps2.setInt(1, userId);
			}
			catch(Exception ex)
			{
				AuthDAO.handleException(ex);
			}
			AuthDAO.executeUpdate(ps2);
			return true;
		}
		else
		{
			return false;
		}

		
	}*/
	
	public static int removeUserById(String userId)
	{
		 String queryText = "DELETE FROM user WHERE userId = ?";
		 PreparedStatement ps = AuthDAO.getPreparedStatement(queryText);
		 int id=-1;
		 try
		 {
			 ps.setString(1, userId);
			 AuthDAO.executeUpdate(ps);
		 }
		 catch(Exception ex)
		 {
			 AuthDAO.handleException(ex);
		 }
		 return id;
	}
	
	
	public static boolean isUserNameAvailable(String userName)
	{
		String queryText = "SELECT * FROM user WHERE username = ?";
		PreparedStatement ps = AuthDAO.getPreparedStatement(queryText);
		try
		{
			ps.setString(1, userName);
		}
		catch(Exception ex)
		{
			AuthDAO.handleException(ex);
		}
		ResultSet r = AuthDAO.executeQuery(ps);
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
			AuthDAO.handleException(ex);
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
