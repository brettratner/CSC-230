/**
 * Christian Hansen and Brett Ratner
 * CSC230-02
 * Lab03
 */

/**
 * This is the Driver class that goes with the LinkedStack class that creates a stack
 * using the LinkedStack class. It reads text from an .inp file as commands like a
 * program normally would from the terminal.
 */

import jsjf.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;
import java.io.IOException;


/**
 *	The Driver class is used to read commands from the .inp file using contains to look for key words.
 */
public class Driver extends LinkedStack<String>
{
	public static void main(String [] args) throws FileNotFoundException
	{
		LinkedStack<String> stackObject = new LinkedStack<String>();
        //try{
        
		Scanner fileScan = new Scanner(new File("lab3.inp"));
        
        while(fileScan.hasNext())
        {
            String line = "";
            
            line = fileScan.nextLine();
            
            //the if statments uses the command key words in lab3.inp to determine which method to run.
            if(line.contains("push"))
            {
                //satisfies the push parameter
                int index = line.indexOf(" ");
                int last = line.lastIndexOf("\"");
                
                //makes the string that will be in the stack.
                String element = line.substring(index + 2, last);
                stackObject.push(element);
            }
            if(line.contains("size"))
            {
                System.out.println("The size of the stack is currently: " + stackObject.size());
            }
            if(line.contains("isEmpty"))
            {
                if(stackObject.isEmpty() == true){
                    System.out.println("The stack is empty");
                }else{
                    System.out.println("The stack is not empty");
                    
                }
            }
            if(line.contains("pop"))
            {
                if(stackObject.isEmpty() == true){
                    System.out.println("Nothing to pop");
                }else{
                    System.out.println(stackObject.pop());
                }
            }
            if(line.contains("toString"))
            {
                System.out.println(stackObject.toString());
            }
            if(line.contains("peek"))
            {	
                if(stackObject.isEmpty() == true){
                    System.out.println("Nothing to peek");
                }else{
					System.out.println(stackObject.peek());		
                }
            }
        }
        
        //trying to catch an exeption incase push was called without a parameter but didnt work as we thought it should.		
		//}
        //	catch(IOException e){
        //		e.printStackTrace();
        //}
	}
}