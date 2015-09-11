/**
  *Brett Ratner
  *CSC230-02
  *Project03
  */

package edu.tcnj.csc230;

public class HTTPLog implements Comparable
{
    
    private String resource;
    private String logDate;
    private String status;
    private int numBytes;
    private int count;
    private static int hashModSize;
     /**
      * first contructor with paramters
      */
    public HTTPLog(  String resource
               , String logDate
               , String status
               , int numBytes)
    {
            this.resource = resource;
            this.logDate = logDate;
            this.status = status;
            this.numBytes = numBytes;
            this.count = 1;
    }

     /**
      *second contructor with no paramerters
      */
    
    public HTTPLog()
    {
    }
     /**
     *third contrctor with a parameter
     */
    public HTTPLog(HTTPLog x) {
        this.resource = x.getResource();
        this.status = x.getStatus();
        this.logDate = x.getLogDate();
        this.numBytes = x.getNumBytes();
        this.count = x.getCount();
    }
     /**  
     * this method returns the resource 
     */

    public String getResource(){
        return this.resource;
    }
    
     /**  
     * this method returns the log date
     */
    public String getLogDate(){
        return this.logDate;
    }
    
     /**  
     * this method returns the status 
     */
    public String getStatus(){
        return this.status;
    }
    
     /**  
     * this method returns the number of bytes 
     */
    public int getNumBytes(){
        return this.numBytes;
    }
    /**  
     * this method returns the count 
     */
    public int getCount(){
        return this.count;
    }
    /**  
     * this method returns the size of the hashMod  
     */
    public static int getHashModSize(){
        return HTTPLog.hashModSize;
    }
    /**
     * sets the resource to the global varaible resource  
     */


    public void setResource(String resource){
     
     this.resource = resource;
    }

    /**
     * sets the log date to the global varaible log date  
     */

    public void setLogDate(String logDate){
        this.logDate = logDate;
    }

    /**
     * sets the status to the global varaible status  
     */

    public void setStatus(String status){
        this.status = status;
    }
    /**
     * sets the number of bytes to the global varaible number of bytes  
     */
    public void setNumBytes(int numBytes){
        this.numBytes = numBytes;
    }
    /**
     * sets the count to the global varaible count
     */
    
    public void setCount(int count){
        this.count = count;
    }
    /**
     * sets the hash mode size to the http hash mode size
     */
    public static void setHashModSize(int hashModSize){
        HTTPLog.hashModSize = hashModSize;
    }

    public int compareTo(Object resource){
        //System.out.println("In compareTo");
        int retVal=0;
        if (resource instanceof HTTPLog) {
            //System.out.println("Is InstanceOf");
            if ((((HTTPLog)resource).getResource().compareToIgnoreCase(this.resource)) > 0){
                retVal =  -1;
            }
            else if((((HTTPLog)resource).getResource().compareToIgnoreCase(this.resource)) < 0){
                retVal =  1;
            }
        }
        if (resource instanceof String) {
            if ((((String)resource).compareToIgnoreCase(this.resource)) > 0){
                retVal =  -1;
            }
            else if((((String)resource).compareToIgnoreCase(this.resource)) < 0){
                retVal =  1;
            }
        }
        //System.out.println("retVal="+retVal);
        return retVal;
    }
    
    public boolean equals (Object resource) {
        return (compareTo(resource) == 0);
    }
    
    public void update(HTTPLog newLog){
        //System.out.println("In update.");
        this.logDate = newLog.getLogDate();
        this.status  = newLog.getStatus();
        this.numBytes += newLog.getNumBytes();
        this.count += newLog.getCount();
    }

    public String toString(){
        return "Date and Time: " + this.logDate + " | resource: " + this.resource + " | status: " + this.status + " | numBytes: " + this.numBytes + " | count: " + this.count;
    }
    
    public int hashCode() {
        //System.out.println("Here in hashCode.");
            int len = resource.length();
            int tmpInt=0;
            String HashValue = "";
            
            try {
            
            for (int i=0; i < len; i++) {
                tmpInt += (int)resource.charAt(i);
            }
            
            tmpInt /= 7;
            tmpInt += 1855;
            
            
            String tmpHash = (new Integer(tmpInt)).toString();
            int midPos = tmpHash.length()/2;
            if (tmpHash.length() > 3) {
                HashValue = tmpHash.substring(midPos-1, 3);
            } else {
                HashValue = tmpHash;
            }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return ( new Integer(HashValue).intValue() % 75);
        }
}