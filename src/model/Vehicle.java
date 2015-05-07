package model;
import utilities.*;
public class Vehicle {
	
	int id;
	String make;
	String model;
	int year;
	int mileage;
	VehicleStyle style;
	double price;
	int sellerID;
	RatingList ratings;
	String description;
	String photoURL;
	User seller;
	
	
	
	public Vehicle(int id, String make, String model, int year, int mileage, VehicleStyle style, double price, int sellerID, RatingList ratings) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.style = style;
		this.price = price;
		this.sellerID = sellerID;
		this.ratings = ratings;
	}
	
	public Vehicle(int id, String make, String model, int year, int mileage, VehicleStyle style, double price, int sellerID) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.style = style;
		this.price = price;
		this.sellerID = sellerID;
		this.ratings = new RatingList();
	}
	
	
	
	public Vehicle()
	{
		super();
		this.id = -1;
		this.make = new String();
		this.model = new String(); 
		this.year = -1;
		this.mileage = -1;
		this.style = VehicleStyle.Sedan;
		this.price = -1;
		this.sellerID = -1;
		this.ratings = new RatingList();
		
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
		this.make = StringUtility.toTitleCase(make);
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
		this.model = StringUtility.toTitleCase(model);
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
	
	public void setStyle(String style)
	{
		this.style = VehicleStyle.Other;
		for(VehicleStyle s : VehicleStyle.values())
		{
			if(style.equalsIgnoreCase(s.toString()))
			{
				this.style = s;
			}
		}
		
	
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the sellerID
	 */
	public int getSellerID() {
		return sellerID;
	}

	/**
	 * @param sellerID the sellerID to set
	 */
	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	/**
	 * @return the ratings
	 */
	public RatingList getRatings() {
		return ratings;
	}

	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(RatingList ratings) {
		this.ratings = ratings;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the photoURL
	 */
	public String getPhotoURL() {
		return photoURL;
	}

	/**
	 * @param photoURL the photoURL to set
	 */
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	/**
	 * @return the seller
	 */
	public User getSeller() {
		return seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(User seller) {
		this.seller = seller;
	}
	
	
	
}
