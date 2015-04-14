package model;
import java.util.*;
public class RatingList {

	ArrayList<Rating> ratings;
	
	public RatingList()
	{
		ratings = new ArrayList<Rating>();
	}
	
	public void rate(Rating rating)
	{
		ratings.add(rating);
	}
	
	public double getAverage()
	{
		double sum = 0;
		for(Rating rating : ratings)
		{
			sum += rating.getNumStars();
		}
		return sum / Rating.MAX_RATING();
	}
	
	
	public String toString()
	{
		String str = new String();
		for(Rating r : ratings)
		{
			str += r.toString() + "\n\n";
		}
		return str;
	}
}
