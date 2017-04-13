import java.util.*;
import java.io.*;

public class EricsMethods
{
	//Global Variables
	public static ArrayList<ArrayList<String>> airportDetails;// = new <ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> flightDetails;// = new <ArrayList<ArrayList<String>>();
	public static File file1 = new File("Airports.txt");
	public static File file2 = new File("Flights.txt");
	public static String [] userInput = new String [] {"SF","Dublin","Aberdeen"};
	
	public static void main(String [] args) throws IOException
	{
		//loadFile
		loadFiles();
		//writeFiles(filename, arraylist)... must be called for each
		writeFiles(file1, airportDetails);
		writeFiles(file2, flightDetails);
		//searchByAirports
		searchByAirports();
	}
	
	/*
	Search Flights by Departure and Arrival Airports - Eric Lambert
	This method takes no arguments, and returns no values. It does, however, access both global arraylists. 
	When this method is called, it is assumed it has passed validation. Meaning that there are 3 arguments given when the command line argument executed. SF = [0], Airport1 = [1], Airport2 = [2]. 
	Firstly, the method attempts to match the airport name with its IATA (International Air Transportation Association) code, not the ICAO code.
	If no flights are found with the departing and arrival airport that matches the IATO code, it prints out the failure message, including the name of the airports as provided by the user.
	Should there be match(es), the method finds each instance in flightDetails with the given airports, and eventually prints out the results after the success report.
	*/
	public static void searchByAirports()
	{
		int index = 0, amountFound = 0;
		String airport1 = userInput[1];
		String airport2 = userInput[2];
		String IATA1 = "", IATA2 = "", result = "", foundResult = "";
		String success = "";
		String failure = ("No flights are departing " + airport1 + " and arriving at " + airport2 + ".");
		
		for(int i = 0; i < airportDetails.get(0).size(); i++)
		{
			if (airport1.matches(airportDetails.get(0).get(i)))
				IATA1 = airportDetails.get(1).get(i);
			if (airport2.matches(airportDetails.get(0).get(i)))
				IATA2 = airportDetails.get(1).get(i);
		}
		
		while((IATA1.length() > 0) && (IATA2.length() > 0) && (index < flightDetails.get(0).size()))
		{
			if((flightDetails.get(1).get(index).equals(IATA1))&&(flightDetails.get(2).get(index).equals(IATA2)))
			{
				foundResult = "";
				for(int i = 0; i < flightDetails.size();i++)
				{
					foundResult += flightDetails.get(i).get(index);
					if(i+1 < flightDetails.size())
						foundResult += ", ";
				}
				foundResult += "\n";
				result += foundResult;
				amountFound++;
			}
			index++;
		}
		
		if(amountFound == 0)
			System.out.println(failure);
		else
		{	
			success = (amountFound + " flight(s) found departing " + airport1 + " and arriving at " + airport2 + ":\n");
			System.out.println(success + result);
		}
	}
	
	/*
	Load Files - Eric Lambert
	This is a fairly straight forward method. The method takes no arguments, and also returns no values, although it does take the global variables such as both arraylists and filenames.
	It begins with adding the necessary inner arraylists to each of the exterior arraylists, such that they can take the necessary formated input.
	Afterwards, it checks to see if the files exist. If they do, this method takes all the input, split at "," and loads it into the necessary arraylist.
	I could have structured this method similar to the writeFiles() method, as it would be more efficient so that only the necessary files accessed and loaded into an arraylist.
	Regardless, I decided to load both files in the singular method, so it only had to be called once.
	Finally there is a commented out "test" portion to make sure the arraylist was constructed properly, and the interior elements are correct.
	I decided to leave in the commented out "test" code, as an easy way to debug in the future.
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
		This method takes 2 arguments, a file, and a 2D arraylist.
		The idea of writing a singular file with the arraylist contents, is that if only 1 file was changed, it would only write over the values of that file, leaving the other file untouched.
		If both files needed to be changed/altered, this method would have to be called twice, with both combinations of desired file and desired arraylist.
		One vulnerability is the fucntion being called with the unintended combination of file1 and arraylist2, for example. The code will still execute, however there may be problems on the subsequent attempt of running the code.
		A goal of this method, was it was designed to take any 2D arraylist and be able to write to a file.	My goal was not to hardcode any value, with the exception of the split character.
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
