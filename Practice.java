import java.util.*;

public class Practice
{
	public static void main(String [] args)
	{
		//Collections.sort(anArrayList); how to sort a single dimension arraylist
		int maxNumber = 10, temp, numberSize = 10, numberMinimum;
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		for(int i = 0; i < maxNumber; i++)
		{
			temp = (int)(Math.random()*numberSize) + 1;
			list1.add(temp);
		}
		System.out.println(list1);
		
		System.out.println(list1.size());
		
		// creates a multideminsional array list
		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		
		//Makes the first array list
		list2.add(new ArrayList<Integer>()); 
		
		//Make the secondary arraylist
		list2.add(new ArrayList<Integer>());
		
		maxNumber = 5;
		numberSize = 10;
		
		//Random numbers in the first list in list2
		for(int i = 0; i < maxNumber; i++)
			list2.get(0).add((int)(Math.random()*numberSize+1));
		
		//Random numbers in the second list in list2
		numberMinimum = 11;
		for(int i = 0; i < maxNumber; i++)
			list2.get(1).add((int)(Math.random()*numberSize+numberMinimum));
		
		System.out.println(list2);
		
		System.out.println("List 2 size: " + list2.size());
		
		System.out.println("List 2.get 0 size: " + list2.get(0).size());
		
		String result = "";
		System.out.println("Matching in pairs of 2:\n");
		for (int inner = 0; inner < list2.get(0).size(); inner ++)
		{
			result = "";
			for(int outer = 0; outer < list2.size(); outer++)
			{
				result += list2.get(outer).get(inner);
				if (outer + 1 < list2.size())
					result += ", ";
			}
			System.out.println(result);
		}		
		
		//Reverse Order????
		/*
		for(int j = 0; j < airportDetails.size(); j++)
		{
			result = "";
			for(int i = 0; i < airportDetails.get(0).size(); i++)
			{
				result += airportDetails.get(j).get(i);
				if(j+1 < airportDetails.size())
					result += ",";
			}
			System.out.println(result);
		}
		*/

	}
}