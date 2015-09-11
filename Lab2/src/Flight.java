/*
  Names: Dylan Wulf, Brett Ratner, Sam Mills
  Class: CSC230-02
  Assignment: Lab2
*/
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class Flight implements Comparable<Flight> {
    String sourceCity;
    String destinationCity;
    String timeEnRoute;
    String indent;
    String flightType;
    GregorianCalendar today;
    GregorianCalendar departureTime;
    GregorianCalendar arrivalTime;
    String departureDateStr;
    String arrivalDateStr;
    
    /**
     * This constructor creates a new Flight object with the following parameters. It assumes the date is today's date, 
     * since the text file does not include a date. I did the best I could with the time zones, but the use of three-letter 
     * time zone codes is deprecated since one code sometimes points to multiple locations. The three-letter codes are the only 
     * form of time zone identification provided by the text file, so that's what I'm forced to use.
     * 
     * @param newIndent The Indent code for the flight.
     * @param newFlightType The type of flight.
     * @param newSourceCity The source city of the flight.
     * @param newDestinationCity The destination city of the flight.
     * @param newDepartureTime The departure time of the flight, as it appears in the text file (unformatted).
     * @param newArrivalTime The estimated arrival time of the flight, as it appears in the text file (unformatted).
     * @param newEstTimeEnRoute The estimated length of the flight.
     */
    public Flight(String newIndent, String newFlightType, String newSourceCity, String newDestinationCity, 
            String newDepartureTime, String newArrivalTime, String newEstTimeEnRoute){
        sourceCity = newSourceCity;
        destinationCity = newDestinationCity;
        timeEnRoute = newEstTimeEnRoute;
        indent = newIndent;
        flightType = newFlightType;
        String departureTimeStr = newDepartureTime;
        String arrivalTimeStr = newArrivalTime;
        today = new GregorianCalendar();
        int departureHour = Integer.parseInt(departureTimeStr.substring(4, 6));
        int departureMinute = Integer.parseInt(departureTimeStr.substring(7, 9));
        TimeZone departureTimeZone = TimeZone.getTimeZone(departureTimeStr.substring(12, 15));
        if (departureTimeStr.substring(9, 11).equalsIgnoreCase("PM")){
            departureHour += 12;
        }
        departureTime = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 
                today.get(Calendar.DAY_OF_MONTH), departureHour, departureMinute);
        departureTime.setTimeZone(departureTimeZone);
        int arrivalHour = Integer.parseInt(arrivalTimeStr.substring(4, 6));
        int arrivalMinute = Integer.parseInt(arrivalTimeStr.substring(7, 9));
        TimeZone arrivalTimeZone = TimeZone.getTimeZone(arrivalTimeStr.substring(12, 15));
        if (arrivalTimeStr.substring(9, 11).equalsIgnoreCase("PM")){
            arrivalHour += 12;
        }
        arrivalTime = new GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 
                today.get(Calendar.DAY_OF_MONTH), arrivalHour, arrivalMinute);
        arrivalTime.setTimeZone(arrivalTimeZone);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd HH:mm zzz");
        dateFormatter.setTimeZone(departureTimeZone);
        departureDateStr = dateFormatter.format(departureTime.getTime());
        dateFormatter.setTimeZone(arrivalTimeZone);
        arrivalDateStr = dateFormatter.format(arrivalTime.getTime());
    }
    
    /**
     * This constructor is to create a new Flight object for searching. The compareTo() method compares 
     * the source cities alphabetically, and is equal if the source city and
     * destination city are the same.
     * 
     * @param newSourceCity The source city
     * @param newDestCity The destination city
     */
    public Flight(String newSourceCity, String newDestCity){
        sourceCity = newSourceCity;
        destinationCity = newDestCity;
    }
    
    /**
     * Compares two flights alphabetically by source city.
     */
    public int compareTo(Flight other){
        if (sourceCity.compareTo(other.getSource()) == 0){
            return destinationCity.compareTo(other.getDestinationCity());
        }
        else{
            return sourceCity.compareTo(other.getSource());
        }
    }
    
    /**
     * @return String containing information about this object
     */
    public String toString(){
        return "Source: " + sourceCity + "\nDestination: " + destinationCity
                + "\nDeparture Time: " + departureDateStr + "\nEstimated Arrival: " + arrivalDateStr
                + "\nTime En Route: " + timeEnRoute
                + "\nIndent: " + indent + "\nType: " + flightType;
    }
    
    /**
     * 
     * @return String containing name of the source city of the flight.
     */
    public String getSource(){
        return sourceCity;
    }

    /**
     * 
     * @return String containing name of destination city of the flight
     */
    public String getDestinationCity(){
        return destinationCity;
    }
    
    /**
     * 
     * @return GregorianCalendar object containing the departure time of the flight.
     */
    public GregorianCalendar getGregorianDepartureTime(){
        return departureTime;
    }
    
    /**
     * 
     * @return GregorianCalendar object containing the estimated arrival time of the flight.
     */
    public GregorianCalendar getGregorianArrivalTime(){
        return arrivalTime;
    }
}
