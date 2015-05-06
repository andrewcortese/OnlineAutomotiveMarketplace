/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author Andrew
 *
 */
public class User implements Serializable {

	/**
	 * required for Serializable implementers
	 */
	private static final long serialVersionUID = -7351729135012380019L;

	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int userId;
	private AccountType accountType;
	private String email;
	
	
	
	/**
	 * nullary constructor for JavaBean
	 */
	public User()
	{
		this.username = new String();
		this.password = new String();
		this.firstName = new String();
		this.lastName = new String();
		this.userId = -1;
		this.accountType = AccountType.Buyer;
		this.email = "None@None.com";
	}
	

	public String getFullName()
	{
		return (this.getFirstName() + " " + this.getLastName());
	}
	
	public boolean isEmpty()
	{
		if(username == null || username.isEmpty())
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}


	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
