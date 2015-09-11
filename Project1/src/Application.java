/*
 * Names: Dylan Wulf, Brett Ratner
 * Class: CSC230-02
 * Assignment: Project 1 (Zip Codes)
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * This is the driver class for this program. It uses searching and 
 * sorting to answer the following questions in an output file named zipout.txt.
 * 
 * List the towns in Rhode Island (RI) in ascending alphabetical order.
 * Provide a unique list (no duplicates) of all of the counties to which the USPS delivers.
 * List all of the locations (city, state), which have the same lat/lon but different zip codes. What are their zip codes?
 * What are all of the different types of postal code types (last column) and how many of each exist?
 * How many total counties does the USPS deliver to?
 * List all of the zip codes whose postal location are either named Springfield or have Springfield in their name (e.g. West Springfield).
 * 
 * @author Dylan Wulf, Brett Ratner
 *
 */
public class Application {
    static int howManyStandard = 0;
    static int howManyUnique = 0;
    static int howManyPO = 0;
    static int howManyMilitary = 0;
    static int howManySpringfield = 0;
    
    /**
     * Main method of this program
     * 
     * @param args Command line arguments for this program
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter("zipout.txt", "UTF-8");
        
        //Getting all the info from the text file into an array of ZipCodeLocation objects
        ZipCodeLocation[] zipArray = getInfoFromFile("zipcodes.txt");
        zipArray = removeNullsAtEnd(zipArray);
        
        //First we sort the list alphabetically by state, then county name, then town name.
        mergeSortBy(zipArray, 0, zipArray.length - 1, 0);
        
        //Finding all towns in RI and sorting them alphabetically, then writing them to zipout.txt
        writer.println("Towns in RI in alphabetical order: ");
        ZipCodeLocation[] RIArray = findAndSortRhodeIslandZips(zipArray);
        for (ZipCodeLocation o : RIArray){
            writer.println(o.getTown());
        }
        writer.println();
        
        //Next we will list all the unique counties the USPS delivers to.
        //We make a new array to hold the unique counties.
        //They are stored as indices of zipArray[]
        //Then we write them to zipout.txt
        int[] uniqueCountiesIndices = findUniqueCounties(zipArray);
        writer.println("List of unique counties (" + uniqueCountiesIndices.length + " total): ");
        for (int o : uniqueCountiesIndices){
            writer.println(zipArray[o].getCounty() + ", " + zipArray[o].getState());
        }
        
        //Sort the zip array by latitude and longitude because it's required for findDuplicateLatLong3 method.
        //Then write the list to zipout.txt
        mergeSortBy(zipArray, 0, zipArray.length - 1, 2);
        int[] duplicateLatLongIndices = findDuplicateLatLong3(zipArray);
        writer.println("\nList of duplicate longitude/latitudes (" + duplicateLatLongIndices.length + " total): ");
        for (int o : duplicateLatLongIndices){
            writer.println(zipArray[o]);
        }
        
        //Writing answers to the other questions to zipout.txt
        writer.println("\nTypes of postal codes and how many of each type: ");
        writer.println("Standard: " + howManyStandard + " Unique: " + howManyUnique + " PO: " + howManyPO + " Military: " + howManyMilitary);
        writer.println("\nHow many counties the USPS delivers to: " + uniqueCountiesIndices.length);
        writer.println("\nList of Springfield zip codes (" + howManySpringfield + " total): ");
        int[] springfieldIndices = findSpringfields(zipArray);
        for (int o : springfieldIndices){
            writer.println(zipArray[o].getZipCode() + " " + zipArray[o].getTown());
        }
        
        //Close the file writer because we're done writing to the file
        writer.close();
    }
    
    /**
     * Finds all the ZipCodeLocation objects which have springfield in their town/county name
     * @param zipArray Array containing all the ZipCodeLocation objects
     * @return integer array containing indices of zipArray that have springfield in the name
     */
    public static int[] findSpringfields(ZipCodeLocation[] zipArray){
        int[] springfieldIndices = new int[howManySpringfield];
        int springfieldIndicesCounter = 0;
        for (int i = 0; i < zipArray.length; i++){
            if (zipArray[i].getTown().contains("SPRINGFIELD") || zipArray[i].getCounty().contains("SPRINGFIELD")){
                springfieldIndices[springfieldIndicesCounter] = i;
                springfieldIndicesCounter++;
            }
        }
        return springfieldIndices;
    }
    
