/** Brett Ratner 
 *	CSC230-02
 *	Lab5
 */


package jsjf;

import java.util.LinkedList;
import java.text.DecimalFormat;
import java.lang.Iterable;
import java.util.Iterator;


	
public abstract class HashtableADT<T> implements Iterable
{
  /**
    * creates an array of linkedLists.
    */
	protected LinkedList<T>[] list;
	
    /**
     * The hashCapacity is the size of the hashtable.The 
     * collisionAmount are the number of collisions. The 
     * overallHashSize is a counter of the total number 
     * of elements in the hashTable. The emptyCell is the
     * amount of empty cells there are.	
     */
	protected int collisionAmount;
	protected int overallHashSize;
	protected int hashCapacity;
	protected int emptyCell;

	
	/**
	 * constructor with parameter.
	 */
	protected HashtableADT(int phashCapacity)
	{
		this.hashCapacity = phashCapacity;
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
              boolean
     */	public void addElement(T element)
	{
		int index = element.hashCode();
		int key = Math.abs(index % list.length); 
		//boolean bool = false;
		if(list[key].size() == 0)
		{
			(list[key]).add(element);
			overallHashSize++;
			emptyCell--;
			//bool = true;
		}
		else
		{
			(list[key]).add(element);
			overallHashSize++;
			collisionAmount++;	
		//bool = false;
		}
		//return bool;

	}
	
        public T find(T element)
	{
            for (int i = 0; i < hashCapacity; i++)
            {
                for(T obj: list[i])
                {
                    if (element.equals(obj)) {
                        return(obj);	
                    }				
                }
            }
            return (null);
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
                for(T obj: list[i])
                {
                    if (element.equals(obj)) {
                        return(true);	
                    }				
                }
            }
            return (false);
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
	
	public int getLongestList(){
		int maxIdx = -1, maxLen = 0;
		for ( int i = 0; i < this.hashCapacity; i++){
			if(maxLen < this.list[i].size()){
				maxIdx = i;
				maxLen = this.list[i].size();
			}
		}
		return maxIdx;
	}
	public int getLongestListSize(){
		int maxIdx = -1, maxLen = 0;
		for ( int i = 0; i < this.hashCapacity; i++){
			if(maxLen < this.list[i].size()){
				maxIdx = i;
				maxLen = this.list[i].size();
			}
		}
		return maxLen;
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
