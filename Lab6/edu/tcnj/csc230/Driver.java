/** Hank Harvey and Brett Ratner
*	CSC230-02
*	Lab05
*/

/**
 *	This program creates a different Person array for each type of sorting method
 *	but they are all sorting for the origial Person array called personObj.
 */

package edu.tcnj.csc230;
import jsjf.*;
import java.net.URL;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *	This program calls the getData() method and after it gets all the data from the URL
 *	it promps the user asking them how many elements in the list they would like to
 *	sort through. Than it will sort through the original array using each of the 5
 *	sorts and print out the what sort it was and how many comparisons it has and how 
 *	long it tool on nanoSeconds.
 */

public class Driver
{
	/**
	*	Arrays to store copies for all the different sorts
	*/
	private static Person[] personObj = new Person[70000];
	private static Person[] selectionObj;
	private static Person[] bubbleObj;
	private static Person[] mergeObj;
	private static Person[] insertionObj;
	private static Person[] quickObj;	

	private static Long[] timeObj = new Long[5];

	/**
	 *	This method calls getData() method to read the URL than it asks the user
	 *	how many elements they would want to sort, than it will sort that many 
	 *	elements for each sorting type. 
	 */
	public static void main(String [] args) throws MalformedURLException, IOException
	{	
		
		getData();

		FileWriter out = new FileWriter(new File("output.txt"));

		Scanner input = new Scanner(System.in);

		System.out.println("How many elements do you want to sort?");
		
		int elementNum = input.nextInt();
		
		selectionObj = new Person[elementNum];
		bubbleObj = new Person[elementNum];
		mergeObj = new Person[elementNum];
		insertionObj = new Person[elementNum];
		quickObj = new Person[elementNum];
		
		for(int i = 0; i < elementNum; i++)
		{
			selectionObj[i] = personObj[i];
			bubbleObj[i] = personObj[i];
			mergeObj[i] = personObj[i];
			insertionObj[i] = personObj[i];
			quickObj[i] = personObj[i];
		}

		DecimalFormat formatter = new DecimalFormat("#,###");

		long begin = 0;
		long end = 0;
		String endTime = null;

		/**
		*	Instance Variables for keeping track of count and time
		*/
		long bubbleTime = 0;
		long bubbleCount = 0;
		long selectionTime = 0;
		long selectionCount = 0;
		long insertionTime = 0;
		long insertionCount = 0;
		long mergeTime = 0;
		long mergeCount = 0;
		long quickTime = 0;
		long quickCount = 0;

		
		out.write("Sort Name     # Comparisons     # Nanoseconds \n");
		
		out.write("-------------------------------------------------\n");
		
		/**	
		 *	Calls the bubble sort and stores values for count and time
		 */

		begin = System.nanoTime();
		Sorting.bubbleSort(bubbleObj);
		end = System.nanoTime();
		timeObj[0] = end - begin;
		bubbleTime = end - begin;
		bubbleCount = bubbleObj[elementNum - 1].getCount();
		bubbleObj[elementNum - 1].setCount(0);		
		
		/**	
		 *	Calls the selection sort and stores values for count and time
		 */
		
		begin = System.nanoTime();
		Sorting.selectionSort(selectionObj);
		end = System.nanoTime();
		timeObj[1] = end - begin;
		selectionTime = end - begin;
		selectionCount = selectionObj[elementNum - 1].getCount();
		selectionObj[elementNum - 1].setCount(0);

		/**	
		 *	Calls the insertion sort and stores values for count and time
		 */
		
		begin = System.nanoTime();
		Sorting.insertionSort(insertionObj);
		end = System.nanoTime();
		timeObj[2] = end - begin;
		insertionTime = end - begin;	
		insertionCount = insertionObj[elementNum - 1].getCount();
		insertionObj[elementNum - 1].setCount(0);

		/**	
		 *	Calls the merge sort and stores values for count and time
		 */
		
		begin = System.nanoTime();
		Sorting.mergeSort(mergeObj);
		end = System.nanoTime();
		timeObj[3] = end - begin;	
		mergeTime = end - begin;
		mergeCount = mergeObj[elementNum - 1].getCount();
		mergeObj[elementNum - 1].setCount(0);


		/**	
		 *	Calls the quick sort and stores values for count and time
		 */

		begin = System.nanoTime();
		Sorting.quickSort(quickObj);
		end = System.nanoTime();
		timeObj[4] = end - begin;
		quickTime = end - begin;	
		quickCount = quickObj[elementNum - 1].getCount();
		quickObj[elementNum - 1].setCount(0);	

		for(int i = 4;i >= 0; i--)
		{
			Sorting.quickSort(timeObj);
			if(timeObj[i] == bubbleTime)
			{
				String bubbleC = formatter.format(bubbleCount);
				endTime = formatter.format(bubbleTime);
				out.write("bubble \t\t"+ bubbleC + " \t\t" + endTime + "     \n");
			} 
			if(timeObj[i] == selectionTime)
			{
				String selectionC = formatter.format(selectionCount);
				endTime = formatter.format(selectionTime);
				out.write("selection \t" + selectionC + " \t\t" + endTime + "     \n");
			} 
			if(timeObj[i] == insertionTime)
			{
				String insertionC = formatter.format(insertionCount);
				endTime = formatter.format(insertionTime);
				out.write("insertion  \t"+ insertionC + " \t\t" + endTime + "     \n");
			} 
			if(timeObj[i] == mergeTime)
			{
				String mergeC = formatter.format(mergeCount);
				endTime = formatter.format(mergeTime);
				out.write("merge \t\t"+ mergeC + " \t\t" + endTime + "     \n");
			} 
			if(timeObj[i] == quickTime)
			{
				String quickC = formatter.format(quickCount);
				endTime = formatter.format(quickTime);
				out.write("quick \t\t"+ quickC + " \t\t" + endTime + "     \n");
			}
		}


		out.close();
	}
	/**
	 *	This method reads in the data from the URL and it ueses the "|" and new line
	 *	as the delimeters. 
	 */
	public static void getData() throws MalformedURLException, IOException
	{
		Scanner scan = new Scanner(new URL("http://s3.amazonaws.com/depasquale/datasets/namesWithBloodType.txt").openStream());
		
		for(int i = -1; scan.hasNext(); i++) //necessary to skip over header line 
		{
			scan.useDelimiter("\n|\\|");
						
			if(i == -1)
			{	
				String line = scan.nextLine();
			}
			else
			{		
				String givenName = scan.next();
				String surname = scan.next();
				String streetAddress = scan.next();
				String city = scan.next();
				String state = scan.next();
				String zip = scan.next();
				String blood = scan.next();
				String rhesus = "";
				
				if(blood.length() == 2)
				{
					char[] charArray = blood.toCharArray();
					blood = Character.toString(charArray[0]);
					rhesus = Character.toString(charArray[1]);	
				}
				if(blood.length() == 3)
				{
					char[] charArray = blood.toCharArray();
					blood = Character.toString(charArray[0]);
					blood = blood + Character.toString(charArray[1]);
					rhesus = Character.toString(charArray[2]);	
				}
				
				
				personObj[i] = new Person(givenName, surname, streetAddress, city, state, zip, blood, rhesus);
				
			}
		}
		scan.close();
	}

}