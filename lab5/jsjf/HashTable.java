/** Brett Ratner and Ricky Zhao
 *	CSC230-02
 *	Lab5
 */


package jsjf;
import java.util.LinkedList;
import java.text.DecimalFormat;


	
public class HashTable<T>
{
  /**
    * creates an array of linkedLists.
    */
	private LinkedList<T>[] list;
	
	/**
     * The hashCapacity is the size of the hashtable.The 
     * collisionAmount are the number of collisions. The 
     * overallHashSize is a counter of the total number 
     * of elements in the hashTable. The emptyCell is the
     * amount of empty cells there are.	
     */
	private int collisionAmount;
	private int overallHashSize;
	private int hashCapacity;
	private int emptyCell;
	
	/**
     * LinkedHashTable constructor.
     * @param Size of the array. 
     */	public HashTable()

	{
		collisionAmount = 0;
		overallHashSize = 0;
		emptyCell = hashCapacity;
		
		list = (LinkedList<T>[]) (new LinkedList[75]);
		
		for (int i=0; i<75; i++)
		{
			list[i] = new LinkedList<T>();
		}
	}
	
	
	/**
	 *	Second constructor with parameter.
	 */
	public HashTable(int hashCapacity)
	{
		this.hashCapacity = hashCapacity;
		collisionAmount = 0;
		overallHashSize = 0;
		emptyCell = hashCapacity;
		
		list = (LinkedList<T>[]) (new LinkedList[hashCapacity]);
		
		for (int i=0; i < hashCapacity; i++)
		{
			list[i] = new LinkedList<T>();
		}
	}
	
	/**
     * Adds an element into the hash table.
     * @param  T element
     */	public void add(T element)
	{
		int index = element.hashCode();
		int key = Math.abs(index % list.length); 
		if(list[key].size() == 0)
		{
			(list[key]).add(element);
			overallHashSize++;
			emptyCell--;
		}
		else
		{
			(list[key]).add(element);
			overallHashSize++;
			collisionAmount++;	
		}
	}
	
	/**
     * Returns true if the hashtable contains the specified element passed.
     * @return returns true if matching element is found.
     * @ param T element
     */
	public boolean contains(T element)
	{
		for (int i = 0; i < hashCapacity; i++)
		{
			LinkedList<T> temp = list[i];
			if(element == temp)
			{
				return(true);	
			}
			else
			{
		        for(T obj: list[i])
		        {
					String word = obj.toString();
					String info = (String) element;
					if((word.toLowerCase()).contains(info.toLowerCase()))
					{
						return(true);	
					}				
		        }
			}
		}
        return (false);
	} 
	
	/**
     * Returns the size
     * @return Returns the size of the hashtable
     */
	public boolean isEmpty()
	{
		return (overallHashSize == 0);	
	}
	
	/**
     * Returns the number of collisions.
     * @return Returns collisions.
     */	public int collisions()
	{
		return (collisionAmount);	
	}
	
	/**
	 * Returns the size of hashTable
	 * @returns Returns size
	 */
	public int size()
	{
		return (overallHashSize);	
	}
	
	
	/**
	*	
	*/
	public String toString()
    {
    	DecimalFormat formatter = new DecimalFormat("00.0");
    	float bucketPercent = ((float) emptyCell /  hashCapacity) * 100;
    	String percent = formatter.format(bucketPercent);
        String result = "Number of elements: " + overallHashSize + "\n" + "Percentage of Empty Buckets: " + percent + "%\n" + "Hash Table Contents: \n" ;

  		for (int i = 0; i < hashCapacity; i++)
		{
			LinkedList<T> temp = list[i];
	        for(T obj: list[i])
	        {
	           result = result + obj.toString() + "\n";
	        }
		}
        return result;
        
    }
	
}
