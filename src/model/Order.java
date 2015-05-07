package model;

import java.util.Date;

public class Order {

	int id;
	Vehicle vehicle;
	int sellerId;
	int buyerId;
	Date dateOrdered;
	boolean isOpen;
	User seller;
	User buyer;
	
	
	public Order()
	{
		this(null, -1, -1, new Date(), false, new User(), new User());
	}
	
	public Order(Vehicle vehicle, int sellerId, int buyerId, Date dateOrdered,
			boolean isOpen, User b, User s) 
	{
		super();
		this.vehicle = vehicle;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.dateOrdered = dateOrdered;
		this.isOpen = isOpen;
		seller=s;
		buyer=b;
	}
	
	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	/**
	 * @return the sellerId
	 */
	public int getSellerId() {
		return sellerId;
	}
	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	/**
	 * @return the buyerId
	 */
	public int getBuyerId() {
		return buyerId;
	}
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	/**
	 * @return the dateOrdered
	 */
	public Date getDateOrdered() {
		return dateOrdered;
	}
	/**
	 * @param dateOrdered the dateOrdered to set
	 */
	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	/**
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}
	/**
	 * @param isOpen the isOpen to set
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
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

	/**
	 * @return the buyer
	 */
	public User getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	
}