    /**
     * Takes the entire array of zip codes and finds zips where the lat/long are the same
     * then returns an array of indices for zipArray of the duplicates.
     * Assumes that the array is already sorted by lat/long.
     * This method correctly finds all the lat/long duplicates and is the most efficient way
     * we found so far.
     * 
     * @param zipArray Array containing all the ZipCodeLocation objects, sorted by latitude and longitude
     * @return integer array containing indices of zipArray which have duplicate latitude/longitude
     */
    public static int[] findDuplicateLatLong3(ZipCodeLocation[] zipArray){
        //This array just holds information about which zip codes have already been checked
        //and which haven't.
        boolean[] zipArrayIndicesChecked = new boolean[zipArray.length];
        for (int i = 0; i < zipArrayIndicesChecked.length; i++){
            zipArrayIndicesChecked[i] = false;
        }
        
        //This array will hold indices of duplicates.
        int[] duplicateLatLongIndices = new int[zipArray.length];
        int currentDuplicatesIndex = 0;
        
        //Go through the entire zipArray once, storing indices in duplicateLatLongIndices[]
        //if there is more than one in a row with the same latitude/longitude
        for (int i = 0; i < zipArray.length; i++){
            if (i < zipArray.length - 1 && !zipArrayIndicesChecked[i] && zipArray[i].compareByLatLong(zipArray[i + 1]) == 0){
                duplicateLatLongIndices[currentDuplicatesIndex] = i;
                currentDuplicatesIndex++;
            }
            while (i < zipArray.length - 1 && !zipArrayIndicesChecked[i] && zipArray[i].compareByLatLong(zipArray[i + 1]) == 0){
                duplicateLatLongIndices[currentDuplicatesIndex] = i + 1;
                currentDuplicatesIndex++;
                zipArrayIndicesChecked[i] = true;
                i++;
            }
        }
        duplicateLatLongIndices = resize(duplicateLatLongIndices, currentDuplicatesIndex);
        return duplicateLatLongIndices;
    }
    
    /**
     * This method correctly finds the duplicate lat/long zip codes, but
     * is fairly inefficient. We found a better way to do this so this method is not used.
     * 
     * @param zipArray array containing all ZipCodeLocation objects
     * @return int array containing indices of duplicate latitudes/longitudes
     */
    public static int[] findDuplicateLatLong2(ZipCodeLocation[] zipArray){
        int[] zipArrayIndices = new int[zipArray.length];
        for (int i = 0; i < zipArrayIndices.length; i++){
            zipArrayIndices[i] = i;
        }
        int[] duplicateLatLongIndices = new int[zipArray.length];
        int currentLatLongIndex = 0;
        for (int i = 0; i < zipArrayIndices.length; i++){
            boolean alreadyFoundADuplicate = false;
            for (int j = 0; j < zipArrayIndices.length; j++){
                if (i != j && zipArrayIndices[i] != -1 && zipArrayIndices[j] != -1 
                        && Math.abs(zipArray[i].getLatitude() - zipArray[j].getLatitude()) < 0.000001 
                        && Math.abs(zipArray[i].getLongitude() - zipArray[j].getLongitude()) < 0.000001){
                    if (!alreadyFoundADuplicate){
                        duplicateLatLongIndices[currentLatLongIndex] = i;
                        currentLatLongIndex++;
                        alreadyFoundADuplicate = true;
                    }
                    duplicateLatLongIndices[currentLatLongIndex] = j;
                    currentLatLongIndex++;
                    zipArrayIndices[j] = -1;
                }
            }
            zipArrayIndices[i] = -1;
        }
        int newDuplicatesArraySize = currentLatLongIndex;
        duplicateLatLongIndices = resize(duplicateLatLongIndices, newDuplicatesArraySize);
        return duplicateLatLongIndices;
    }
    
