/**
 * Class that
 * @author Zhaohe Zhang
 * Student Number: 250905014
 * Date: 
 */


public class TTTDictionary<T> implements TTTDictionaryADT
{
	private DoubleLinkedNode<TTTRecord>[] hashTable;
	private int tableSize;
	private int numElements = 0;
	private int x = 11;
	
	@SuppressWarnings("unchecked")
	public TTTDictionary()
	{
		tableSize = 997;
		hashTable = new DoubleLinkedNode[tableSize];
	}
	
	@SuppressWarnings("unchecked")
	public TTTDictionary(int newTableSize)
	{
		this.tableSize = newTableSize;
		hashTable = new DoubleLinkedNode[tableSize];
	}

	private int hashing(String record, int size, int x)
	{
		int hashCode = record.charAt(0);
		for(int index = 1; index < record.length(); index ++)
		{
			hashCode = (hashCode * x + (int)record.charAt(index)) % size;
		}
		return hashCode;
	}
	
	public int put(TTTRecord record) throws DuplicatedKeyException
	{
		int collision = 0;
		int hashCode = hashing(record.getConfiguration(), this.tableSize, x);
		
		DoubleLinkedNode<TTTRecord> newRecord = new DoubleLinkedNode<TTTRecord>(record);
		if(hashTable[hashCode] != null)
		{
			DoubleLinkedNode<TTTRecord> current = hashTable[hashCode];
			collision = 1;
			
			while(current != null)
			{	
				if(current.getElement().getConfiguration().equals(newRecord.getElement().getConfiguration()))
				{
					throw new DuplicatedKeyException(record.getConfiguration());
				}
				current = current.getNext();
			}
			newRecord.setNext(hashTable[hashCode]);
			hashTable[hashCode].setPrev(newRecord);
			hashTable[hashCode] = newRecord;
			numElements += 1;
		}
		else
		{
			hashTable[hashCode] = newRecord;
		}
		return collision;
	}

    public void remove(String config) throws InexistentKeyException
    {
    		boolean found = false;
    		int hashCode = hashing(config, this.tableSize, x);
    		
    		if(hashTable[hashCode] == null)
    		{
    			throw new InexistentKeyException(config);
    		}
    		else
    		{
    			DoubleLinkedNode<TTTRecord> current = hashTable[hashCode];
    			while(current != null)
    			{
    				if(current.getElement().getConfiguration().equals(config))
    				{
    					found = true;
    					if(current.getPrev() == null)
    					{
    						hashTable[hashCode] = current.getNext();
    						current.remove();
    					}
    					else
    					{
    						current.remove();
    					}
    					numElements -= 1;
    					break;
    				}
    				current = current.getNext();
    			}
    		}
    		if(found == false)
    		{
    			throw new InexistentKeyException(config);
    		}
    }

    public TTTRecord get(String config)
    {
    		TTTRecord result = null;
    		int hashCode = hashing(config, this.tableSize, x);
    		DoubleLinkedNode<TTTRecord> current = hashTable[hashCode];
    		while(current != null)
    		{
    			if(current.getElement().getConfiguration().equals(config))
    			{
    				result = current.getElement();
    				break;
    			}
    			current = current.getNext();
    		}
    		return result;
    }

    public int numElements()
    {
    		return numElements;
    }

}
