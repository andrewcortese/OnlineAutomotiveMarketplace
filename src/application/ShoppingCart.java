package application;

import java.util.ArrayList;

import model.Vehicle;

public class ShoppingCart {
	
	private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	
public ShoppingCart(){
	super();
}

public static void add(Vehicle v){
	
	vehicles.add(v);
}

public static void remove(){
	
	vehicles.remove(vehicles);
}


public static int count(){
	
	
	return vehicles.size();
	
	
}

public static double calculateTotal(){   
	double total = 0;
	if(vehicles == null)
	{
		vehicles = new ArrayList<Vehicle>();
	}
    for(Vehicle v : vehicles)
    {
      total += v.getPrice();
    }
	
	return total;
}


public static ArrayList<Vehicle> getVehicles() {
	return vehicles;
}



public static void setVehicles(ArrayList<Vehicle> vehicles) {
	ShoppingCart.vehicles = vehicles;
}
  
public static void clear()
{
	ShoppingCart.vehicles = new ArrayList<Vehicle>();
}

}
