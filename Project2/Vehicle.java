/**
 * Brett Ratner
 * Assignment: Project 2
 * Class: CSC230-02
 * @author Brett Ratner
 */
import jsjf.*;
import jsjf.exceptions.*;
import java.util.*;
import java.text.*;


    /**
      *This class contains the the number of each vehicle called vehicleNumber, which street the vehicle is in called
      *Street, which direction the vehicle is going in called direction and the arrivalTime for each vehicle as 
      *well as the departureTime for each vehicle.
    */
public class Vehicle
{
        private int vehicleNumber;					
        private enum Street {Church, Main};			
        private Street street;
        private enum Direction {N, S, E, W};		
        private Direction direction;
        private int arrivalTime;					
        private int departureTime;					

		
        /**
          *the constructor creates the Vechile object that has parameters. 
          */
        public Vehicle(int vehicleNum, int timeArrived)
        {
                vehicleNumber = vehicleNum;
                arrivalTime = timeArrived;    

				Random num = new Random();
				int streetName = num.nextInt(2);
				Random direct = new Random();
				int directionName = num.nextInt(4);
				
        
            // the Enums that inputs the two streets Church and Main.
			switch (streetName)				
			{
				case 0:
						street = street.Church;
						break;
				case 1:
						street = street.Main;
						break;
			}

            // the Enum inputs the four directions North(N), South(s), East(E), West(W).
			switch (directionName)	
			{
				case 0:
						direction = direction.N;
						break;
				case 1:
                        direction = direction.S;
                        break;
						
				case 2:
                        direction = direction.E;
                        break;
						
				case 3:
						direction = direction.W;
						break;
			}
		}
       /**The toString method returns whcich lane the car was in as well as which direction it went in
         *along with the number of the vehicle and that vehicles total wait time, along with how long 
         *that vehicle took to get into a gueue.
        */
        public String toString()
        {
                //this makes a new numberFormat object called format.
                NumberFormat format = NumberFormat.getInstance();				
                String waitTime = format.format(departureTime - arrivalTime);				
                String totalTimeElapsed = format.format(departureTime);	
                String display; 

                //retuns the display for the northbound, southbound, eastbound, and westbound traffic.
				if(direction.equals("N"))	
                        return display = "[Time " + totalTimeElapsed + "] Vehicle #" + vehicleNumber + " (northbound) moved through intersection. Total wait time " + waitTime + " seconds.";
                
				if(direction.equals("S"))	
                       return display = "[Time " + totalTimeElapsed + "] Vehicle #" + vehicleNumber + " (southbound) moved through intersection. Total wait time " + waitTime + " seconds.";
                
				if(direction.equals("E"))	
                       return display = "[Time " + totalTimeElapsed + "] Vehicle #" + vehicleNumber + " (eastbound) turned right and headed southbound. Total wait time " + waitTime + " seconds.";
                
				else 
                       return display =  "[Time " + totalTimeElapsed + "] Vehicle #" + vehicleNumber + " (westbound) turned right and headed northbound. Total wait time " + waitTime + " seconds.";
        }
		
		/**
         *The accessor for the vehicle number.
         *@return the vehicle number
        */
        public int getVehicleNumber()
        {
                return vehicleNumber;
        }

		/**
         *The accessor for the Street.
         *@return the street
        */
        public String getStreet()
        {
                return street + "";
        }
		
		/**
         *The accessor for the Direction.
         *@return the Direction
        */
        public String getDirection()
        {
                return direction + "";
        }
		
		/**
         *The arrival time mutator, sets a value for the arrivalTime
         *with the variable arrive.
        */
        public void setArrivalTime(int arrive)
        {
                arrivalTime = arrive;
        }

		/**
         *The accessor for the ArrivalTime.
         *@return the ArrivalTime.
        */
        public int getArrivalTime()
        {
                return arrivalTime;
        }

		/**
         *The departure time muator, sets a value for the departureTime
         *with the variable departure.
        */
        public void setDepartureTime(int departure)
        {
                departureTime = departure;
        }
				
		/**
         *The accessor for the DepartureTime.
         *@return the departureTime
        */
        public int getDepartureTime()
        {
                return departureTime;
        }
}