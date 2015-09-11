/** Hank Harvey and Brett Ratner 
*	CSC230-02
*	Lab05
*/
  
/**
*	This program...
*/

package edu.tcnj.csc230;

/** *   This program... */ 
public class Person implements Comparable<Person> 
{ 
	/**    
	*   These instance variables create the firstName(givenName), LastName(surname),
	*	streetAddress, city, state, zipCode, bloodType and its rhesus, a private
	*	String and coun a private int.     
	*/
	
	private String givenName; 
	private String surname;     
	private String streetAddress;     
	private String city;     
	private String state;
	private String zipCode;     
	private String bloodType;     
	private String rhesus;     
	private static long count;

	/**
     *	This constructor creates the person object and it has in the parameters the first name(givenName),
     *	lastname(surname), streetAddress, city, state, zipcode, bloodtype as well as the rhesus.
     */
	public Person(String givenName, String surname, String streetAddress, String city, String state, String zipCode, String bloodType, String rhesus)
	{
		this.givenName = givenName;
		this.surname = surname;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.bloodType = bloodType;
		this.rhesus = rhesus;
	}
	
	/**
	*	This method returns the given name
	*/
	public String getGivenName()
	{
		return givenName;
	}

	/**
	*	This method returns the surname
	*/
	public String getSurname()
	{
		return surname;
	}

	/**
	*	This method returns the street address
	*/
	public String getAddress()
	{
		return streetAddress;
	}

	/**
	*	This method returns the city
	*/
	public String getCity()
	{
		return city;
	}

	/**
	*	This method returns the state
	*/
	public String getState()
	{
		return state;
	}
	
	/**
	*	This method returns the zip code
	*/
	public String getZip()
	{
		return zipCode;
	}
	
	/**
	*	This method returns the blood type
	*/
	public String getBloodType()
	{
		return bloodType;
	}
	
	/**
     *	This method returns the Rhesus of the blood type
	*/
	public String getRhesus()
	{
		return rhesus;
	}

	/**
	*	This method sets the counter
	*/
	public long getCount()
	{
		return count;
	}

	/**
	*	This method sets the counter
	*/
	public void setCount(long val)
	{
		count = val;
	}
	
	/**
	*	This method compares according to spec
	*/
	public int compareTo(Person p)
	{
		count++;
		if (((p.bloodType).compareToIgnoreCase(bloodType)) > 0)
		{
			return -1;
		} else if (((p.bloodType).compareToIgnoreCase(bloodType)) < 0)
		{
			return 1;	
		} else if (((p.rhesus).compareToIgnoreCase(rhesus)) > 0)
		{
			return -1;
		} else if (((p.rhesus).compareToIgnoreCase(rhesus)) < 0)
		{
			return 1;	
		} else if (((p.state).compareToIgnoreCase(state)) > 0)
		{
			return -1;
		} else if (((p.state).compareToIgnoreCase(state)) < 0)
		{
			return 1;	
		}
		
		return 0;
	}
	
	/**
	*	This method returns a string representation of object content
	*/
	public String toString()
	{
		return "Given Name: " + givenName + "\t" + "Surname: " + surname + "\t" + "Street Address: " + streetAddress + "\t" + "City: " + city + "\t" + "State: " + state + "\t" + "Zip Code: " + zipCode + "\t" + "Blood Type: " + bloodType + "\t" + "Rhesus Factor: " + rhesus + ".";
	}

	
}