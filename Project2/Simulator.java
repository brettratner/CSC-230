/**
 * Brett Ratner
 * Assignment: Project 2
 * Class: CSC230-02
 * @author Brett Ratner
 */

import java.io.*;
import java.util.*;
import jsjf.*;
import jsjf.exceptions.*;
import java.text.*;


/** 
  *This class is called by the Driver class and this class is doing 
  *the actual simulation and it makes eight linked queues to randomly
  *store the vehicles in. It will keep making new vehicles until the 
  * manx number of vechicles is reached and than it wil let those vehicles
  *pass through the traffic light than stop.
  */
public class Simulator

{
        /**these are all eight of the linked queues*/
        LinkedQueue<Vehicle> eastMain         = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> eastRightMain    = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> westMain         = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> westRightMain    = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> northChurch      = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> northRightChurch = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> southChurch      = new LinkedQueue<Vehicle>();
        LinkedQueue<Vehicle> southRightChurch = new LinkedQueue<Vehicle>();

        private EmptyCollectionException excep = new EmptyCollectionException ("Queue is empty");
        
        //sets the first vehicle number to one instead of it starting at 0.
        private int vehicleNumber = 1;
        
        int timer = 0;      //makes the timer varialbe
        int count = 0;      //makes the count variable
        int trafficWaitTime;       //makes the trafficWaitTime variable
		
        // sets 120 to be the max number of vehicles to be made
        private final int maxNumberOfVehicles = 120;
		
