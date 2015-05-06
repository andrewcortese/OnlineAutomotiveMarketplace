package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


	import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Driver;

import java.util.*;
	//import config.Config;


	/*
	 * TODO:
	 * (1) create a method to get a Order by its id
	 * (2) create a method to get all Orders matching a particular price
	 * (3) create a method to get all Orders matching the given make
	 * (4) create a method to get all Orders matching the given model
	 * (5) create a method to get all Orders matching the given year
	 * (6) create a method to get all Orders matching the given style
	 * (7) create a method to get all Orders matching the given seller's userID
	 * 
	 * 
	 * When you return "all Orders matching..." return it as an ArrayList<Order>
	 */


	public class OrderDAO {

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
				OrderDAO.handleException(ex);
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
			OrderDAO.loadDriver();
			OrderDAO.connection = OrderDAO.getConnection();
			PreparedStatement query = null;
			try
			{
				query = connection.prepareStatement(queryText);
			}
			catch(Exception ex)
			{
				OrderDAO.handleException(ex);
			}
			return query;
		}
		
		/**
		 * helper method to handle exceptions using the Logger
		 */
		private static void handleException(Exception ex)
		{
			Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
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
				OrderDAO.handleException(ex);
			}
			return result;
		}
		
		
		private static int executeUpdate(PreparedStatement query) {
			int result = 0;
			System.out.println(query.toString());
			try
			{
				result = query.executeUpdate();
			}
			catch(Exception ex)
			{
				OrderDAO.handleException(ex);
			}
			return result;
		}
		
		public static ArrayList<Order> getByMake(String make)
		{
			return OrderDAO.getOrders("make", make, -1, -1.0, DataKeyType.Str);
		}
		
		public static ArrayList<Order> getByModel(String model)
		{
			return OrderDAO.getOrders("model", model, -1, -1.0, DataKeyType.Str);
		}
		
		public static ArrayList<Order> getByStyle(String style)
		{
			return OrderDAO.getOrders("style", style, -1, -1.0, DataKeyType.Str);
		}
		
		public static ArrayList<Order> getByYear(int year)
		{
			return OrderDAO.getOrders("year", "", year, -1.0, DataKeyType.Int);
		}
		
		public static ArrayList<Order> getByPrice(double price)
		{
			return OrderDAO.getOrders("price", "", -1, price, DataKeyType.Dec);
		}
		
		public static ArrayList<Order> getOrders(String key, String stringValue, int intValue, double decValue, DataKeyType type)
		{
			String queryText = "SELECT * FROM Order WHERE "+key+" = ?";
			System.out.printf("\nQuery: %s", queryText);
			 PreparedStatement ps = OrderDAO.getPreparedStatement(queryText);
			 ArrayList<Order> vs = new ArrayList<Order>();
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
			
				 ResultSet r = OrderDAO.executeQuery(ps);
				 while(r.next())
				 {
					 int id = r.getInt("id");
					 vs.add(OrderDAO.getOrderById(id));
				 }
				 
			 }
			 catch(Exception ex)
			 {
				 OrderDAO.handleException(ex);
			 }
		
			 return vs;
		}
		
		
		
		
		

		public static Order getOrderById(int OrderId)
		{
			 String queryText = "SELECT * FROM Order WHERE id = ?";
			 PreparedStatement ps = OrderDAO.getPreparedStatement(queryText);
			
			 Order v = new Order();
			 try
			 {
				 ps.setInt(1, OrderId);
				 ResultSet r = OrderDAO.executeQuery(ps);
				 if(r.next())
				 {
					 
					 v.setId(OrderId);
					 v.setBuyerId(r.getInt("buyerID"));
					 v.setSellerId(r.getInt("buyerID"));
					 Vehicle ve = VehicleDAO.getVehicleById(r.getInt("vehicleID"));
					 v.setVehicle(ve);
					 
					
				 }
			 }
			 catch(Exception ex)
			 {
				 OrderDAO.handleException(ex);
			 }
			 return v;
		}
		
		public static int enterNewOrder(int buyer, int seller, int vehicle)
		{
			String queryText = "INSERT INTO LAB03.order (buyerID, sellerID) VALUES (1, 2)";
			//String queryText = "INSERT INTO orders (buyerID, sellerID, vehicleID, orderDate) VALUES (?,?,?,?)";
			PreparedStatement ps = OrderDAO.getPreparedStatement(queryText);
			try
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				//ps.setInt(1, buyer);
				//ps.setInt(2, seller);
				//ps.setInt(3, vehicle);
				//ps.setString(4, date.toString());
			}
			catch(Exception ex)
			{
				OrderDAO.handleException(ex);
			}
			OrderDAO.executeUpdate(ps);
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
		
		
	}

