package model;

/**
 * @author Andrew
 *
 */
public class Rating {
	
	
	public static double MAX_RATING()
	{
		return 5.0;
	}
	
	private double numStars;
	private String comment;
	
	public Rating(double numStars)
	{
		this.setNumStars(numStars);
		this.setComment(new String());
	}
	
	public Rating(double numStars, String comment)
	{
		this.setNumStars(numStars);
		this.setComment(comment);
	}
	
	
	private void setNumStars(double numStars)
	{
		if(numStars > MAX_RATING())
		{
			numStars = MAX_RATING();
		}
		this.numStars = numStars;
	}
	
	private void setComment(String comment)
	{
		this.comment = comment;
	}
	
	public double getNumStars()
	{
		return this.numStars;
	}
	
	public String getComment()
	{
		return this.comment;
	}
	
	public String toString()
	{
		return String.format("Stars: %s \n Comment: \n%2", this.getNumStars(), this.getComment());
	}
	
}
