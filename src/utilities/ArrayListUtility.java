package utilities;

import java.util.*;
public class ArrayListUtility {

	public static <T> ArrayList<T> intersection(ArrayList<T> a, ArrayList<T> b)
	{
		ArrayList<T> result = new ArrayList<T>();
		for(T t : a)
		{
			if(b.contains(t))
			{
				result.add(t);
			}
		}
		
		
		return result;
	}
	
	public static <T> ArrayList<T> union(ArrayList<T> a, ArrayList<T> b)
	{
		ArrayList<T> result = new ArrayList<T>();
		result.addAll(a);
		for(T t : b)
		{
			if(!result.contains(t))
			{
				result.add(t);
			}
		}
		
		return result;
	}
	
	public static <T> ArrayList<T> rightToLeftCombine(ArrayList<T> left, ArrayList<T> right)
	{
		ArrayList<T> result = new ArrayList<T>();
		if(left.isEmpty())
		{
			result.addAll(right);
		}
		else
		{
			result = intersection(left, right);
		}
		
		return result;
	}
	
}
