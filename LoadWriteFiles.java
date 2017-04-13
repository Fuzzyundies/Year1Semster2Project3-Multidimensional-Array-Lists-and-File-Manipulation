import java.util.*;
import java.io.*;

public class LoadWriteFiles
{
	//Global Variables
	public static ArrayList<ArrayList<String>> airportDetails;// = new <ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> flightDetails;// = new <ArrayList<ArrayList<String>>();
	public static File file1 = new File("Airports.txt");
	public static File file2 = new File("Flights.txt");
	public static userInput [];
	
	public static void main(String [] args) throws IOException
	{
		loadFiles();
		writeFiles(file1, airportDetails);
		writeFiles(file2, flightDetails);
	}
	
	
	/*
	Load Files - Eric Lambert
	This is a fairly straight forward method. The method takes no arguments, and also returns no values.
	It begins with adding the necessary inner arraylists to each of the exterior arraylists, such that they can take the necessary formated input.
	Afterwards, it checks to see if the files exist. If they do, this method takes all the input, split at "," and loads it into the necessary arraylist.
	Finally there is a commented out "test" portion to make sure the arraylist was constructed properly, and the interior elements are correct.
	*/
	public static void loadFiles() throws IOException
	{
		int airportParts = 2, flightParts = 8;
		Scanner in;
		String[] fileElements;
		airportDetails = new ArrayList<ArrayList<String>>();
		flightDetails = new ArrayList<ArrayList<String>>();
		
		
		//Create the inner arraylists, regardless if the file exists
		for (int i = 0; i < airportParts; i++)
			airportDetails.add(new ArrayList<String>());
		for (int i = 0; i <flightParts; i++)
			flightDetails.add(new ArrayList<String>());
		
		
		//if Airports.txt exists, it loads the values into the airportDetails
		if(file1.exists())
		{
			in = new Scanner(file1);
			while(in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				for (int i = 0; i < airportParts; i++)
					airportDetails.get(i).add(fileElements[i]);
			}
			in.close();
		}
		
		//if Flights.txt exists, it loads the values into flightDetails
		if(file2.exists())
		{
			in = new Scanner(file2);
			while(in.hasNext())
			{
				fileElements = in.nextLine().split(",");
				for (int i = 0; i < flightParts; i++)
					flightDetails.get(i).add(fileElements[i]);
			}
			in.close();
		}
		
		/*
		//Testing Purposes Only 
		//Print out both ArrayLists to make sure they copied properly
		String result;
		System.out.println("Airport Details:");
		for(int i = 0; i < airportDetails.get(0).size(); i++)
		{
			result = "";
			for(int j = 0; j < airportDetails.size(); j++)
			{
				result += airportDetails.get(j).get(i);
				if(j+1 < airportDetails.size())
					result += ",";
			}
			System.out.println(result);
		}
		
		System.out.println("\nFlight Details:");
		for(int i = 0; i < flightDetails.get(0).size(); i++)
		{
			result = "";
			for(int j = 0; j < flightDetails.size(); j++)
			{
				result += flightDetails.get(j).get(i);
				if(j+1 < flightDetails.size())
					result += ",";
			}
			System.out.println(result);
		}
		*/
	}
	
	/*
		writeFiles - Eric Lambert
		This method takes 2 arguments, a file, and a 2D arraylist. The method is designed to take any 2D arraylist and be able to write to a file.
		My goal was not to hardcode any value, with the exception of the split character.
		The file being written to is the aFile name that is passed. If the file does not exist, or the filename was entered wrong, the system will create a new file by the passed name.
		The interior if statement is to put in the desired split character, with no extra iterations.
	*/
	public static void writeFiles(File aFile, ArrayList<ArrayList<String>> anArrayList) throws IOException
	{
		String result;
		PrintWriter out = new PrintWriter(aFile);
		
		for(int i = 0; i < anArrayList.get(0).size(); i++)
		{
			result = "";
			for (int j = 0; j < anArrayList.size(); j++)
			{
				result += anArrayList.get(j).get(i);
				if(j+1 < anArrayList.size())
					result += ",";
			}
			out.println(result);
		}
		out.close();
	}
}
