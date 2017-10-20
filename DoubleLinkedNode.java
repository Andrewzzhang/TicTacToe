/**
 * Class that represents double linked node.
 * @author Zhaohe Zhang	
 * Date: 2017/10/18
 * @param <T>
 */

public class DoubleLinkedNode<T>
{
	/* Attributes declarations. */
	private DoubleLinkedNode<T> prev;
	private DoubleLinkedNode<T> next;
	private T element;
	
	/**
	 * Default constructor.
	 */
	public DoubleLinkedNode()
	{
		this.prev = null;
		this.next = null;
		this.element = null;
	}
	
	/**
	 * Constructor creates DoubleLinkedNode using given element.
	 * @param element the element to be stored into the node.
	 */
	public DoubleLinkedNode(T element)
	{
		this.prev = null;
		this.next = null;
		this.element = element;
	}
	
	/**
	 * getElement method return the element stored in the node.
	 * @return the element stored in the node.
	 */
	public T getElement()
	{
		return this.element;
	}
	
	/**
	 * setElement method replace or store the new element into the node.
	 * @param newElement to be stored into the node.
	 */
	public void setElement(T newElement)
	{
		this.element = newElement;
	}
	
	/**
	 * getNext method return the next node.
	 * @return the node next to current node.
	 */
	public DoubleLinkedNode<T> getNext()
	{
		return next;
	}
	
	/**
	 * setNext method set the given node as the next node.
	 * @param newNext node to be set as the next node.
	 */
	public void setNext(DoubleLinkedNode<T> newNext)
	{
		this.next = newNext;
	}
	
	/**
	 * getPrev method return the previous node.
	 * @return the previous node.
	 */
	public DoubleLinkedNode<T> getPrev()
	{
		return prev;
	}
	
	/**
	 * setPrev method set the given node as the previous node.
	 * @param newPrev node to be set as the previous node.
	 */
	public void setPrev(DoubleLinkedNode<T> newPrev)
	{
		this.prev = newPrev;
	}
	
	/**
	 * remove method remove the current node from the list.
	 */
	public void remove()
	{
		if(this.getPrev() == null && this.getNext() == null) /* remove the node when it is the only node in the list */
		{
			this.element = null;
		}
		else if(this.getPrev() == null) /* remove the node when it is the head of the list. */
		{
			this.getNext().setPrev(this.getPrev());
		}
		else if(this.getNext() == null) /* remove the node when it is the tail of the list */
		{
			this.getPrev().setNext(this.getNext());
		}
		else /* remove the node when it is at the middle of the list. */
		{
			this.getPrev().setNext(this.getNext());
			this.getNext().setPrev(this.getPrev());
		}
	}
}
