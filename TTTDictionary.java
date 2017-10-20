/**
 * Class that represents a hashTable to store <TTTRecord> records.
 * @author Zhaohe Zhang
 * Date: 2017/10/18
 */

public class TTTDictionary<T> implements TTTDictionaryADT
{
	/* Attributes declarations */
	private DoubleLinkedNode<TTTRecord>[] hashTable;
	private int tableSize;
	private int numElements = 0;
	private int x = 11;
	
	/**
	 * Default constructor.
	 */
	@SuppressWarnings("unchecked")
	public TTTDictionary()
	{
		tableSize = 997;
		hashTable = new DoubleLinkedNode[tableSize];
	}
	
	/**
	 * Constructor creates hash table with given size
	 * @param newTableSize the size of the hashing table.
	 */
	@SuppressWarnings("unchecked")
	public TTTDictionary(int newTableSize)
	{
		this.tableSize = newTableSize;
		hashTable = new DoubleLinkedNode[tableSize];
	}

	/**
	 * hashing method return a hashCode with given parameters.
	 * @param record the String to be changed to hashCode.
	 * @param size the size of the hash table.
	 * @param x the value x for Polynomial Hash Function.
	 * @return hashCode.
	 */
	private int hashing(String record, int size, int x)
	{
		int hashCode = record.charAt(0);
		for(int index = 1; index < record.length(); index ++)
		{
			hashCode = (hashCode * x + (int)record.charAt(index)) % size;
		}
		return hashCode;
	}
	
	/**
	 * put method put <TTTRecord> record into the hashTable.
	 * @param record to be stored into the hashTable.
	 * @return 1 if there is a collision occur.
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException
	{
		int collision = 0;
		int hashCode = hashing(record.getConfiguration(), this.tableSize, x); //Get the hashCode for the String representation.
		
		DoubleLinkedNode<TTTRecord> newRecord = new DoubleLinkedNode<TTTRecord>(record); // Create a new doubleLinkedNode to store the record.
		
		if(hashTable[hashCode] != null) // Check if a collision occurs.
		{
			DoubleLinkedNode<TTTRecord> current = hashTable[hashCode]; // Grep the existing node for calculation.
			collision = 1;
			
			while(current != null) // Loop over the entire link stored at this position.
			{	
				if(current.getElement().getConfiguration().equals(newRecord.getElement().getConfiguration())) // Check if the key is duplicated.
				{
					throw new DuplicatedKeyException(record.getConfiguration());
				}
				current = current.getNext();
			}
			newRecord.setNext(hashTable[hashCode]); // Set the new Node as the head and link the Node already exist to the new Node.
			hashTable[hashCode].setPrev(newRecord);
			hashTable[hashCode] = newRecord;
			numElements += 1; // The number of records increased by 1.
		}
		else
		{
			hashTable[hashCode] = newRecord; // Store the node at this position when the position is empty.
			numElements += 1; // The number of records increased by 1.
		}
		return collision;
	}

	/**
	 * remove method remove a <TTTRecord> record form the hashTable.
	 * @param config the string configuration of the record to be removed.
	 */
    public void remove(String config) throws InexistentKeyException
    {
    		boolean found = false;
    		int hashCode = hashing(config, this.tableSize, x); // Get the hashCode for the given config.
    		
    		if(hashTable[hashCode] == null) // Check if the position is empty, so that the record is not exist.
    		{
    			throw new InexistentKeyException(config);
    		}
    		else
    		{
    			DoubleLinkedNode<TTTRecord> current = hashTable[hashCode]; // Grep the existing node for calculation.
    			while(current != null) // Loop over the entire link.
    			{
    				if(current.getElement().getConfiguration().equals(config)) // Check if the node has the correct config.
    				{
    					found = true;
    					if(current.getPrev() == null) // If the node is either head or is the only node in the list.
    					{
    						hashTable[hashCode] = current.getNext();
    						current.remove();
    					}
    					else
    					{
    						current.remove();
    					}
    					numElements -= 1; // The total number of elements decreased by 1.
    					break;
    				}
    				current = current.getNext();
    			}
    		}
    		if(found == false) // If the position has node but the node was not exist in the list.
    		{
    			throw new InexistentKeyException(config);
    		}
    }

    /**
     * get method return the record with given string config.
     * @param config the string representation of record.
     * @return the record.
     */
    public TTTRecord get(String config)
    {
    		TTTRecord result = null;
    		int hashCode = hashing(config, this.tableSize, x); // Get hashCode for given config.
    		DoubleLinkedNode<TTTRecord> current = hashTable[hashCode]; // Grep the existing node for calculation.
    		while(current != null) // Loop over the entire list.
    		{
    			if(current.getElement().getConfiguration().equals(config)) // Check if the node has the correct config.
    			{
    				result = current.getElement();
    				break;
    			}
    			current = current.getNext();
    		}
    		return result; // Return the record.
    }

    public int numElements()
    {
    		return numElements; // Return the total number of records in the table.
    }

}
