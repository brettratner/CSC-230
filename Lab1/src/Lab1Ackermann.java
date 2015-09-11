/*
 * Names: Dylan Wulf, Brett Ratner, Sam Mills
 * Class: CSC230-02
 * Assignment: Lab1, part 2
 */

import java.util.Scanner;

/**
 * This class contains the main method for this program, which takes two input
 * integers and produces an output using the Ackermann function.
 * 
 * @author Dylan Wulf, Brett Ratner, Sam Mills
 *
 */
public class Lab1Ackermann {

    /**
     * This main method takes two integers, input by the user, and passes them to the 
     * A(int, int) to produce a result based on the Ackermann function.
     * 
     * @param args Program arguments
     */
    public static void main(String[] args) {
        Scanner userIn = new Scanner(System.in);
        int m;
        int n;
        System.out.println("Input m: ");
        m = userIn.nextInt();
        System.out.println("Input n: ");
        n = userIn.nextInt();
        userIn.close();
        System.out.println(A(m, n));
    }

    /**
     * This method takes two ints as input and one int as output based on the 
     * Ackermann function, which it calculates using recursion.
     * 
     * @param m First integer input
     * @param n Second integer input
     * @return Integer, the output of the Ackermann function.
     */
    public static int A(int m, int n) {
        if (m == 0)
            return n + 1;
        else if (n == 0 && m > 0) {
            return A(m - 1, 1);
        } else if (n > 0 && m > 0) {
            return A(m - 1, A(m, n - 1));
        } else
            return -1;
    }
}
