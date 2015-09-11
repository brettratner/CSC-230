

package edu.tcnj.csc230;

import java.io.*;



public class LogLoader 
{
    private BufferedReader logReader;

    public void initLogReader(String logFile) throws IOException {
        logReader = new BufferedReader(new FileReader(logFile));
    }
    
    public String nextLog() throws IOException {
        return logReader.readLine();
    }
    
    public void closeReader() throws IOException {
        logReader.close();
    }
            
 }