    /**
     * This method only works correctly if there are no lat/long duplicates in different counties.
     * we assumed this was true at first, but later found out this is false in a couple cases.
     * We found a better way to do this so this method is not used.
     * 
     * @param zipArray array containing all ZipCodeLocation objects, sorted by state then by county.
     * @param uniqueCountiesIndices int array containing indices of unique counties
     * @return int array containing indices of objects with equivalent latitude/longitude
     */
    public static int[] findDuplicateLatLong(ZipCodeLocation[] zipArray, int[] uniqueCountiesIndices){
        int[] zipArrayIndices = new int[zipArray.length];
        for (int i = 0; i < zipArrayIndices.length; i++){
            zipArrayIndices[i] = i;
        }
        int[] duplicateLatLongIndices = new int[zipArray.length];
        int currentLatLongIndex = 0;
        int currentCountyIndex = 0;
        int currentZipArrayIndex = 0;
        while (currentCountyIndex < uniqueCountiesIndices.length){
            boolean alreadyFoundADuplicate = false;
            while (currentCountyIndex < uniqueCountiesIndices.length - 1 && 
                    currentZipArrayIndex < uniqueCountiesIndices[currentCountyIndex + 1]){
                int currentZipArrayIndexBeingChecked = 0;
                while (currentZipArrayIndexBeingChecked < uniqueCountiesIndices[currentCountyIndex + 1]){
                    if (currentZipArrayIndex != currentZipArrayIndexBeingChecked && zipArrayIndices[currentZipArrayIndexBeingChecked] != -1 && 
                            zipArrayIndices[currentZipArrayIndex] != -1 && 
                            Math.abs(zipArray[currentZipArrayIndex].getLatitude() 
                                    - zipArray[currentZipArrayIndexBeingChecked].getLatitude()) < 0.000001
                            && Math.abs(zipArray[currentZipArrayIndex].getLongitude() 
                                    - zipArray[currentZipArrayIndexBeingChecked].getLongitude()) < 0.000001){
                        if (!alreadyFoundADuplicate){
                            duplicateLatLongIndices[currentLatLongIndex] = currentZipArrayIndex;
                            alreadyFoundADuplicate = true;
                            currentLatLongIndex++;
                        }
                        duplicateLatLongIndices[currentLatLongIndex] = currentZipArrayIndexBeingChecked;
                        currentLatLongIndex++;
                        zipArrayIndices[currentZipArrayIndexBeingChecked] = -1;
                    }
                    currentZipArrayIndexBeingChecked++;
                }
                zipArrayIndices[currentZipArrayIndex] = -1;
                currentZipArrayIndex++;
            }
            currentCountyIndex++;
        }
        int newDuplicatesArraySize = currentLatLongIndex;
        duplicateLatLongIndices = resize(duplicateLatLongIndices, newDuplicatesArraySize);
        return duplicateLatLongIndices;
    }
    
    /**
     * Finds all the unique counties in zipArray
     * 
     * @param zipArray array containing all the ZipCodeLocation objects, sorted 
     *        alphabetically by state then county.
     * @return integer array containing indices which all have unique counties.
     */
    public static int[] findUniqueCounties(ZipCodeLocation[] zipArray){
        int[] uniqueCountiesIndices = new int[zipArray.length];
        uniqueCountiesIndices[0] = 0;
        int currentCountyIndex = 0;
        for (int i = 0; i < zipArray.length; i++){
            if (!zipArray[i].getCounty().equals(zipArray[uniqueCountiesIndices[currentCountyIndex]].getCounty())){
                currentCountyIndex++;
                uniqueCountiesIndices[currentCountyIndex] = i;
            }
        }
        int howManyUniqueCounties = currentCountyIndex + 1;
        uniqueCountiesIndices = resize(uniqueCountiesIndices, howManyUniqueCounties);
        return uniqueCountiesIndices;
    }
    
