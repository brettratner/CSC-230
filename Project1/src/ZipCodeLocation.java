/*
 * Names: Dylan Wulf, Brett Ratner
 * Class: CSC230-02
 * Assignment: Project 1 (Zip Codes)
 */

/**
 * Class to hold information for zip code objects
 * 
 * @author Dylan Wulf, Brett Ratner
 */
public class ZipCodeLocation implements Comparable<ZipCodeLocation> {
    String zipCode;
    double latitude;
    double longitude;
    String town;
    String state;
    String county;
    String type;
    
    /**
     * Constructor for the ZipCodeLocation class.
     * 
     * @param zip String: the zip code of this location
     * @param latitude double: the latitude of this location
     * @param longitude double: the longitude of this location
     * @param town String: the name of the town
     * @param state String: the name of the state or territory
     * @param county String: the name of the county
     * @param type String: the type of this postal code (available types: STANDARD, UNIQUE, PO BOX ONLY, MILITARY)
     */
    public ZipCodeLocation(String zip, double latitude, double longitude, String town, String state, String county, String type){
        this.zipCode = zip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.town = town;
        this.state = state;
        this.county = county;
        this.type = type;
    }
    
    /**
     * Compares two ZipCodeLocation objects alphabetically by state, then town, then county.
     * @param other The other object to be compared to this one
     * 
     * @return integer, < 0 if this object is less than other, 0 if this object is equal to other, 
     * or > 0 if this object is greater than other.
     */
    public int compareByTown(ZipCodeLocation other){
        if (state.compareTo(other.getState()) == 0){
            if (town.compareTo(other.getTown()) == 0){
                return county.compareTo(other.getCounty());
            }
            else return town.compareTo(other.getTown());
        }
        else return state.compareTo(other.getState());
    }
    
    /**
     * compareTo method from the Comparable interface. Compares two ZipCodeLocation objects
     * alphabetically by state, then county, then town. 
     * 
     * @return integer, < 0 if this object is less than other, 0 if this object is equal to other, 
     * or > 0 if this object is greater than other.
     */
    public int compareTo(ZipCodeLocation other){
        if (state.compareTo(other.getState()) == 0){
            if (county.compareTo(other.getCounty()) == 0){
                return town.compareTo(other.getTown());
            }
            else return county.compareTo(other.getCounty());
        }
        else return state.compareTo(other.getState());
    }
    
    /**
     * Compares two ZipCodeLocation objects by latitude, then longitude. 
     * 
     * @param other The other object to be compared to this one. 
     * @return integer, < 0 if this object is less than other, 0 if this object is equal to other, 
     * or > 0 if this object is greater than other.
     */
    public int compareByLatLong(ZipCodeLocation other){
        if (Math.abs(latitude - other.getLatitude()) < 0.000001){
            if (Math.abs(longitude - other.getLongitude()) < 0.000001)
                return 0;
            else if (longitude > other.getLongitude())
                return 1;
            else
                return -1;
        }
        else if (latitude > other.getLatitude())
            return 1;
        else
            return -1;
    }
    
    /**
     * Returns a String representing all of the data in this object. 
     * Overrides Object.toString()
     * 
     * @return String containing all data in this object.
     */
    public String toString(){
        String out = "";
        out += zipCode  + ", ";
        out += latitude + ", ";
        out += longitude + ", ";
        out += town + ", ";
        out += state + ", ";
        out += county + ", ";
        out += type;
        return out;
    }
    
    /**
     * 
     * @return String: the zip code of this object
     */
    public String getZipCode(){
        return zipCode;
    }
    
    /**
     * 
     * @return double: the latitude of this object
     */
    public double getLatitude(){
        return latitude;
    }
    
    /**
     * 
     * @return double: the longitude of this object
     */
    public double getLongitude(){
        return longitude;
    }
    
    /**
     * 
     * @return String: the town name of this object
     */
    public String getTown(){
        return town;
    }
    
    /**
     * 
     * @return String: the name of the state or territory
     */
    public String getState(){
        return state;
    }
    
    /**
     * 
     * @return String: the name of the county
     */
    public String getCounty(){
        return county;
    }
    
    /**
     * 
     * @return String: the postal type
     */
    public String getType(){
        return type;
    }

}
