/** Brett Ratner and Ricky Zhao
  *	CSC230-02
  *	Lab5
*/

/** 
 * This program reads from a url and takes in the iTunes track data source
 * After it than creates 3 hashtables that are 3 different sizes. the first
 * hashtable(hashTable1) has a size of 10, the second hashtable(hashTable2)
 * has the size of 100, the third hashtable(hashTable3) has a size of 1000.
 * Than it makes a table in the draw method and ouputs it to the terminal.
 * this table has the size of the hashTable, displays either a yes(Y) or a
 * no(N) if the string "Domino College" is there, and the number of collisions
 * that occured.
 */

package edu.tcnj.csc230;

import jsjf.*;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;


public class Processor
{

	
	private HashTable hashTable1 = new HashTable(10);
	private HashTable hashTable2 = new HashTable(100);
	private HashTable hashTable3 = new HashTable(1000);
	
	/**
	 * This method calls the getData() and the draw() methods
	 */
	public void run() throws MalformedURLException, IOException
	{
		
		getData();
		draw();
		
	}
	
	/**
	 * This method is doing the reading from the url and than puts the iTune
	 * objects into the hashTable.	
	 */
	public void getData() throws MalformedURLException, IOException
	{
		Scanner scan = new Scanner(new URL("http://s3.amazonaws.com/depasquale/datasets/itunes.txt").openStream());
		
		for(int i = -1; scan.hasNext(); i++) //necessary to skip over header line 
		{
			String currentLine = scan.nextLine();
			
			String[] pieces = currentLine.split("\t");
			
			if(i == -1)
			{	
			}
			else
			{		
				String song = pieces[0];
				String artist = pieces[1];
				String album = pieces[2];
				long size = Long.parseLong(pieces[4]);
				int time = Integer.parseInt(pieces[5]);
				
				
				iTunes iTuneObject = new iTunes(song, artist, album, size, time);

				hashTable1.add(iTuneObject);
				hashTable2.add(iTuneObject);
				hashTable3.add(iTuneObject);
			}
		}
		scan.close();
	}
	
	/**
	 * This method outputs the table to the terminal and in the table
	 * it has the size of the table and if it contains the string 
	 * "Domino College" and the number of collisions.	
	 */
	public void draw()
	{
		
		String hashTable1Cont = "N";
		String hashTable2Cont = "N";
		String hashTable3Cont = "N";
		if(hashTable1.contains("Domino College"))
			hashTable1Cont = "Y";
		if(hashTable2.contains("Domino College"))
			hashTable2Cont = "Y";
		if(hashTable3.contains("Domino College"))
			hashTable3Cont = "Y";
		
		System.out.format("+---------------+----------+--------------+\n");
		System.out.printf("| Size of Table | Contains | # Collisions |\n");
		
		System.out.format("+---------------+----------+--------------+\n");
		System.out.printf("|      10       |    " + hashTable1Cont + "     |     "+ hashTable1.collisions() + "     |\n");
		
		System.out.format("+---------------+----------+--------------+\n");
		System.out.printf("|      100      |    "+ hashTable2Cont + "     |     " +hashTable2.collisions() + "     |\n");
		

		System.out.format("+---------------+----------+--------------+\n");	
		System.out.printf("|      1000     |    " + hashTable3Cont + "     |     "+ hashTable3.collisions() + "     |\n");
		System.out.format("+---------------+----------+--------------+\n");	
		
	}
}