    /**
     * Finds all the towns in Rhode Island and puts them in alphabetical order.
     * 
     * @param allZipCodes array containing all ZipCodeLocation objects, sorted alphabetically by state.
     * @return ZipCodeLocation array containing objects which have the the towns in RI listed alphabetically.
     */
    public static ZipCodeLocation[] findAndSortRhodeIslandZips(ZipCodeLocation[] allZipCodes){
        
        //First find out how many RI zip codes there are
        //Since the array has been sorted by state, we know all the RI zips will be 
        //grouped together, and we can exit the loop once we reach the end of RI zips
        int howManyRI = 0;
        boolean foundRI = false;
        int firstRIIndex = 0;
        for (int i = 0; i < allZipCodes.length; i++){
            if (allZipCodes[i] != null && allZipCodes[i].getState().equals("RI")){
                howManyRI++;
                if (!foundRI){ 
                    foundRI = true;
                    firstRIIndex = i;
                }
            }
            else if (allZipCodes[i] == null || (foundRI && !allZipCodes[i].getState().equals("RI")))
                i = allZipCodes.length;
        }
        //Then make an array of that size
        ZipCodeLocation[] RIArray = new ZipCodeLocation[howManyRI];
        
        //Then take all the RI zip codes in the list and put them in a separate array
        for (int i = 0; i < RIArray.length; i++){
                RIArray[i] = allZipCodes[i + firstRIIndex];
        }
        //Then sort them so the towns are in alphabetical order
        mergeSortBy(RIArray, 0, RIArray.length - 1, 1);
        return RIArray;
    }

    /**
     * This method parses the information from the input text file. 
     * 
     * @param filename String: the name of the file to take the input from
     * @return array containing all valid ZipCodeLocation objects
     * @throws FileNotFoundException
     */
    public static ZipCodeLocation[] getInfoFromFile(String filename) throws FileNotFoundException{
        File zipCodes = new File(filename);
        Scanner fileLineScanner = new Scanner(zipCodes);
        int howManyLines = 0;
        while (fileLineScanner.hasNextLine()){
            howManyLines++;
            fileLineScanner.nextLine();
        }
        fileLineScanner.close();
        ZipCodeLocation[] zipCodeArray = new ZipCodeLocation[howManyLines];
        Scanner fileScanner = new Scanner(zipCodes);
        fileScanner.useDelimiter(",|\n");
        int index = 0;
        while (fileScanner.hasNext()){
            String zip = fileScanner.next();
            String latStr = fileScanner.next();
            String longStr = fileScanner.next();
            String town = fileScanner.next();
            String state = fileScanner.next();
            String county = fileScanner.next();
            String type = fileScanner.next();
            if (!(zip.isEmpty() || latStr.isEmpty() || longStr.isEmpty() || 
                town.isEmpty() || state.isEmpty() || county.isEmpty() || type.isEmpty())){
                zip = zip.replaceAll("\"", "");
                latStr = latStr.replaceAll("\"", "");
                longStr = longStr.replaceAll("\"", "");
                town = town.replaceAll("\"", "");
                state = state.replaceAll("\"", "");
                county = county.replaceAll("\"", "");
                type = type.replaceAll("\"", "").replaceAll("\r", "");
                double latitude = Double.parseDouble(latStr);
                double longitude = Double.parseDouble(longStr);
                zipCodeArray[index] = new ZipCodeLocation(zip, latitude, longitude, town, state, county, type);
                index++;
            }
            //Counting different types of postal codes and how many have springfield in the name
            if (type.contains("STANDARD"))
                howManyStandard++;
            if (type.contains("UNIQUE"))
                howManyUnique++;
            if (type.contains("PO BOX ONLY"))
                howManyPO++;
            if (type.contains("MILITARY"))
                howManyMilitary++;
            if (town.contains("SPRINGFIELD") || county.contains("SPRINGFIELD"))
                howManySpringfield++;
        }
        fileScanner.close();
        return zipCodeArray;
        
    }
    
