/*
 * Names: Dylan Wulf, Brett Ratner
 * Class: CSC230-02
 * Assignment: Lab 4
 */
package edu.tcnj.csc230;
import java.io.FileNotFoundException;

/**
 * This class instantiates an InputProcessor object and calls the run() method.
 */
public class Driver{
    public static void main(String[] args){
        InputProcessor processor = new InputProcessor();
        try{
            processor.run();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found :(");
        }
    }
}
