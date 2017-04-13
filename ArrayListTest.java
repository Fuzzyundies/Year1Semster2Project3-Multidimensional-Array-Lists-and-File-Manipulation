import java.io.*;
import java.util.*;

public class ArrayListTest
{
	public static void main(String [] args) throws IOException
	{
		int airportInfoParts = 2, flightInfoParts = 8;
		String fileElements []; 
		String fileNotFound = "Necessary file(s) not found. Please check that \"Airports.txt\" and \"Flights.txt\" exist in this folder.";
		File file1 = new File("Airports.txt");
		File file2 = new File("Flights.txt");
		//Collections.sort(anArrayList); how to sort a single dimension arraylist
		ArrayList<ArrayList<String>> airports = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < airportInfoParts; i++)
			airports.add(new ArrayList<String>());
		
		ArrayList<ArrayList<String>> flights = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < flightInfoParts; i++)
			flights.add(new ArrayList<String>());
		
		if (file1.exists() && file2.exists())
		{
			Scanner in;
			in = new Scanner(file1);
			while(in.hasNext())
			{
				fileElements = (in.nextLine()).split(",");
				for(int i = 0; i < airportInfoParts; i++)
				{	
					airports.get(i).add(fileElements[i]);
				}
			}
			in.close();
			in = new Scanner(file2);
			while(in.hasNext())
			{
				fileElements = (in.nextLine()).split(",");
				for(int i = 0; i <flightInfoParts; i++)
				{
					flights.get(i).add(fileElements[i]);
				}
			}
			in.close();
		}
		else
			System.out.println(fileNotFound);
		
		System.out.println("Airports:");
		for(int i = 0; i < airports.size()-1; i++)
		{
			for(int j = 0; j < airports.get(i).size();j++)
			{
				System.out.print(j + ":");
				System.out.print(airports.get(i).get(j));
				System.out.print(", ");
				System.out.println(airports.get(i+1).get(j));
			}
		}
		System.out.println(airports);
	}
}