	public void run()
        {
        /**
          *this pre-populates the intersection with the random creation of 
          *7-12 vehicle objects, and randomly assigning them to a linked queue
          *by randomly assigning them a direction and a lane.
        */
            
            for (int i = 0; i <= ((int) (Math.random() * 5 + 7)); i++)
            {
                    Vehicle vehicle = new Vehicle (vehicleNumber, timer);
                
                        if (vehicle.getStreet().equals("Church"))
                        {
                                if (vehicle.getDirection().equals("N"))
                                {
                                    northChurch.enqueue(vehicle);
                                }
										
                                else if	(vehicle.getDirection().equals("S"))
                                     {   
                                         southChurch.enqueue(vehicle);
									 }
								else if (vehicle.getDirection().equals("E"))
                                     {
										 northRightChurch.enqueue(vehicle);
								     }
                                else{ southRightChurch.enqueue(vehicle); }
                        }
                        else
                        {
                                if (vehicle.getDirection().equals("E"))
                                {
                                    eastMain.enqueue(vehicle);
                                }		
                                else if (vehicle.getDirection().equals("W"))
                                     {
                                         westMain.enqueue(vehicle);
                                     }
								else if (vehicle.getDirection().equals("N"))
                                     {
										 eastRightMain.enqueue(vehicle);
                                     }
								else{ westRightMain.enqueue(vehicle); }
                        }
						vehicleNumber++;
			}
			
			try
			{
    			//this makes the output file called output.txt
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
                BufferedWriter error  = new BufferedWriter(new FileWriter("error.txt", true));
        
                NumberFormat fmt = NumberFormat.getInstance();
                fmt.setMinimumIntegerDigits(2);
                
                 writer.write ("---Start of simulation, time set to 0.\n");

                while ((!northChurch.isEmpty()) || (!southChurch.isEmpty()) || (!eastRightMain.isEmpty()) || (!westRightMain.isEmpty()) || 
                       (!eastMain.isEmpty()) || (!westMain.isEmpty()) || (!northRightChurch.isEmpty()) || (!southRightChurch.isEmpty()))
                {
                
                       
                        /**Print out the beginning of North-South traffic*/
                        writer.write ("---Light changed.  Now processing north/south-bound traffic---\n");
                        
                        while (count < 2)
                        {
                                //this increments the timer by 3 seconds
                                timer = timer + 3;
                        
                                /**
                                *This will remove the vehicles from the front of the appropriate queue as long
                                *as the queue is not empty and print it to the output.txt and if it is 
                                *empty and the EmptyCollectionException is cought than it will do a
                                *println saying that the queue is empty.
                                */
                                if (!northChurch.isEmpty())
                                {
                                        
                                        try
                                        {
                                                Vehicle vehicle = northChurch.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                        
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (northbound) " + "continued straight. Total wait time " + trafficWaitTime +
                                                              " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions)
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would go straight and go north on church street");
                                        }
                                }
                        
                                if (!southChurch.isEmpty())
                                {
                                
                                        try
                                        {
                                                Vehicle vehicle = southChurch.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() +
                                                              " (southbound) " + "continued straight. Total wait time " + trafficWaitTime + 
                                                              " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions)
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would go straight and go south on church street");
                                        }
                                }
								
								 if (!northRightChurch.isEmpty())
                                {
                                        
                                        try
                                        {
                                                Vehicle vehicle = northRightChurch.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                        
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (northbound) " + "turned right and headed eastbound. Total wait time " + 
                                                              trafficWaitTime +  " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions)
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would make a right turn from north church street and go east on main street");
                                        }
                                }
                                
								 if (!southRightChurch.isEmpty())
                                {
                                        
                                        try
                                        {
                                                Vehicle vehicle = southRightChurch.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                        
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (southbound) " + "turned right and headed westbound. Total wait time " + 
                                                              trafficWaitTime +  " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions)
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would make a right turn from south church street and go west on main street");
                                        }
                                }
							    //increment the count variable
                                count++;
                        
                        }
                        
                        writer.write ("\n");
                        
                        count = 0;
        
						/**populates the intersection again with the random creation of 8-15 
                          *additional vehicles, but will stop making new vehicles if the 
                          *max number of vehicles is eaqual to the vehicle number. This will
                          *place the vehicles in the right queue.
                          */
                        for (int i = 0; i <= ((int) (Math.random() * 7 + 8)); i++)
                        {
                                if (vehicleNumber <= maxNumberOfVehicles)
                                {
                                        Vehicle vehicle = new Vehicle (vehicleNumber, timer);
                
                                        if (vehicle.getStreet().equals("Church"))
                                        {
                                                if (vehicle.getDirection().equals("N"))
                                                {
                                                    northChurch.enqueue(vehicle);
                                                }		
                                                else if (vehicle.getDirection().equals("S"))
                                                {
                                                         southChurch.enqueue(vehicle);
												}
												else if (vehicle.getDirection().equals("E"))
                                                {
														 northRightChurch.enqueue(vehicle);
												}
												else{ southRightChurch.enqueue(vehicle); }
                                        }
                                        else
                                        {
                                                if (vehicle.getDirection().equals("W"))
                                                { 
                                                    westMain.enqueue(vehicle);
														
                                                }
                                                else if (vehicle.getDirection().equals("E"))
                                                     {
                                                         eastMain.enqueue(vehicle);
												     }
												else if (vehicle.getDirection().equals("N"))
                                                     {
														 eastRightMain.enqueue(vehicle);
                                                     }
												
												else{ westRightMain.enqueue(vehicle); }
                                        }
                                        
                                        vehicleNumber++;
                                }
                        }

                        /**Prints out the beginning of East-West traffic*/
                        writer.write ("---Light changed. Now processing east/west-bound traffic---\n");
                        
                        while (count < 4)
                        {
                                //this increments the timer by 3 seconds
                                timer = timer + 3;
        
                              /**
                                *This will remove the vehicles from the front of the appropriate queue as long
                                *as the queue is not empty and print it to the output.txt and if it is 
                                *empty and the EmptyCollectionException is cought than it will do a
                                *println saying that the queue is empty.
                                */

                                if (!eastMain.isEmpty())
                                {
                                
                                        try
                                        {
                                                Vehicle vehicle = eastMain.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (eastbound) " + "turned right and headed southbound. Total wait time " + 
                                                              trafficWaitTime + " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions)
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would go straight and go east on main street");
                                        }
                                }
                                
                                
                                if (!westMain.isEmpty())
                                {
                                
                                        try
                                        {
                                                Vehicle vehicle = westMain.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (westbound) " + "continued straight. Total wait time " + trafficWaitTime + 
                                                              " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions) 
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would go straight and go west on main street");
                                        }
                                }
								
								if (!eastRightMain.isEmpty())
                                {
                                
                                        try
                                        {
                                                Vehicle vehicle = eastRightMain.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (eastbound) " + "turned right and headed southbound. Total wait time " + 
                                                              trafficWaitTime + " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions)
                                        {
                                                error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would make a right turn from east main street and go south on church street");
                                        }
                                }
                                
								if (!westRightMain.isEmpty())
                                {
                                        try
                                        {
                                                Vehicle vehicle = westRightMain.dequeue();
                                                vehicle.setDepartureTime(timer);
                                                trafficWaitTime = vehicle.getDepartureTime() - vehicle.getArrivalTime();
                
                                                writer.write ("[Time " + fmt.format(timer) + "] Vehicle #" + vehicle.getVehicleNumber() + 
                                                              " (westbound) " + "turned right and headed northbound. Total wait time " + 
                                                              trafficWaitTime + " seconds.\n");
                                        }
                                        catch (EmptyCollectionException Exceptions) 
                                        {
                                               error.append ("Tried to remove from an empty Queue at time:" + fmt.format(timer) + 
                                                    "and it would make a right turn from west main street and go north on church street");
                                        }
                                }
                                //increment the count variable
                                count++;
                        }
                        
                        writer.write ("\n");                        
                        count = 0;
                
                        /**populates the intersection again with the random creation of 3-15 
                          *additional vehicles, but will stop making new vehicles if the 
                          *max number of vehicles is eaqual to the vehicle number.
                          */
                        for (int i = 0; i <= ((int) (Math.random() * 12 + 3)); i++)
                        {
                                if (vehicleNumber <= maxNumberOfVehicles)
                                {
                                        Vehicle vehicle = new Vehicle (vehicleNumber, timer);
                
                                        if (vehicle.getStreet().equals("Church"))
                                        {
                                                if (vehicle.getDirection().equals("N"))
                                                {
                                                        northChurch.enqueue(vehicle);
                                                }
                                                else{ southChurch.enqueue(vehicle); }
                                        }
                                        else
                                        {
                                                if (vehicle.getDirection().equals("W"))
                                                {
                                                    westMain.enqueue(vehicle);
                                                }
                                                else{ eastMain.enqueue(vehicle); }
                                        }
                                        
                                        
                                        vehicleNumber++;
                                }
                        }
                }
                
                System.out.println ("Simulation Success");

                
                //closes the text file
                writer.close();
                error.close();
                
                }
                //this will catch the IOException if it cannot write to the ouput file */
                catch (IOException exception)
                {
                        System.err.println ("Can not make the output file.");
                        System.exit(1);
                }
        }        
}