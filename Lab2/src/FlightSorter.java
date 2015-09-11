/*
  Names: Dylan Wulf, Brett Ratner, Sam Mills
  Class: CSC230-02
  Assignment: Lab2
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class FlightSorter {

    /**
     * Sorts a list of flights located in sampleflights.txt alphabetically, prints them out, and then searches for a flight 
     * with the source city "Jinzhou (ZYJZ / JNZ)" and prints its location in the list.
     * 
     * @param args Program arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException{
        Flight[] flightArray = new Flight[50];
        Scanner flightScanner = new Scanner(new File("sampleflights.txt"));
        flightScanner.useDelimiter("\t|\n|\r\n");
        for (int i = 0; i < flightArray.length; i++){
            flightArray[i] = new Flight(flightScanner.next(), flightScanner.next(), flightScanner.next(), flightScanner.next(), 
                    flightScanner.next(), flightScanner.next(), flightScanner.next());
        }
        bidirectionalBubbleSort(flightArray);
        for (int i = 0; i < flightArray.length; i++){
            System.out.println("[" + i + "]");
            System.out.println(flightArray[i] + "\n");
        }
        System.out.println("\nSearching for flight: Jinzhou (ZYJZ / JNZ) to Shanghai Pudong Int'l (ZSPD / PVG)...");
        System.out.println("Position of flight: " + linearSearch(flightArray, new Flight("Jinzhou (ZYJZ / JNZ)", "Shanghai Pudong Int'l (ZSPD / PVG)")));
        flightScanner.close();

    }
    
    /**
     * Uses linear search algorithm to find the first instance of target in data[] and returns its location in data[]
     * @param data An array of objects to be searched
     * @param target The target object you are searching for
     * @return The index of the first instance in data[] matching target, or -1 if not found.
     */
    public static <T extends Comparable<T>> int linearSearch(T[] data, T target){
        for (int i = 0; i < data.length; i++){
            if (target.compareTo(data[i]) == 0){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Uses bidirectional bubble sort algorithm to sort data[]
     * @param data The sorted array
     */
    public static <T extends Comparable<T>> void bidirectionalBubbleSort(T[] data){
        int left = 0;
        int right = data.length - 1;
        while (left < right){
            for (int pos = left; pos < right; pos++){
                if (data[pos].compareTo(data[pos + 1]) > 0){
                    swap(data, pos, pos + 1);
                }
            }
            right--;
            
            for (int pos = right; pos > left; pos--){
                if (data[pos].compareTo(data[pos - 1]) < 1){
                    swap(data, pos, pos - 1);
                }
            }
            left++;
        }
    }
    
    /**
     * Swaps two elements in an array
     * @param data Array containing the two elements to be swapped
     * @param index1 First element to be swapped with the second element
     * @param index2 Second element to be swapped with the first element
     */
    public static <T extends Comparable<T>> void swap(T[] data, int index1, int index2){
        T temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

}
