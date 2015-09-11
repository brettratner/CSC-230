/*
 * Names: Dylan Wulf, Brett Ratner
 * Class: CSC230-02
 * Assignment: Lab 4
 */
package edu.tcnj.csc230;
import jsjf.*;
import jsjf.exceptions.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class takes input from an input file (default name lab4.inp) 
 * and interprets the commands in that file. 
 */
public class InputProcessor{
    String filename;
    String defaultFilename = "lab4.inp";
    
    /**
     * Constructor with one argument that allows you to set the file name.
     * @param file String containing the file name
     */
    public InputProcessor(String file){
        filename = file;
    }

    /**
     * Constructor with no parameters, uses default file name lab4.inp
     */
    public InputProcessor(){
        filename = defaultFilename;
    }

    /**
     * Reads the input file one line at a time, one command per line, and
     * calls the corresponding method. Possible commands: 
     * enqueue <string>
     * dequque
     * size
     * toString
     * isEmpty
     * first
     */
    public void run() throws FileNotFoundException{
        Scanner inpScanner = new Scanner(new File(filename));
        CircularArrayQueue<String> strQueue = new CircularArrayQueue<String>();
        
        while (inpScanner.hasNextLine()){
            String thisLine = inpScanner.nextLine();
            String command = thisLine;
            String argument = "";

            if (thisLine.length() >= 7 && thisLine.substring(0, 7).equalsIgnoreCase("enqueue")){
                command = thisLine.substring(0, 7);
                argument = thisLine.substring(7, thisLine.length());
                if (!argument.isEmpty() && argument.charAt(0) == ' ')
                    argument = argument.substring(1, argument.length());
                strQueue.enqueue(argument);
                System.out.println("\"" + argument + "\" was enqueued.");
            }

            else if (command.equalsIgnoreCase("size"))
                System.out.println("The size of the queue is currently " + strQueue.size());

            else if (command.equalsIgnoreCase("isEmpty")){
                System.out.print("The queue is ");
                if (!strQueue.isEmpty())
                    System.out.print("not ");
                System.out.println("empty.");
            }

            else if (command.equalsIgnoreCase("dequeue")){
                try{
                    System.out.println(strQueue.dequeue());
                }
                catch (EmptyCollectionException e){
                    System.out.println("Nothing to dequeue.");
                }
            }

            else if (command.equalsIgnoreCase("first")){
                if (!strQueue.isEmpty()){
                    System.out.println(strQueue.first());
                }
                else
                    System.out.println("Nothing to peek.");
            }

            else if (command.equalsIgnoreCase("toString"))
                System.out.println(strQueue);

            else
                System.out.println("Unrecognized command: \"" + command + "\"");

        }
        inpScanner.close();
    }
}
