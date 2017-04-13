 import java.io.*;
 import java.util.*;
 
 public class WriteFile
 {
	public static void main(String [] args) throws IOException
	{
		int airportInfoParts = 2, flightInfoParts = 8;
		String fileElements [];
		String result = "";
		String fileNotFound = "Necessary file(s) not found. Please check that \"Airports.txt\" and \"Flights.txt\" exists in this folder.";
	 	File file1 = new File("Airports.txt");
		File file2 = new File("Flights.txt");
		File file3 = new File("AirportTest.txt");
		File file4 = new File("FlightTest.txt");
		Scanner in;
		ArrayList<ArrayList<String>> airports = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < airportInfoParts; i++)
			airports.add(new ArrayList<String>());
		
		ArrayList<ArrayList<String>> flights = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < flightInfoParts; i++)
			flights.add(new ArrayList<String>());
		
		if (file1.exists() && file2.exists())
		{

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
				for(int i = 0; i < flightInfoParts; i++)
				{
					flights.get(i).add(fileElements[i]);
				}
			}
			in.close();
		}
		else
			System.out.println(fileNotFound);
		
		System.out.println("\nFlights: ");
		for(int i = 0; i < flights.get(0).size(); i++)
		{
			result = "";
			for (int j = 0; j < flights.size(); j++)
			{
				result += flights.get(j).get(i);
				if((j + 1) < flights.size())
					result += ",";
			}
			System.out.println(result);
		}
		
		System.out.println("\nAirports: ");
		for(int i = 0; i < airports.get(0).size(); i++)
		{
			result = "";
			for(int j = 0; j < airports.size(); j++)
			{
				result += airports.get(j).get(i);
				if((j + 1) < airports.size())
					result += ",";
			}
			System.out.println(result);
		}
		
		PrintWriter out = new PrintWriter(file3);
		for(int i =0; i < airports.get(0).size(); i++)
		{
			result = "";
			for(int j = 0; j < airports.size(); j++)
			{
				result += airports.get(j).get(i);
				if(j + 1 < airports.size())
					result += ",";
			}
			out.println(result);
		}
		out.close();
		
		out = new PrintWriter(file4);
		for(int i = 0; i < flights.get(0).size(); i++)
		{
			result = "";
			for(int j = 0; j < flights.size(); j++)
			{
				result += flights.get(j).get(i);
				if(j + 1 < flights.size())
					result += ",";
			}
			out.println(result);
		}
		out.close();
	}
 }
 
 /*
	Checks to see if both files are there, if not still continue, create array list, and write to file
	
	FileNotFoundException e
 */