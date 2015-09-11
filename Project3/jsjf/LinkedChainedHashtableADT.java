

package jsjf;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedChainedHashtableADT<T> extends HashtableADT implements Iterable {

    public LinkedChainedHashtableADT(int phashCapacity) {
        super(phashCapacity);
    }
        
    public Iterator<T> iterator() {
        LinkedList tmpList = new LinkedList();
        
  		for (int i = 0; i < hashCapacity; i++)
		{
                    LinkedList<T> temp = list[i];
                        
                    for(T obj: temp)
                    {
                        tmpList.add(obj);
                    }
		}
        return tmpList.iterator();
    }   

    public Iterator getChainIterator(int bucket) {
        return (this.list[bucket].iterator());
    }
    
}
