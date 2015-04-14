package model;

public class Vehicle {
	int id;
	String make;
	String model;
	int year;
	int mileage;
	VehicleStyle style;
	
	
	public Vehicle(int id, String make, String model, int year, int mileage, VehicleStyle style) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.style = style;
	}
	
	public Vehicle()
	{
		super();
		this.id = -1;
		this.make = new String();
		this.model = new String(); 
		this.year = -1;
		this.mileage = -1;
		this.style = null;
	}
	
	public boolean isEmpty()
	{
		if(id==-1 && 
		   make.isEmpty() && 
		   model.isEmpty() && 
		   year == -1 && 
		   mileage == -1 && 
		   style == null)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	
	
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	
	
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	
	
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	
	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}
	
	
	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	
	/**
	 * @return the style
	 */
	public VehicleStyle getStyle() {
		return style;
	}
	
	
	/**
	 * @param style the style to set
	 */
	public void setStyle(VehicleStyle style) {
		this.style = style;
	}
	
	
	
}
