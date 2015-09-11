

package edu.tcnj.csc230;

import java.io.*;
import java.util.Scanner;
import jsjf.LinkedChainedHashtableADT;
import java.util.Iterator;

/**
 * This program reads a file called access_log.inp and it allows the user to find 
 * a specific resource by by typing the word detail and than one of the resources.
 * than you can type the word topten and get a list of the top ten compared 
 * by thier resources and displays the appropraite information. than you can type
 * the word chain and get what bucket is the longest and than tells you how many
 * elememtns are in that linked List and than displays a list of all those elements. 
 * Or you can type in the word quit and quit the program. 
 */

public class Driver
{

    static String logFile = "access_log.inp";
    static boolean leave = false;
    
    static String logDate;
    static String logResource;
    static String logStatus;
    static String logNumBytes;
    static String logLine;
    static LinkedChainedHashtableADT HTTPLogHashTable;
    static HTTPLog curHTTPLog = new HTTPLog();
    static HTTPLog[] toptenList = new HTTPLog[10];
    static final int myHashSize = 75;
    

	public static void main(String[] args) {
        boolean leave = false;
        String userCmd;
        BufferedReader cmdReader = new BufferedReader(new InputStreamReader(System.in));
        HTTPLog.setHashModSize(myHashSize);
        HTTPLogHashTable = new LinkedChainedHashtableADT(Driver.myHashSize);
        loadLogFile();        
        
        do {
            try {
                System.out.println("Enter Command <Detail <resource>, Topten, Chain, Quit>: ");
                userCmd = cmdReader.readLine();
                System.out.println("Thank you for your command: " + userCmd);
                
                if (userCmd.toUpperCase().contains("DETAIL")) {
                    String tmpResource = userCmd.substring(7);
                    
                    curHTTPLog.setResource(tmpResource);
                    
                    
                    HTTPLog dtlLog = (HTTPLog)HTTPLogHashTable.find(curHTTPLog);
                    
                    if (dtlLog != null ) {
                        System.out.println(dtlLog.toString());
                    } else {
                        System.out.println(tmpResource + " was not found.");
                    }                    
                } else if (userCmd.toUpperCase().equals("TOPTEN")) {
    
                    doTopTen();
                    for (int i=0; i<10; i++) {
                        System.out.println(toptenList[i].toString());
                    }
                    leave = false;
                } else if (userCmd.toUpperCase().equals("CHAIN")) {
                        System.out.println("Longest Index: " + HTTPLogHashTable.getLongestList());
                        Iterator myList = HTTPLogHashTable.getChainIterator(HTTPLogHashTable.getLongestList());
                        System.out.println("index Size: " + HTTPLogHashTable.getLongestListSize());
                        while (myList.hasNext()){
                            System.out.println(myList.next().toString());
                        }
                    leave = false;
                } else if (userCmd.toUpperCase().equals("QUIT")) {
                    leave = true;
                } else {
                System.out.println("Excuse Me: " + userCmd + " not understood.");
                }
            
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
            }    
        } while (!leave);
    }
   /**
    *this method will load the access log file and callse the setters. 
    */
    private static void loadLogFile() {
        LogLoader ll = new LogLoader();
        String curLog;
        HTTPLog ht_HTTPLog;
        
        try {
            ll.initLogReader(logFile);

            while ((curLog = ll.nextLog()) != null) {
                parseLog(curLog);
                curHTTPLog.setResource(logResource);
                curHTTPLog.setLogDate(logDate);
                curHTTPLog.setNumBytes(new Integer(logNumBytes).intValue());
                curHTTPLog.setStatus(logStatus);
                curHTTPLog.setCount(1);
           
                if (HTTPLogHashTable.contains(curHTTPLog)) {
                    ht_HTTPLog = (HTTPLog) HTTPLogHashTable.find(curHTTPLog);
                    ht_HTTPLog.update(curHTTPLog);
                } else {
      
                    HTTPLogHashTable.addElement(new HTTPLog(curHTTPLog));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * uses the scanner and has different delimters for the different catagories
 * of the element and than if the resource is "/" it should change to inde.html
 * but I am having some troubble. 
 */
    private static void parseLog(String logLine) {
        try {
            String result= "";
            String turnToIndex = "";
            Scanner parseLine = new Scanner(logLine);
            parseLine.useDelimiter("\\[|\\]");
            parseLine.next();                   //Skip this item.
            logDate = parseLine.next();
            parseLine.useDelimiter(" ");
            parseLine.next();                   //Skip this item.
            parseLine.useDelimiter(" |\\?");
            parseLine.next();                   //Skip this item.
            logResource = parseLine.next();
             if (turnToIndex.contains("/"))
        {
            result = "/index.html";
        }
        
        else if (turnToIndex.contains("?"))
        {
            result = turnToIndex.substring(0, turnToIndex.indexOf("?"));
        }
        else
        {
            result = turnToIndex;
        }
            parseLine.useDelimiter("\"");
            parseLine.next();                   //Skip this item.
            parseLine.useDelimiter(" ");
            parseLine.next();                   //Skip this item.
            logStatus = parseLine.next();
            logNumBytes = parseLine.next();
       
        

            try {
                new Integer(logNumBytes).intValue();
            }
            catch(NumberFormatException i) {
                logNumBytes = "0";
            }
            parseLine.close();
            
           
            
            
        } catch (Exception e) {
            System.err.println( e.getMessage());
        }
    }
/**
 * This finds the top ten elements by looking at the number of collisions, 
 * the first for loop will find the top ten and the second for loop is there
 * to put it in decending order. 
 */
    private static void doTopTen () {
        Iterator<HTTPLog> list = HTTPLogHashTable.iterator();
        
        while (list.hasNext()) {
            
            curHTTPLog = list.next();
            //System.out.println("curHTTPLog: " + curHTTPLog.toString());
            for (int i=0; i < 10; i++) {
                if (toptenList[i] == null) {
                    //System.out.println("equals");
                    toptenList[i] = curHTTPLog;
                    break;
                } else if (curHTTPLog.getCount() >= toptenList[i].getCount()) {
                    for (int j=9; j > i; j--) {
                        toptenList[j] = toptenList[j-1];
                    }
                    //System.out.println("greaterequal");
                    toptenList[i] = curHTTPLog;
                    break;
                } else if (curHTTPLog.getCount() < toptenList[i].getCount()) {
                    //System.out.println("lessthan");
                    continue;
                }
            }
        }
      /*  for(HTTPLog list: HTTPLogHashTable)
        {
            System.out.println( obj.toString() + "\n";
        }*/
    }
     
	}