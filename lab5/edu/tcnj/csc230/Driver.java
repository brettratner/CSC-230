/** Brett Ratner and Ricky Zhao
  *	CSC230-02
  *	Lab5
*/

package edu.tcnj.csc230;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * This method calls the run method in processor.java and than 
 * the run method will execute everything.
 */
public class Driver
{
	/**
	*	
	*/
	public static void main(String [] args)  throws MalformedURLException, IOException
	{	
		Processor processingObj = new Processor();
		processingObj.run();	
	}
}