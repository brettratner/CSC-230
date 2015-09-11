/*
 * Names: Dylan Wulf, Brett Ratner, Sam Mills
 * Class: CSC230-02
 * Assignment: Lab1, part 1
 */

import java.util.Scanner;

/**
 * This class contains the main method for this program, which converts any 
 * base ten number to any base between 2 and 36 (inclusive), taking input 
 * from the standard input. 
 * 
 * @author Dylan Wulf, Brett Ratner, Sam Mills
 * 
 */
public class Lab1NumberBase {
    
    /**
     * This main method takes the user's input and calls the other methods to 
     * convert the number to another base.
     * 
     * @param args The program arguments
     */
    public static void main(String[] args) {
        Scanner userIn = new Scanner(System.in);
        int num;
        byte base;
        System.out.println("N base 10: ");
        num = userIn.nextInt();
        System.out.println("New base: ");
        base = userIn.nextByte();
        userIn.close();
        if (base >= 2 && base <= 36)
            System.out.println(decimalToBase(base, num));
        else
            System.out.println("Invalid base.");
    }

    /**
     * This method takes a number in base ten and converts it to another base, 
     * returning it as a String.
     * 
     * @param newBase The base to convert to (between 2 and 36, inclusive)
     * @param n The number in base ten to be converted to newBase
     * @return String containing the number in the new base
     */
    public static String decimalToBase(byte newBase, int n) {
        int quotient = n / newBase;
        int remainder = n % newBase;
        String remStr = Character.toString(intToChar(remainder));
        if (quotient == 0)
            return remStr;
        else
            return decimalToBase(newBase, quotient) + remStr;
    }

    /**
     * This method takes in an integer and converts it to a character
     * so that it can be represented as a single digit.
     * 
     * @param in The integer to be converted to a character
     * @return Character representing the integer
     */
    public static char intToChar(int in) {
        if (in >= 0 && in <= 9)
            return (char) (in + 48);
        else
            return (char) (in + 55);
    }
}