    /**
     * Copies an array into another array with a new size. 
     * 
     * @param array Input array to be resized
     * @param newSize New size of the array
     * @return pointer to array with new size
     */
    public static int[] resize(int[] array, int newSize){
        int[] temp = new int[newSize];
        for (int i = 0; i < temp.length; i++){
            temp[i] = array[i];
        }
        return temp;
    }
    
    /**
     * Removes the null pointers at the end of the array containing all ZipCodeLocation
     * objects. 
     * 
     * @param array array containing all ZipCodeLocation objects
     * @return pointer to resized array
     */
    public static ZipCodeLocation[] removeNullsAtEnd(ZipCodeLocation[] array){
        int lastNonNullIndex = array.length - 1;
        for (int i = array.length - 1; i >= 0; i--){
            if (array[i] != null){
                lastNonNullIndex = i;
                i = -1;
            }
        }
        ZipCodeLocation[] temp = new ZipCodeLocation[lastNonNullIndex + 1];
        for (int i = 0; i < temp.length; i++){
            temp[i] = array[i];
        }
        return temp;
    }
    
    
    /**
     * Sorts according to the variable howToSort. 
     * 0 sorts alphabetically by state, then county, then town name.
     * 1 sorts alphabetically by state, then town, then county.
     * 2 sorts by latitude, then longitude.
     * 
     * @param data array with data to be sorted
     * @param min index of beginning of section of data[] to be sorted
     * @param max index of end of section of data[] to be sorted
     * @param howToSort what to sort the array by
     */
    public static void mergeSortBy(ZipCodeLocation[] data, int min, int max, int howToSort) {
        if (min < max) {
            int mid = (min + max) / 2; 
            mergeSortBy(data, min, mid, howToSort); 
            mergeSortBy(data, mid+1, max, howToSort); 
            mergeBy(data, min, mid, max, howToSort); 
        } 
    }
    
    /**
     * Merges two groups of objects putting the elements in order. 
     * 
     * @param data array containing all the objects
     * @param first index of beginning of data[] to be sorted
     * @param mid index of middle of data[] to be sorted
     * @param last index of end of data[] to be sorted
     * @param howToSort what to sort the array by (see mergeSortBy())
     */
    public static void mergeBy(ZipCodeLocation[] data, int first, int mid, int last, int howToSort) { 
        ZipCodeLocation[] temp = new ZipCodeLocation[data.length]; 
        int first1 = first, last1 = mid; 
        // endpoints of first subarray 
        int first2 = mid+1, last2 = last; 
        // endpoints of second subarray
        int index = first1; 
        // next index open in temp array 
        // Copy smaller item from each subarray into temp until one
        // of the subarrays is exhausted 
        while (first1 <= last1 && first2 <= last2) {
            int comparison = 1;
            if (data[first1] != null && data[first2] != null){
                switch(howToSort){
                    case 0: 
                        comparison = data[first1].compareTo(data[first2]);
                        break;
                    case 1:
                        comparison = data[first1].compareByTown(data[first2]);
                        break;
                    case 2:
                        comparison = data[first1].compareByLatLong(data[first2]);
                }
            }
            if (comparison < 0) { 
                temp[index] = data[first1]; 
                first1++; 
            }
            else { 
                temp[index] = data[first2]; 
                first2++; 
            } 
            index++; 
        } 
        // Copy remaining elements from first subarray, if any 
        while (first1 <= last1) { 
            temp[index] = data[first1]; 
            first1++; 
            index++; 
        } 
        // Copy remaining elements from second subarray, if any 
        while (first2 <= last2) { 
            temp[index] = data[first2]; 
            first2++; 
            index++; 
        }
        // Copy merged data into original array 
        for (index = first; index <= last; index++) 
            data[index] = temp[index]; 
    }
    
}
