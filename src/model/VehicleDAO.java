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
	
	public static ArrayList<Vehicle> getByMake(String make)
	{
		return VehicleDAO.getVehicles("make", make, -1, -1.0, DataKeyType.Str);
	}
	
	public static ArrayList<Vehicle> getByModel(String model)
	{
		return VehicleDAO.getVehicles("model", model, -1, -1.0, DataKeyType.Str);
	}
	
	public static ArrayList<Vehicle> getByStyle(String style)
	{
		return VehicleDAO.getVehicles("style", style.toLowerCase(), -1, -1.0, DataKeyType.Str);
	}
	
	public static ArrayList<Vehicle> getByYear(int year)
	{
		return VehicleDAO.getVehicles("year", "", year, -1.0, DataKeyType.Int);
	}
	
	public static ArrayList<Vehicle> getByPrice(double price)
	{
		return VehicleDAO.getVehicles("price", "", -1, price, DataKeyType.Dec);
	}
	
	public static ArrayList<Vehicle> getBySeller(int id)
	{
		return VehicleDAO.getVehicles("sellerID", new String(), id, -1.0, DataKeyType.Int);
	}
	
	public static ArrayList<Vehicle> getVehicles(String key, String stringValue, int intValue, double decValue, DataKeyType type)
	{
		String queryText = "SELECT * FROM Vehicle WHERE "+key+" = ?";
		System.out.printf("\nQuery: %s", queryText);
		 PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		 ArrayList<Vehicle> vs = new ArrayList<Vehicle>();
		 try
		 {
			 if(type == DataKeyType.Str)
			 {
				 ps.setString(1, stringValue.toLowerCase());
			 }
			 else if(type == DataKeyType.Int)
			 {
				 ps.setInt(1, intValue);
			 }
			 else if(type == DataKeyType.Dec)
			 {
				 ps.setDouble(1, decValue);
			 }
		
			 ResultSet r = VehicleDAO.executeQuery(ps);
			 while(r.next())
			 {
				 int id = r.getInt("id");
				 vs.add(VehicleDAO.getVehicleById(id));
			 }
			 
		 }
		 catch(Exception ex)
		 {
			 VehicleDAO.handleException(ex);
		 }
		 for(Vehicle v : vs)
		 {
			 System.out.println(v.getMake() + " " + v.getModel());
		 }
		 return vs;
	}
	
	public static int deleteVehicle(int id)
	{
		
		String queryText = "DELETE FROM Vehicle WHERE id = ?";
		PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		try
		{
			ps.setInt(1, id);
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		VehicleDAO.executeUpdate(ps);
		return 1;
	}
	
	
	public static ArrayList<Vehicle> getAll()
	{
		ArrayList<Vehicle> all = new ArrayList<Vehicle>();
		String queryText = "SELECT * FROM Vehicle";
		
		 PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		 ArrayList<Vehicle> vs = new ArrayList<Vehicle>();
		 try
		 {
			 ResultSet r = VehicleDAO.executeQuery(ps);
			 while(r.next())
			 {
				 int id = r.getInt("id");
				 vs.add(VehicleDAO.getVehicleById(id));
			 }
		 }
		 catch(Exception ex)
		 {
			 VehicleDAO.handleException(ex);
		 }
		
		 return vs;
		
	}
	

	public static Vehicle getVehicleById(int vehicleId)
	{
		 String queryText = "SELECT * FROM Vehicle WHERE id = ?";
		 PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		 int id=-1;
		 Vehicle v = new Vehicle();
		 try
		 {
			 ps.setInt(1, vehicleId);
			 ResultSet r = VehicleDAO.executeQuery(ps);
			 if(r.next())
			 {
				 System.out.println(r.getString("make"));
				 v.setId(vehicleId);
				 v.setMake(r.getString("make"));
				 v.setModel(r.getString("model"));
				 v.setYear(r.getInt("year"));
				 v.setPrice(r.getDouble("price"));
				 v.setSellerID(r.getInt("sellerID"));
				 v.setMileage(r.getInt("mileage"));
				 v.setStyle(r.getString("style"));
				 User seller = AuthDAO.getUserById(v.getSellerID());
				 v.setSeller(seller);
				 
				 id = r.getInt("id");
			 }
		 }
		 catch(Exception ex)
		 {
			 VehicleDAO.handleException(ex);
		 }
		 return v;
	}
	
	public static int enterNewVehicle(String make, String model, String style, int year, double price, int sellerID, int mileage)
	{
		
		String queryText = "INSERT INTO Vehicle (make, model, style, year, price, sellerID, mileage) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = VehicleDAO.getPreparedStatement(queryText);
		try
		{
			ps.setString(1, make);
			ps.setString(2, model);
			ps.setString(3, style);
			ps.setInt(4,  year);
			ps.setDouble(5, price);
			ps.setInt(6, sellerID);
			ps.setInt(7,mileage);
		}
		catch(Exception ex)
		{
			VehicleDAO.handleException(ex);
		}
		VehicleDAO.executeUpdate(ps);
		return 1;
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

	public static ArrayList<Vehicle> getByMileage(int miles) {
		return VehicleDAO.getVehicles("mileage", "", miles, -1.0, DataKeyType.Int);
	}
	
	
}
